package com.example.chooseyouradventurev2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Activite representant la page de jeu de mouvement
 */
public class JeuMouvement extends AppCompatActivity implements View.OnTouchListener {

    /**
     * variable definissant le nombre d'instruction à effectuer
     */
    private final int NBMAXINSTRUCTION = 10;

    /**
     * variable définissant le score du joueur et le nombre d'instruction effectué
     */
    private int score, nbInstruction;

    private int minDistanceX;

    private int minDistanceY;

    /**
     * variable definissant l instruction a effectue et la precedente
     */
    private int index, dernierIndex;

    private float debutX, finX, debutY, finY;

    private TextView instruction,textScore;

    /**
     * variable definissant le tableau des instructions
     */
    private String[] tabInstruction = {"vers le haut","vers le bas","à gauche","à droite"};

    /**
     * constructeur permettant la creation de l activite
     * @param savedInstanceState : parametre permettant de sauvegarder l etat de la page
     */
    protected void onCreate (Bundle savedInstanceState) {

        // on appelle le constructeur de AppCombatActivity
        super.onCreate(savedInstanceState);

        // ajout du layout sur l activite
        setContentView(R.layout.activity_jeu_mouvement);

        // on récupére la fenêtre
        Display fenetre = getWindowManager().getDefaultDisplay();

        // on crée la variable de taille et on récupére la taille de l'écran
        Point taille = new Point();
        fenetre.getSize(taille);

        // on définis les distances minimales de déplacement à la moitié de la taille de l'écran
        minDistanceX = taille.x/4;
        minDistanceY = taille.y/4;

        // on recupere le score
        score = getIntent().getIntExtra("score",0);

        // on recupere les champs de texte
        instruction = findViewById(R.id.instruction);
        textScore = findViewById(R.id.score);

        View ecran = findViewById(R.id.ecran);
        ecran.setOnTouchListener(this);

        changerInstruction();

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    public boolean onTouchEvent(MotionEvent event){

        switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:
                debutX = event.getX();
                debutY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                finX = event.getX();
                finY = event.getY();

                float deltaX = finX - debutX;
                float deltaY = finY - debutY;

                // Left to Right swipe action
                if (finX > debutX && Math.abs(deltaX) > minDistanceX)
                {
                    Toast.makeText(this, "" + index, Toast.LENGTH_SHORT).show ();

                    if(index == 3){

                        score++;

                    } else {

                        score--;

                    }

                } else if( finX < debutX && Math.abs(deltaX) > minDistanceX) {

                    if(index == 2){

                        score++;

                    } else {

                        score--;

                    }

                } else if( finY < debutY && Math.abs(deltaY) > minDistanceY) {

                    if(index == 0){

                        score++;

                    } else {

                        score--;

                    }

                } else if( finY > debutY && Math.abs(deltaY) > minDistanceY) {

                    Toast.makeText(this, "" + index, Toast.LENGTH_SHORT).show ();

                    if(index == 1){

                        score++;

                    } else {

                        score--;

                    }

                }

                changerInstruction();

                break;

        }

        return false;

    }

    /**
     * methode permettant le changement d instruction
     */
    public void changerInstruction(){

        // on augmente le nombre d'instruction effectué
        nbInstruction++;

        // si on a effectué le nombre d'instruction attendus
        if(NBMAXINSTRUCTION == nbInstruction){

            // on definis l activite comme etant celle de JeuMouvement
            Intent activite = new Intent(JeuMouvement.this, MainActivity.class);

            // on envoie le score a l activite
            activite.putExtra("score",score);
            activite.putExtra("dejaJoue",true);

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

            // on recupere une valeur aleatoire entre 0 et 3
            random = (int)(Math.random() * 4);

            // tant que la valeur obtenus aleatoirement est la meme que la precedente
        }while(random == index);

        // on ajoute le texte
        instruction.setText(tabInstruction[random]);

        // on garde le dernier index
        dernierIndex = index;

        // on change l instruction demande
        index = random;

        textScore.setText("score : " + score);

    }
}
