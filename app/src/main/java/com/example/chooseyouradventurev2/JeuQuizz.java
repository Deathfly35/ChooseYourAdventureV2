package com.example.chooseyouradventurev2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Activite representant la page de quizz
 */
public class JeuQuizz extends AppCompatActivity {

    /**
     * variable definissant le bouton de choix numero 1, 2, 3 et 4
     */
    private Button choix1, choix2, choix3, choix4;

    /**
     * variable definissant l enonce et le score
     */
    private TextView enonce, textScore;

    /**
     * variable definissant le tableau des choix, des questions et des bons choix
     */
    private String[][] tabQuizz = {{"enonce", "choix1", "choix2", "choix3", "choix4", "1"},
            {"enonce2", "choix12", "choix22", "choix32", "choix42", "2"}};

    /**
     * variable definissant les questions deja passe
     */
    private ArrayList<Integer> tabQuestionPasse;

    /**
     * variable définissant le numero de la question
     */
    private int random;

    /**
     * variable definissant le score du joueur
     */
    private int score;

    /**
     * variable definissant les jeux deja fais
     */
    private ArrayList<Integer> tabJeu;

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

        // on recupere les jeux deja fais
        tabJeu = getIntent().getIntegerArrayListExtra("tabJeu");

        // application du layout sur l activite
        setContentView(R.layout.activity_jeu_quizz);

        // recuperation des boutons
        choix1 = findViewById(R.id.choix1);
        choix2 = findViewById(R.id.choix2);
        choix3 = findViewById(R.id.choix3);
        choix4 = findViewById(R.id.choix4);

        // recuperation de l enonce
        enonce = findViewById(R.id.enonce);

        // recuperation du texte du score
        textScore = findViewById(R.id.score);

        // on initialise le tableau des questions deja pose
        tabQuestionPasse = new ArrayList<>();

