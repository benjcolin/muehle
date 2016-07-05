package sample.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sample.view.View;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    private Stage primaryStage;


    public Controller(Stage stage){
        primaryStage = stage;
    }

    @FXML
    private Button button1;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        button1.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/view/1vs1.fxml"));
                Parent root = null;
                try {
                    primaryStage.close();
                    root = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    Pane pane = new Pane();
                    stage.show();


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }



}
