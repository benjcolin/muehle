package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    Tournament tournament;
    Game game;

    @FXML
    private Text player1Name;
    @FXML
    private Text player2Name;
    @FXML
    private Text currentPlayer;



    public ControllerGame(Tournament tournament){

        this.tournament = tournament;
        game = new Game(tournament.getPlayers().get(0),tournament.getPlayers().get(1));



    }

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        player1Name.setText(tournament.getPlayers().get(0).getName() + " ( Schwarz )");
        player2Name.setText(tournament.getPlayers().get(1).getName().toString() + " ( Weiss )");
        currentPlayer.setText(game.getCurrentPlayer().getName()+ " ist am Zug");
    }



}
