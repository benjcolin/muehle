package sample.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/start.fxml"));
        primaryStage.setTitle("Mühle");
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
