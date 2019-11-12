package com.example.chooseyouradventurev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Activite representant la page d accueil
 */
public class MainActivity extends AppCompatActivity {

    /**
     * variable definissant le bouton de lancement
     */
    private Button start;

    /**
     * variable definissant le texte du score
     */
    private TextView textScore;

    /**
     * variable definissant le score
     */
    private int score;

    /**
     * variable definissant si le jeu a deja commence
     */
    private boolean dejaJoue;

    /**
     * variable definissant l activite suivante
     */
    private Intent activite;

    /**
     * Constructeur permettant l'initialisation de l activite
     * @param savedInstanceState : paramétre permettant de sauvegarder l etat de la page
     */
    protected void onCreate(Bundle savedInstanceState) {

        // on appelle le constructeur de AppCompatActivity
        super.onCreate(savedInstanceState);

        // on recupere le score
        score = getIntent().getIntExtra("score",0);

        // on recupere le fait que le jeu est deja commence ou non
        dejaJoue = getIntent().getBooleanExtra("dejaJoue",false);

        // si le jeu n'a pas deja commence
        if(!dejaJoue) {

            // application du layout sur l activite
            setContentView(R.layout.activity_main);

            // recuperation des boutons
            start = findViewById(R.id.start);

            // recuperation du texte du score
            textScore = findViewById(R.id.score);

            // on ajoute le texte du score
            textScore.setText("score : " + score);

            // affectation des actions
            start.setOnClickListener(new View.OnClickListener() {

                // on gere le cas du clique
                public void onClick(View v) {

                    // on lance la selection aleatoire d'activity
                    lancementActivity();

                }

            });

        // sinon
        } else {

            // on lance la selection aleatoire d'activity
            lancementActivity();

        }
    }

    public void lancementActivity(){

        // on fais un chiffre aléatoire entre 0 et 1 bornes comprises
        int random = (int) (Math.random() * 2);

        // en fonction de la valeur du random on joue à un jeu différent et donc
        switch (random) {

            // si random est égal à 0
            case 0:

                // on definis l activite comme etant celle de JeuQuizz
                activite = new Intent(MainActivity.this, JeuQuizz.class);
                break;

            // si random est égal à 1
            case 1:

                // on definis l activite comme etant celle de JeuGeste
                activite = new Intent(MainActivity.this, JeuGeste.class);
                break;

            // si random est égal à 2
            case 2:

                // on definis l activite comme etant celle de JeuMouvement
                activite = new Intent(MainActivity.this, JeuMouvement.class);
                break;

        }

        // on envoie les jeux faient et le score a l activite
        activite.putExtra("tabJeu", new ArrayList<Integer>());
        activite.putExtra("score",score);

        // on lance l activity
        startActivity(activite);

    }
}
