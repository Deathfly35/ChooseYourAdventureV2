package com.example.chooseyouradventurev2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

/**
 * Activite representant la page de jeu geste
 */
public class JeuGeste extends AppCompatActivity implements SensorEventListener {

    /**
     * variable definissant le nombre d'instruction à effectuer
     */
    private final int NBMAXINSTRUCTION = 10;

    /**
     * variable definissant la derniere detection
     */
    private long derniereDetection = 0;

    /**
     * variable definissant le champ d instruction de l application et du score
     */
    private TextView instruction,textScore;

    /**
     * variable definissant le tableau des instructions
     */
    private String[] tabInstruction = {"vers le haut","vers le bas","à plat","à l'envers","à gauche","à droite"};

    /**
     * variable definissant l instruction a effectue et la precedente
     */
    private int index,dernierIndex;

    /**
     * variable definissant le score de la partie et le nombre d'instruction effectué
     */
    private int score, nbInstruction;

    /**
     * constructeur permettant la creation de l activite
     * @param savedInstanceState : parametre permettant de sauvegarder l etat de la page
     */
    protected void onCreate(Bundle savedInstanceState) {

        // on appelle le constructeur de AppCombatActivity
        super.onCreate(savedInstanceState);

        // on recupere le score
        score = getIntent().getIntExtra("score",0);

        // ajout du layout sur l activite
        setContentView(R.layout.activity_jeu_geste);

        // on recupere les champs de texte
        instruction = findViewById(R.id.instruction);
        textScore = findViewById(R.id.score);

        // on recupere le detecteur de l'appareil
        SensorManager detecteur = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // on recupere l accelerometre du detecteur
        Sensor accelerometre = Objects.requireNonNull(detecteur).getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // on ajoute le listener sur le detecteur et l accelerometre
        detecteur.registerListener(this, accelerometre, SensorManager.SENSOR_DELAY_NORMAL);

        changerInstruction();

    }

    /**
     * methode permettant de gerer l etat du detecteur
     * @param event : parametre definissant l evenement concernant le detecteur
     */
    @SuppressLint("SetTextI18n")
    public void onSensorChanged(SensorEvent event) {

        // on recupre le detecteur de l evenement
        Sensor mySensor = event.sensor;

        // si on est sur la partie acceleromotre
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            // on recupere le temps en millisecondes
            long tempsActuel = System.currentTimeMillis();

            // si cela va faire plus de 100 milisecondes que l on a pas detecte
            if ((tempsActuel - derniereDetection) > 1000) {

                // on recupere la difference de la derniere mise à jour et le temps actuel
                derniereDetection = tempsActuel;

                // on recupere les valeurs x, y et z
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                // si le portable est incline vers le haut
                if(y > 8 && dernierIndex != 0){

                    // si on a la bonne reponse
                    if(index == 0){

                        // on incremente et affiche le score
                        textScore.setText("score : " + (++score));

                        // on passe a l'instruction suivante
                        changerInstruction();

                    // sinon
                    } else {

                        // on decremente et affiche le score
                        textScore.setText("score : " + (--score));

                    }

                // si le portable est incline vers le bas
                } else if(y < -8 && dernierIndex != 1){

                    // si on a la bonne reponse
                    if(index == 1){

                        // on incremente et affiche le score
                        textScore.setText("score : " + (++score));

                        // on passe a l'instruction suivante
                        changerInstruction();

                        // sinon
                    } else {

                        // on decremente et affiche le score
                        textScore.setText("score : " + (--score));

                    }

                // si le portable est à plat
                } else if(z > 8 && dernierIndex != 2){

                    // si on a la bonne reponse
                    if(index == 2){

                        // on incremente et affiche le score
                        textScore.setText("score : " + (++score));

                        // on passe a l'instruction suivante
                        changerInstruction();

                        // sinon
                    } else {

                        // on decremente et affiche le score
                        textScore.setText("score : " + (--score));

                    }

                // si le portable est à l'envers
                } else if(z < -8 && dernierIndex != 3){

                    // si on a la bonne reponse
                    if(index == 3){

                        // on incremente et affiche le score
                        textScore.setText("score : " + (++score));

                        // on passe a l'instruction suivante
                        changerInstruction();

                        // sinon
                    } else {

                        // on decremente et affiche le score
                        textScore.setText("score : " + (--score));

                    }

                // si le portable est incline a gauche
                } else if(x > 8 && dernierIndex != 4){

                    // si on a la bonne reponse
                    if(index == 4){

                        // on incremente et affiche le score
                        textScore.setText("score : " + (++score));

                        // on passe a l'instruction suivante
                        changerInstruction();

                        // sinon
                    } else {

                        // on decremente et affiche le score
                        textScore.setText("score : " + (--score));

                    }

                // si le portable est incline a droite
                } else if(x < -8 && dernierIndex != 5){

                    // si on a la bonne reponse
                    if(index == 5){

                        // on incremente et affiche le score
                        textScore.setText("score : " + (++score));

                        // on passe a l'instruction suivante
                        changerInstruction();

                        // sinon
                    } else {

                        // on decremente et affiche le score
                        textScore.setText("score : " + (--score));

                    }

                }

            }
        }
    }

    /**
     * methode permettant le changement d instruction
     */
    public void changerInstruction(){

        // si on a effectué le nombre d'instruction attendus
        if(NBMAXINSTRUCTION == nbInstruction){

            // on definis l activite comme etant celle de JeuQuizz
            Intent activite = new Intent(JeuGeste.this, MainActivity.class);

            // on envoie le score a l activite
            activite.putExtra("score",score);

            // on lance l activity
            startActivity(activite);

            // sinon
        } else {

            // on augmente le nombre d'instruction effectué
            nbInstruction++;

        }

        // variable definissant la prochaine instruction aleatoirement
        int random;

        do{

            // on recupere une valeur aleatoire entre 0 et 5
            random = (int)(Math.random() * 6);

        // tant que la valeur obtenus aleatoirement est la meme que la precedente
        }while(random == index);

        // on ajoute le texte
        instruction.setText(tabInstruction[random]);

        // on garde le dernier index
        dernierIndex = index;

        // on change l instruction demande
        index = random;

    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
