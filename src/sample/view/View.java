package sample.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.controller.Controller;

public class View extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Controller controller = new Controller(primaryStage);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/view/start.fxml"));
        fxmlLoader.setController(controller);
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Mühle");
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();


    }




    public static void main(String[] args) {
        launch(args);
    }
}