        // affectation des actions de choix 1
        choix1.setOnClickListener(new View.OnClickListener() {

            // on gere le cas du clique
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {

                // on cree l alerte
                AlertDialog alertDialog = new AlertDialog.Builder(JeuQuizz.this).create();

                // on definit le titre
                alertDialog.setTitle("Résultat");

                // si il s agit de la bonne reponse
                if(tabQuizz[random][5].equals("1")){

                    // on augmente le score du joueur
                    score++;

                    // alors on affiche bonne reponse
                    alertDialog.setMessage("bonne réponse");

                // sinon
                } else {

                    // on diminue le score du joueur
                    score--;

                    // on affiche mauvaise reponse
                    alertDialog.setMessage("Mauvaise réponse");

                }

                // on ajoute le texte du score
                textScore.setText("score : " + score);

                // on cree l ecoute des actions sur l alerte
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {

                    // on gere le cas du clique
                    public void onClick(DialogInterface dialog, int which) {

                        // on retire l alerte
                        dialog.dismiss();

                    }

                });

                // on affichage l alerte
                alertDialog.show();

                // si on a selectionne la bonne reponse
                if(tabQuizz[random][5].equals("1")) {

                    // on lance la prochaine question et si toutes les questions ont ete pose alors on lance la prochaine activite
                    if (!initialisationQuizz()) {

                        // on definis l activite comme etant celle de JeuQuizz
                        activite = new Intent(JeuQuizz.this, MainActivity.class);

                        // on envoie le score et les jeux deja faient a l activite
                        activite.putExtra("score",score);
                        activite.putExtra("tabJeu",tabJeu.add(0));
                        activite.putExtra("dejaJoue",true);

                        // on lance l activity
                        startActivity(activite);

                    }

                }

            }

        });

        // affectation des actions de choix 2
        choix2.setOnClickListener(new View.OnClickListener() {

            // on gere le cas du clique
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {

                // on cree l alerte
                AlertDialog alertDialog = new AlertDialog.Builder(JeuQuizz.this).create();

                // on definit le titre
                alertDialog.setTitle("Résultat");

                // si il s agit de la bonne reponse
                if(tabQuizz[random][5].equals("2")){

                    // on augmente le score du joueur
                    score++;

                    // alors on affiche bonne reponse
                    alertDialog.setMessage("bonne réponse");

                    // sinon
                } else {

                    // on diminue le score du joueur
                    score--;

                    // on affiche mauvaise reponse
                    alertDialog.setMessage("Mauvaise réponse");

                }

                // on ajoute le texte du score
                textScore.setText("score : " + score);

                // on cree l ecoute des actions sur l alerte
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {

                    // on gere le cas du clique
                    public void onClick(DialogInterface dialog, int which) {

                        // on retire l alerte
                        dialog.dismiss();

                        // si on a selectionne la bonne reponse
                        if(tabQuizz[random][5].equals("2")) {

                            // on lance la prochaine question et si toutes les questions ont ete pose alors on lance la prochaine activite
                            if (!initialisationQuizz()) {

                                // on definis l activite comme etant celle de JeuQuizz
                                activite = new Intent(JeuQuizz.this, MainActivity.class);

                                // on envoie le score et les jeux deja faient a l activite
                                activite.putExtra("score",score);
                                activite.putExtra("tabJeu",tabJeu.add(0));
                                activite.putExtra("dejaJoue",true);

                                // on lance l activity
                                startActivity(activite);

                            }

                        }

                    }

                });

                // on affichage l alerte
                alertDialog.show();

            }

        });

        // affectation des actions de choix 3
        choix3.setOnClickListener(new View.OnClickListener() {

            // on gere le cas du clique
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {

                // on cree l alerte
                AlertDialog alertDialog = new AlertDialog.Builder(JeuQuizz.this).create();

                // on definit le titre
                alertDialog.setTitle("Résultat");

                // si il s agit de la bonne reponse
                if(tabQuizz[random][5].equals("3")){

                    // on augmente le score du joueur
                    score++;

                    // alors on affiche bonne reponse
                    alertDialog.setMessage("bonne réponse");

                    // sinon
                } else {

                    // on diminue le score du joueur
                    score--;

                    // on affiche mauvaise reponse
                    alertDialog.setMessage("Mauvaise réponse");

                }

                // on ajoute le texte du score
                textScore.setText("score : " + score);

                // on cree l ecoute des actions sur l alerte
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {

                    // on gere le cas du clique
                    public void onClick(DialogInterface dialog, int which) {

                        // on retire l alerte
                        dialog.dismiss();

                        // si on a selectionne la bonne reponse
                        if(tabQuizz[random][5].equals("3")) {

                            // on lance la prochaine question et si toutes les questions ont ete pose alors on lance la prochaine activite
                            if (!initialisationQuizz()) {

                                // on definis l activite comme etant celle de JeuQuizz
                                activite = new Intent(JeuQuizz.this, MainActivity.class);

                                // on envoie le score et les jeux deja faient a l activite
                                activite.putExtra("score",score);
                                activite.putExtra("tabJeu",tabJeu.add(0));
                                activite.putExtra("dejaJoue",true);

                                // on lance l activity
                                startActivity(activite);

                            }

                        }

                    }

                });

                // on affichage l alerte
                alertDialog.show();

            }

        });

        // affectation des actions de choix 4
        choix4.setOnClickListener(new View.OnClickListener() {

            // on gere le cas du clique
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {

                // on cree l alerte
                AlertDialog alertDialog = new AlertDialog.Builder(JeuQuizz.this).create();

                // on definit le titre
                alertDialog.setTitle("Résultat");

                // si il s agit de la bonne reponse
                if(tabQuizz[random][5].equals("4")){

                    // on augmente le score du joueur
                    score++;

                    // alors on affiche bonne reponse
                    alertDialog.setMessage("bonne réponse");

                    // sinon
                } else {

                    // on diminue le score du joueur
                    score--;

                    // on affiche mauvaise reponse
                    alertDialog.setMessage("Mauvaise réponse");

                }

                // on ajoute le texte du score
                textScore.setText("score : " + score);

                // on cree l ecoute des actions sur l alerte
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {

                    // on gere le cas du clique
                    public void onClick(DialogInterface dialog, int which) {

                        // on retire l alerte
                        dialog.dismiss();

                        // si on a selectionne la bonne reponse
                        if(tabQuizz[random][5].equals("4")) {

                            // on lance la prochaine question et si toutes les questions ont ete pose alors on lance la prochaine activite
                            if (!initialisationQuizz()) {

                                // on definis l activite comme etant celle de JeuQuizz
                                activite = new Intent(JeuQuizz.this, MainActivity.class);

                                // on envoie le score et les jeux deja faient a l activite
                                activite.putExtra("score",score);
                                activite.putExtra("tabJeu",tabJeu.add(0));
                                activite.putExtra("dejaJoue",true);

                                // on lance l activity
                                startActivity(activite);

                            }

                        }

                    }

                });

                // on affichage l alerte
                alertDialog.show();

            }

        });

        // on initialise le quizz
        initialisationQuizz();

    }

    /**
     * méthode permettant d'initialiser tous les champs de texte du quizz
     * @return on renvoit false si toutes les questions ont ete pose sinon renvoit vrai
     */
    protected boolean initialisationQuizz(){

        // si on a finis de pose les questions
        if(tabQuestionPasse.size() == tabQuizz.length){

            // alors on renvoit false
            return false;

        }

        // tant que
        do{

            // on récupére un nouvel index du tableau du quizz
            random = (int)(Math.random() * tabQuizz.length);

        // on est sur une question qui existe deja
        }while(tabQuestionPasse.contains(random));

        // on ajoute numero de la question
        tabQuestionPasse.add(random);

        // on ajoute le texte de l enonce
        enonce.setText(tabQuizz[random][0]);

        // on ajoute le texte des choix
        choix1.setText(tabQuizz[random][1]);
        choix2.setText(tabQuizz[random][2]);
        choix3.setText(tabQuizz[random][3]);
        choix4.setText(tabQuizz[random][4]);



        // on renvoit true
        return true;

    }

}
