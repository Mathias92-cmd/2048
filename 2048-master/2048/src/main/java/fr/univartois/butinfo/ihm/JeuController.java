/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 *
 * (c) 2022-2023 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.ihm;

/**
 * Sample Skeleton for 'jeu2048-view.fxml' Controller Class
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Random;

public class JeuController {

    @FXML // fx:id="gridGame"
    private GridPane gridGame; // Value injected by FXMLLoader

    @FXML // fx:id="scoreText"
    private Label scoreText; // Value injected by FXMLLoader

    private static final Random rand = new Random();

    private int score = 0;

    private Label[][] tileLabels = new Label[4][4];

    private Grid grille;



    public JeuController(){
        grille = new Grid();
    }


    @FXML
    void initialize() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Label label = new Label("0");
                label.setStyle("-fx-background-color: #f3eeb;");
                label.setPrefSize(400, 400);
                label.setAlignment(Pos.CENTER);
                gridGame.add(label, j, i);
                tileLabels[i][j] = label;
            }
        }
        startGame();
    }


    @FXML
    void onDownButonClick(ActionEvent event) {
        MoveResult values = grille.moveDown();
        if(values.hasMoved() != false){
            score += values.getMergeScore();
            scoreText.setText(Integer.toString(score));
            genValuesTileLibre();
            miseAjourAffichage();
            if(grille.isBlocked()){
                gridGame.setDisable(true);
            }
        }
    }

    @FXML
    void onLeftButonClick(ActionEvent event) {
        MoveResult values = grille.moveLeft();
        if(values.hasMoved() != false){
            score += values.getMergeScore();
            scoreText.setText(Integer.toString(score));
            genValuesTileLibre();
            miseAjourAffichage();
            if(grille.isBlocked()){
                gridGame.setDisable(true);
            }
        }
    }

    @FXML
    void onResetButonClick(ActionEvent event) {
        startGame();
    }

    @FXML
    void onRightButonClick(ActionEvent event) {
        MoveResult values = grille.moveRight();
        if(values.hasMoved() != false){
            score += values.getMergeScore();
            scoreText.setText(Integer.toString(score));
            genValuesTileLibre();
            miseAjourAffichage();
            if(grille.isBlocked()){
                gridGame.setDisable(true);
            }
        }
    }

    @FXML
    void onUpButonClick(ActionEvent event) {
        MoveResult values = grille.moveUp();
        if(values.hasMoved() != false){
            score += values.getMergeScore();
            scoreText.setText(Integer.toString(score));
            genValuesTileLibre();
            miseAjourAffichage();
            if(grille.isBlocked()){
                gridGame.setDisable(true);
            }
        }
    }

    public void genValuesTileLibre(){
        List<Tile> tuilleLibre = grille.availableTiles();
        int tuileNumber = rand.nextInt(tuilleLibre.size());
        int valeur = rand.nextDouble() < 0.9 ? 2 : 4; // ? = if ternaire
        Tile tuile = tuilleLibre.get(tuileNumber);

        tuile.setValue(valeur);
    }

    public void miseAjourAffichage(){
        for(int i = 0;i < tileLabels.length; i++){
            for(int j = 0; j < tileLabels.length ; j ++ ){
                tileLabels[i][j].setText(grille.get(i,j).toString());
            }

        }
    }

    public void startGame(){
        grille.clear();
        genValuesTileLibre();
        genValuesTileLibre();
        miseAjourAffichage();
        score = 0;
        scoreText.setText(Integer.toString(score));
    }



}

