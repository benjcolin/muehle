package sample.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.model.Game;
import sample.model.Piece;
import sample.model.Point;
import sample.model.Tournament;

import javax.swing.*;
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
    private int player1NumberPieces;
    private int player2NumberPieces;
    private Piece selected = new Piece(Color.WHITE);
    private Point oldPoint = new Point();

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
    @FXML
    private Text player1NumberOfPieces;
    @FXML
    private Text player2NumberOfPieces;

    public ControllerGame(Tournament tournament, Stage stage){
        this.tournament = tournament;
        this.stage = stage;
        game = new Game(tournament.getPlayers().get(0),tournament.getPlayers().get(1));
    }

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        player1Name.setText(tournament.getPlayers().get(0).getName() + " ( Schwarz )");
        player2Name.setText(tournament.getPlayers().get(1).getName() + " ( Weiss )");
        currentPlayer.setText(game.getCurrentPlayer().getName()+ " ist am Zug");

        setPoints();
        setPieces();
        actualizeScreen();

        //Button Aufgeben
        giveUp.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                stage.close();
            }
        });
    }

    private void pointHandler(MouseEvent mouseEvent) {
        boolean played = false;
        int row = 0;
        int col = 0;
        Circle c = (Circle) mouseEvent.getSource();
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0; j < 8; j++){
                if (board[i][j] == c){
                    row = i;
                    col = j;
                }
            }
        }
        if (game.getNumberPiecesPlacedCurrentPlayer() < 9){
            game.movePiece(game.getPiecesOfCurrentPlayer()[game.getNumberPiecesOnBoardCurrentPlayer()], game.getPoint(row, col));
            played = true;
        }else if (selected != null && ( game.isOldPointNext(oldPoint,game.getPoint(row,col)) || game.getNumberPiecesOnBoardCurrentPlayer() == 3)){
            if(selected.getColor() == game.getCurrentPlayer().getColor()){
                game.movePiece(selected, game.getPoint(row, col));
                played = true;
            }
        }
        if(played){
            if (game.checkForMill()){
                JOptionPane.showMessageDialog(null, "Mühle gebildet", "Titel", JOptionPane.INFORMATION_MESSAGE);
            }
            game.changePlayer();
        }
        actualizeScreen();
    }

    private void actualizeScreen() {
        player1NumberPieces = 9-game.getNumberPiecesPlacedPlayer1();
        player2NumberPieces = 9-game.getNumberPiecesPlacedPlayer2();

        player1NumberOfPieces.setText("" + player1NumberPieces);
        player2NumberOfPieces.setText("" + player2NumberPieces);

        currentPlayer.setText(game.getCurrentPlayer().getName()+ " ist am Zug");
        Point[][] pointBoard = game.getBoard();
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0; j < 8; j++){
                if (pointBoard[i][j].getPiece() != null){
                    pieces[i][j].setFill(pointBoard[i][j].getPiece().getColor());
                    pieces[i][j].setVisible(true);
                }else {
                    pieces[i][j].setVisible(false);
                }
            }
        }
    }

    private void pieceHandler(MouseEvent mouseEvent) {

        Circle c = (Circle) mouseEvent.getSource();
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0; j < 8; j++){
                if (pieces[i][j] == c){
                    selected = game.getBoard()[i][j].getPiece();
                    oldPoint = game.getPoint(i,j);
                }
            }
        }
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
            board[0][counter].setOnMouseClicked(this::pointHandler);
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
            board[0][counter].setOnMouseClicked(this::pointHandler);
            pane.getChildren().add(board[0][counter]);
            counter++;
        }
        for(int i = 0 ; i <2 ; i++){
            board[0][counter] = new Circle();
            board[0][counter].setRadius(radius);
            board[0][counter].setFill(Color.BLACK);
            board[0][counter].setLayoutX(xAxis+(150 - (i * 150)));
            board[0][counter].setLayoutY(yAxis+(2*150));
            board[0][counter].setVisible(true);
            board[0][counter].setOnMouseClicked(this::pointHandler);
            pane.getChildren().add(board[0][counter]);
            counter++;
        }

        board[0][counter] = new Circle();
        board[0][counter].setRadius(radius);
        board[0][counter].setFill(Color.BLACK);
        board[0][counter].setLayoutX(xAxis);
        board[0][counter].setLayoutY(yAxis+150);
        board[0][counter].setVisible(true);
        board[0][counter].setOnMouseClicked(this::pointHandler);
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
            board[1][counter].setOnMouseClicked(this::pointHandler);
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
            board[1][counter].setOnMouseClicked(this::pointHandler);
            pane.getChildren().add(board[1][counter]);
            counter++;
        }
        for(int i = 0 ; i <2 ; i++){
            board[1][counter] = new Circle();
            board[1][counter].setRadius(radius);
            board[1][counter].setFill(Color.BLACK);
            board[1][counter].setLayoutX(xAxis+(100 - (i*100)));
            board[1][counter].setLayoutY(yAxis+(2*100));
            board[1][counter].setVisible(true);
            board[1][counter].setOnMouseClicked(this::pointHandler);
            pane.getChildren().add(board[1][counter]);
            counter++;
        }

        board[1][counter] = new Circle();
        board[1][counter].setRadius(radius);
        board[1][counter].setFill(Color.BLACK);
        board[1][counter].setLayoutX(xAxis);
        board[1][counter].setLayoutY(yAxis+100);
        board[1][counter].setVisible(true);
        board[1][counter].setOnMouseClicked(this::pointHandler);
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
            board[2][counter].setOnMouseClicked(this::pointHandler);
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
            board[2][counter].setOnMouseClicked(this::pointHandler);
            pane.getChildren().add(board[2][counter]);
            counter++;
        }
        for(int i = 0 ; i <2 ; i++){
            board[2][counter] = new Circle();
            board[2][counter].setRadius(radius);
            board[2][counter].setFill(Color.BLACK);
            board[2][counter].setLayoutX(xAxis+(50 - (i*50)));
            board[2][counter].setLayoutY(yAxis+(2*50));
            board[2][counter].setVisible(true);
            board[2][counter].setOnMouseClicked(this::pointHandler);
            pane.getChildren().add(board[2][counter]);
            counter++;
        }

        board[2][counter] = new Circle();
        board[2][counter].setRadius(radius);
        board[2][counter].setFill(Color.BLACK);
        board[2][counter].setLayoutX(xAxis);
        board[2][counter].setLayoutY(yAxis+50);
        board[2][counter].setVisible(true);
        board[2][counter].setOnMouseClicked(this::pointHandler);
        pane.getChildren().add(board[2][counter]);
    }

    private void setPieces(){
        int xAxis = 50;
        int yAxis = 50;

        //Aussere Pieces zeichnen
        int counter = 0;
        for(int i = 0 ; i <3 ; i++){
            pieces[0][counter] = new Circle();
            pieces[0][counter].setRadius(12);
            pieces[0][counter].setLayoutX(xAxis+(i*150));
            pieces[0][counter].setLayoutY(yAxis);
            pieces[0][counter].setVisible(false);
            pieces[0][counter].setOnMouseClicked(this::pieceHandler);
            pane.getChildren().add(pieces[0][counter]);
            counter++;
        }
        for(int i = 0 ; i <2 ; i++){
            pieces[0][counter] = new Circle();
            pieces[0][counter].setRadius(12);
            pieces[0][counter].setLayoutX(xAxis+(2*150));
            pieces[0][counter].setLayoutY(yAxis+((1+i)*150));
            pieces[0][counter].setVisible(false);
            pieces[0][counter].setOnMouseClicked(this::pieceHandler);
            pane.getChildren().add(pieces[0][counter]);
            counter++;
        }
        for(int i = 0 ; i <2 ; i++){
            pieces[0][counter] = new Circle();
            pieces[0][counter].setRadius(12);
            pieces[0][counter].setLayoutX(xAxis+(150 - (i*150)));
            pieces[0][counter].setLayoutY(yAxis+(2*150));
            pieces[0][counter].setVisible(false);
            pieces[0][counter].setOnMouseClicked(this::pieceHandler);
            pane.getChildren().add(pieces[0][counter]);
            counter++;
        }

        pieces[0][counter] = new Circle();
        pieces[0][counter].setRadius(12);
        pieces[0][counter].setLayoutX(xAxis);
        pieces[0][counter].setLayoutY(yAxis+150);
        pieces[0][counter].setVisible(false);
        pieces[0][counter].setOnMouseClicked(this::pieceHandler);
        pane.getChildren().add(pieces[0][counter]);
        counter=0;

        //Mittlere Pieces zeichnen
        xAxis = 100;
        yAxis = 100;
        for(int i = 0 ; i <3 ; i++){
            pieces[1][counter] = new Circle();
            pieces[1][counter].setRadius(12);
            pieces[1][counter].setLayoutX(xAxis+(i*100));
            pieces[1][counter].setLayoutY(yAxis);
            pieces[1][counter].setVisible(false);
            pieces[1][counter].setOnMouseClicked(this::pieceHandler);
            pane.getChildren().add(pieces[1][counter]);
            counter++;
        }
        for(int i = 0 ; i <2 ; i++){
            pieces[1][counter] = new Circle();
            pieces[1][counter].setRadius(12);
            pieces[1][counter].setLayoutX(xAxis+(2*100));
            pieces[1][counter].setLayoutY(yAxis+((1+i)*100));
            pieces[1][counter].setVisible(false);
            pieces[1][counter].setOnMouseClicked(this::pieceHandler);
            pane.getChildren().add(pieces[1][counter]);
            counter++;
        }
        for(int i = 0 ; i <2 ; i++){
            pieces[1][counter] = new Circle();
            pieces[1][counter].setRadius(12);
            pieces[1][counter].setLayoutX(xAxis+(100 - (i*100)));
            pieces[1][counter].setLayoutY(yAxis+(2*100));
            pieces[1][counter].setVisible(false);
            pieces[1][counter].setOnMouseClicked(this::pieceHandler);
            pane.getChildren().add(pieces[1][counter]);
            counter++;
        }

        pieces[1][counter] = new Circle();
        pieces[1][counter].setRadius(12);
        pieces[1][counter].setLayoutX(xAxis);
        pieces[1][counter].setLayoutY(yAxis+100);
        pieces[1][counter].setVisible(false);
        pieces[1][counter].setOnMouseClicked(this::pieceHandler);
        pane.getChildren().add(pieces[1][counter]);
        counter=0;

        //innere Pieces zeichnen
        xAxis = 150;
        yAxis = 150;
        for(int i = 0 ; i <3 ; i++){
            pieces[2][counter] = new Circle();
            pieces[2][counter].setRadius(12);
            pieces[2][counter].setLayoutX(xAxis+(i*50));
            pieces[2][counter].setLayoutY(yAxis);
            pieces[2][counter].setVisible(false);
            pieces[2][counter].setOnMouseClicked(this::pieceHandler);
            pane.getChildren().add(pieces[2][counter]);
            counter++;
        }
        for(int i = 0 ; i <2 ; i++){
            pieces[2][counter] = new Circle();
            pieces[2][counter].setRadius(12);
            pieces[2][counter].setLayoutX(xAxis+(2*50));
            pieces[2][counter].setLayoutY(yAxis+((1+i)*50));
            pieces[2][counter].setVisible(false);
            pieces[2][counter].setOnMouseClicked(this::pieceHandler);
            pane.getChildren().add(pieces[2][counter]);
            counter++;
        }
        for(int i = 0 ; i <2 ; i++){
            pieces[2][counter] = new Circle();
            pieces[2][counter].setRadius(12);
            pieces[2][counter].setLayoutX(xAxis+(50 - (i*50)));
            pieces[2][counter].setLayoutY(yAxis+(2*50));
            pieces[2][counter].setVisible(false);
            pieces[2][counter].setOnMouseClicked(this::pieceHandler);
            pane.getChildren().add(pieces[2][counter]);
            counter++;
        }

        pieces[2][counter] = new Circle();
        pieces[2][counter].setRadius(12);
        pieces[2][counter].setLayoutX(xAxis);
        pieces[2][counter].setLayoutY(yAxis+50);
        pieces[2][counter].setVisible(false);
        pieces[2][counter].setOnMouseClicked(this::pieceHandler);
        pane.getChildren().add(pieces[2][counter]);
    }
}
