package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.model.Game;
import sample.model.Player;
import sample.model.Tournament;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Colin Hauri on 05.07.2016.
 */
public class ControllerGame implements Initializable{

    private Tournament tournament;
    private Game game;
    private Stage stage;
    private Circle[][] board = new Circle[3][8];

    @FXML
    private Text player1Name;
    @FXML
    private Text player2Name;
    @FXML
    private Text currentPlayer;
    @FXML
    private Button giveUp;
    @FXML
    private Pane pane;

    public ControllerGame(Tournament tournament, Stage stage){
        this.tournament = tournament;
        this.stage = stage;
        game = new Game(tournament.getPlayers().get(0),tournament.getPlayers().get(1));
    }

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        int xAxis = 50;
        int yAxis = 50;

        player1Name.setText(tournament.getPlayers().get(0).getName() + " ( Schwarz )");
        player2Name.setText(tournament.getPlayers().get(1).getName().toString() + " ( Weiss )");
        currentPlayer.setText(game.getCurrentPlayer().getName()+ " ist am Zug");

        //Points zeichnen
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 8; j++){
                board[i][j] = new Circle();
                board[i][j].setRadius(12);
                board[i][j].setFill(Color.BLACK);
                board[i][j].setLayoutX(xAxis);
                board[i][j].setLayoutY(yAxis);
                board[i][j].setVisible(true);
                board[i][j].setOnMouseClicked(this::pointHandler);
                pane.getChildren().add(board[i][j]);
            }
        }

        //Button Aufgeben
        giveUp.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                stage.close();
            }
        });

    }

    private void pointHandler(MouseEvent mouseEvent) {
        Circle c = (Circle) mouseEvent.getSource();
        c.setVisible(false);
    }
}
