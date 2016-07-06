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
    private Circle[][] pieces = new Circle[3][8];

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
        player1Name.setText(tournament.getPlayers().get(0).getName() + " ( Schwarz )");
        player2Name.setText(tournament.getPlayers().get(1).getName() + " ( Weiss )");
        currentPlayer.setText(game.getCurrentPlayer().getName()+ " ist am Zug");

        setPieces();
        setPoints();

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
        //c.setVisible(false);
    }

    private void setPoints(){
        int xAxis = 50;
        int yAxis = 50;
        int radius = 7;

        //Aussere Points zeichnen
        int counter = 0;
        for(int i = 0 ; i <3 ; i++){
            board[0][counter] = new Circle();
            board[0][counter].setRadius(radius);
            board[0][counter].setFill(Color.BLACK);
            board[0][counter].setLayoutX(xAxis+(i*150));
            board[0][counter].setLayoutY(yAxis);
            board[0][counter].setVisible(true);
            pane.getChildren().add(board[0][counter]);
            counter++;
        }
        for(int i = 0 ; i <2 ; i++){
            board[0][counter] = new Circle();
            board[0][counter].setRadius(radius);
            board[0][counter].setFill(Color.BLACK);
            board[0][counter].setLayoutX(xAxis+(2*150));
            board[0][counter].setLayoutY(yAxis+((1+i)*150));
            board[0][counter].setVisible(true);
            pane.getChildren().add(board[0][counter]);
            counter++;
        }
        for(int i = 0 ; i <2 ; i++){
            board[0][counter] = new Circle();
            board[0][counter].setRadius(radius);
            board[0][counter].setFill(Color.BLACK);
            board[0][counter].setLayoutX(xAxis+(i*150));
            board[0][counter].setLayoutY(yAxis+(2*150));
            board[0][counter].setVisible(true);
            pane.getChildren().add(board[0][counter]);
            counter++;
        }

        board[0][counter] = new Circle();
        board[0][counter].setRadius(radius);
        board[0][counter].setFill(Color.BLACK);
        board[0][counter].setLayoutX(xAxis);
        board[0][counter].setLayoutY(yAxis+150);
        board[0][counter].setVisible(true);
        pane.getChildren().add(board[0][counter]);
        counter=0;

        //Mittlere Points zeichnen
        xAxis = 100;
        yAxis = 100;
        for(int i = 0 ; i <3 ; i++){
            board[1][counter] = new Circle();
            board[1][counter].setRadius(radius);
            board[1][counter].setFill(Color.BLACK);
            board[1][counter].setLayoutX(xAxis+(i*100));
            board[1][counter].setLayoutY(yAxis);
            board[1][counter].setVisible(true);
            pane.getChildren().add(board[1][counter]);
            counter++;
        }
        for(int i = 0 ; i <2 ; i++){
            board[1][counter] = new Circle();
            board[1][counter].setRadius(radius);
            board[1][counter].setFill(Color.BLACK);
            board[1][counter].setLayoutX(xAxis+(2*100));
            board[1][counter].setLayoutY(yAxis+((1+i)*100));
            board[1][counter].setVisible(true);
            pane.getChildren().add(board[1][counter]);
            counter++;
        }
        for(int i = 0 ; i <2 ; i++){
            board[1][counter] = new Circle();
            board[1][counter].setRadius(radius);
            board[1][counter].setFill(Color.BLACK);
            board[1][counter].setLayoutX(xAxis+(i*100));
            board[1][counter].setLayoutY(yAxis+(2*100));
            board[1][counter].setVisible(true);
            pane.getChildren().add(board[1][counter]);
            counter++;
        }

        board[1][counter] = new Circle();
        board[1][counter].setRadius(radius);
        board[1][counter].setFill(Color.BLACK);
        board[1][counter].setLayoutX(xAxis);
        board[1][counter].setLayoutY(yAxis+100);
        board[1][counter].setVisible(true);
        pane.getChildren().add(board[1][counter]);
        counter=0;

        //innere Points zeichnen
        xAxis = 150;
        yAxis = 150;
        for(int i = 0 ; i <3 ; i++){
            board[2][counter] = new Circle();
            board[2][counter].setRadius(radius);
            board[2][counter].setFill(Color.BLACK);
            board[2][counter].setLayoutX(xAxis+(i*50));
            board[2][counter].setLayoutY(yAxis);
            board[2][counter].setVisible(true);
            pane.getChildren().add(board[2][counter]);
            counter++;
        }
        for(int i = 0 ; i <2 ; i++){
            board[2][counter] = new Circle();
            board[2][counter].setRadius(radius);
            board[2][counter].setFill(Color.BLACK);
            board[2][counter].setLayoutX(xAxis+(2*50));
            board[2][counter].setLayoutY(yAxis+((1+i)*50));
            board[2][counter].setVisible(true);
            pane.getChildren().add(board[2][counter]);
            counter++;
        }
        for(int i = 0 ; i <2 ; i++){
            board[2][counter] = new Circle();
            board[2][counter].setRadius(radius);
            board[2][counter].setFill(Color.BLACK);
            board[2][counter].setLayoutX(xAxis+(i*50));
            board[2][counter].setLayoutY(yAxis+(2*50));
            board[2][counter].setVisible(true);
            pane.getChildren().add(board[2][counter]);
            counter++;
        }

        board[2][counter] = new Circle();
        board[2][counter].setRadius(radius);
        board[2][counter].setFill(Color.BLACK);
        board[2][counter].setLayoutX(xAxis);
        board[2][counter].setLayoutY(yAxis+50);
        board[2][counter].setVisible(true);
        pane.getChildren().add(board[2][counter]);
        counter=0;
    }

    private void setPieces(){
        int xAxis = 50;
        int yAxis = 50;

        //Aussere Points zeichnen
        int counter = 0;
        for(int i = 0 ; i <3 ; i++){
            pieces[0][counter] = new Circle();
            pieces[0][counter].setRadius(12);
            pieces[0][counter].setFill(Color.BLACK);
            pieces[0][counter].setLayoutX(xAxis+(i*150));
            pieces[0][counter].setLayoutY(yAxis);
            pieces[0][counter].setVisible(false);
            pieces[0][counter].setOnMouseClicked(this::pointHandler);
            pane.getChildren().add(pieces[0][counter]);
            counter++;
        }
        for(int i = 0 ; i <2 ; i++){
            pieces[0][counter] = new Circle();
            pieces[0][counter].setRadius(12);
            pieces[0][counter].setFill(Color.BLACK);
            pieces[0][counter].setLayoutX(xAxis+(2*150));
            pieces[0][counter].setLayoutY(yAxis+((1+i)*150));
            pieces[0][counter].setVisible(false);
            pieces[0][counter].setOnMouseClicked(this::pointHandler);
            pane.getChildren().add(pieces[0][counter]);
            counter++;
        }
        for(int i = 0 ; i <2 ; i++){
            pieces[0][counter] = new Circle();
            pieces[0][counter].setRadius(12);
            pieces[0][counter].setFill(Color.BLACK);
            pieces[0][counter].setLayoutX(xAxis+(i*150));
            pieces[0][counter].setLayoutY(yAxis+(2*150));
            pieces[0][counter].setVisible(false);
            pieces[0][counter].setOnMouseClicked(this::pointHandler);
            pane.getChildren().add(pieces[0][counter]);
            counter++;
        }

        pieces[0][counter] = new Circle();
        pieces[0][counter].setRadius(12);
        pieces[0][counter].setFill(Color.BLACK);
        pieces[0][counter].setLayoutX(xAxis);
        pieces[0][counter].setLayoutY(yAxis+150);
        pieces[0][counter].setVisible(false);
        pieces[0][counter].setOnMouseClicked(this::pointHandler);
        pane.getChildren().add(pieces[0][counter]);
        counter=0;

        //Mittlere Points zeichnen
        xAxis = 100;
        yAxis = 100;
        for(int i = 0 ; i <3 ; i++){
            pieces[1][counter] = new Circle();
            pieces[1][counter].setRadius(12);
            pieces[1][counter].setFill(Color.BLACK);
            pieces[1][counter].setLayoutX(xAxis+(i*100));
            pieces[1][counter].setLayoutY(yAxis);
            pieces[1][counter].setVisible(false);
            pieces[1][counter].setOnMouseClicked(this::pointHandler);
            pane.getChildren().add(pieces[1][counter]);
            counter++;
        }
        for(int i = 0 ; i <2 ; i++){
            pieces[1][counter] = new Circle();
            pieces[1][counter].setRadius(12);
            pieces[1][counter].setFill(Color.BLACK);
            pieces[1][counter].setLayoutX(xAxis+(2*100));
            pieces[1][counter].setLayoutY(yAxis+((1+i)*100));
            pieces[1][counter].setVisible(false);
            pieces[1][counter].setOnMouseClicked(this::pointHandler);
            pane.getChildren().add(pieces[1][counter]);
            counter++;
        }
        for(int i = 0 ; i <2 ; i++){
            pieces[1][counter] = new Circle();
            pieces[1][counter].setRadius(12);
            pieces[1][counter].setFill(Color.BLACK);
            pieces[1][counter].setLayoutX(xAxis+(i*100));
            pieces[1][counter].setLayoutY(yAxis+(2*100));
            pieces[1][counter].setVisible(false);
            pieces[1][counter].setOnMouseClicked(this::pointHandler);
            pane.getChildren().add(pieces[1][counter]);
            counter++;
        }

        pieces[1][counter] = new Circle();
        pieces[1][counter].setRadius(12);
        pieces[1][counter].setFill(Color.BLACK);
        pieces[1][counter].setLayoutX(xAxis);
        pieces[1][counter].setLayoutY(yAxis+100);
        pieces[1][counter].setVisible(false);
        pieces[1][counter].setOnMouseClicked(this::pointHandler);
        pane.getChildren().add(pieces[1][counter]);
        counter=0;

        //innere Points zeichnen
        xAxis = 150;
        yAxis = 150;
        for(int i = 0 ; i <3 ; i++){
            pieces[2][counter] = new Circle();
            pieces[2][counter].setRadius(12);
            pieces[2][counter].setFill(Color.BLACK);
            pieces[2][counter].setLayoutX(xAxis+(i*50));
            pieces[2][counter].setLayoutY(yAxis);
            pieces[2][counter].setVisible(false);
            pieces[2][counter].setOnMouseClicked(this::pointHandler);
            pane.getChildren().add(pieces[2][counter]);
            counter++;
        }
        for(int i = 0 ; i <2 ; i++){
            pieces[2][counter] = new Circle();
            pieces[2][counter].setRadius(12);
            pieces[2][counter].setFill(Color.BLACK);
            pieces[2][counter].setLayoutX(xAxis+(2*50));
            pieces[2][counter].setLayoutY(yAxis+((1+i)*50));
            pieces[2][counter].setVisible(false);
            pieces[2][counter].setOnMouseClicked(this::pointHandler);
            pane.getChildren().add(pieces[2][counter]);
            counter++;
        }
        for(int i = 0 ; i <2 ; i++){
            pieces[2][counter] = new Circle();
            pieces[2][counter].setRadius(12);
            pieces[2][counter].setFill(Color.BLACK);
            pieces[2][counter].setLayoutX(xAxis+(i*50));
            pieces[2][counter].setLayoutY(yAxis+(2*50));
            pieces[2][counter].setVisible(false);
            pieces[2][counter].setOnMouseClicked(this::pointHandler);
            pane.getChildren().add(pieces[2][counter]);
            counter++;
        }

        pieces[2][counter] = new Circle();
        pieces[2][counter].setRadius(12);
        pieces[2][counter].setFill(Color.BLACK);
        pieces[2][counter].setLayoutX(xAxis);
        pieces[2][counter].setLayoutY(yAxis+50);
        pieces[2][counter].setVisible(false);
        pieces[2][counter].setOnMouseClicked(this::pointHandler);
        pane.getChildren().add(pieces[2][counter]);
        counter=0;
    }
}
