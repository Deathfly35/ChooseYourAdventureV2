package com.example.chooseyouradventurev2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


/**
 * Activite representant la page de jeu de mouvement
 */
public class JeuMouvement extends AppCompatActivity {

    /**
     * variable définissant le score du joueur
     */
    private int score;

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

    }

}
