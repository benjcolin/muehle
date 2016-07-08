package sample.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.model.Player;
import sample.model.Tournament;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Colin Hauri on 05.07.2016.
 */
public class Controller1vs1 implements Initializable {
    private Stage onevsoneStage;
    private Stage stage = new Stage();

    public Controller1vs1(Stage stage) {
        onevsoneStage = stage;
    }

    @FXML
    private TextField player1Name;
    @FXML
    private TextField player2Name;
    @FXML
    private Button startButton;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        startButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {

                Tournament players = new Tournament();

                players.addPlayer(new Player(Color.BLACK, player1Name.getText()));
                players.addPlayer(new Player(Color.WHITE, player2Name.getText()));

                ControllerGame controllerGame = new ControllerGame(players, stage);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/view/game.fxml"));
                Parent root = null;

                try {
                    onevsoneStage.close();
                    fxmlLoader.setController(controllerGame);
                    root = fxmlLoader.load();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

