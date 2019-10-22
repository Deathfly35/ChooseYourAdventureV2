package com.example.chooseyouradventurev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Activite representant la page d accueil
 */
public class MainActivity extends AppCompatActivity {

    /**
     * variable definissant le bouton de lancement
     */
    private Button start;

    /**
     * Constructeur permettant l'initialisation de l activite
     * @param savedInstanceState : paramétre permettant de sauvegarder l etat de la page
     */
    protected void onCreate(Bundle savedInstanceState) {

        // on appelle le constructeur de AppCompatActivity
        super.onCreate(savedInstanceState);

        // application du layout sur l activite
        setContentView(R.layout.activity_main);

        // recuperation des boutons
        start = findViewById(R.id.start);

        // affectation des actions
        start.setOnClickListener(new View.OnClickListener() {

            // on gere le cas du clique
            public void onClick(View v) {

                // on fais un chiffre aléatoire entre 0 et 1 bornes comprises
                int random = (int)(Math.random()* 2);

                // en fonction de la valeur du random on joue à un jeu différent et donc
                switch(random){

                        // si random est égal à 0
                    case 0:

                        // on lance l activity de JeuQuizz
                        startActivity(new Intent(MainActivity.this, JeuQuizz.class));
                        break;

                        // si random est égal à 1
                    case 1:

                        // on lance l activity de JeuGeste
                        startActivity(new Intent(MainActivity.this, JeuGeste.class));
                        break;

                }

            }

        });
    }
}
