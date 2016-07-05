package sample.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.view.View;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerStart implements Initializable{
    private Stage primaryStage;
    private Stage einsvseinsStage = new Stage();

    public ControllerStart(Stage stage){

        primaryStage = stage;
    }

    @FXML
    private Button button1;


    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        Controller1vs1 controller1vs1 = new Controller1vs1(einsvseinsStage);
        button1.setOnAction(new EventHandler<javafx.event.ActionEvent>() {

            @Override
            public void handle(javafx.event.ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/view/1vs1.fxml"));
                Parent root = null;
                try {
                    primaryStage.close();
                    fxmlLoader.setController(controller1vs1);
                    root = (Parent) fxmlLoader.load();
                    einsvseinsStage.setScene(new Scene(root));
                    einsvseinsStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
