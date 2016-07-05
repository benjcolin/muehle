package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Colin Hauri on 05.07.2016.
 */
public class Controller1vs1 implements Initializable {
    private Stage einsvseinsStage;

    public Controller1vs1(Stage stage){

        einsvseinsStage = stage;
    }

    @FXML
    private TextField spieler1Name;
    @FXML
    private TextField spieler2Name;


    @FXML
    private Button startButton;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        startButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/view/game.fxml"));
                Parent root = null;
                try {
                    einsvseinsStage.close();
                    root = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

