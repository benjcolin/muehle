package sample.model;

import javafx.scene.paint.Color;

import javax.swing.*;
import java.util.Arrays;

/**
 * Created by Benjamin on 28.06.2016.
 */
public class Game {
    public Player player1;
    public Player player2;
    private Player currentPlayer;
    private Piece[] piecesOfPlayer1 = new Piece[9];
    private Piece[] piecesOfPlayer2 = new Piece[9];

    private int numberPiecesPlacedPlayer1 = 0;
    private int numberPiecesPlacedPlayer2 = 0;
    private Point[][] board = new Point[3][8];

    public Game(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = player1;
        for(int i = 0; i < 9 ; i++){
            piecesOfPlayer1[i] = new Piece(Color.BLACK);
        }
        for(int i = 0; i < 9 ; i++){
            piecesOfPlayer2[i] = new Piece(Color.WHITE);
        }
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 8; j++){
                board[i][j] = new Point();
            }
        }
    }

    public int getNumberPiecesOnBoardCurrentPlayer(){
        if(currentPlayer == player1){
            return getNumberPiecesOnBoardPlayer1();
        }else{
            return getNumberPiecesOnBoardPlayer2();
        }
    }

    public int getNumberPiecesOnBoardPlayer1(){
        int numberOfPiecesFromPlayer1onBoard = 0;
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0; j < 8; j++){
                if (board[i][j].getPiece() != null){
                    if (board[i][j].getPiece().getColor() == Color.BLACK){
                        numberOfPiecesFromPlayer1onBoard++;
                    }
                }
            }
        }
        return numberOfPiecesFromPlayer1onBoard;
    }

    public int getNumberPiecesOnBoardPlayer2(){
        int numberOfPiecesFromPlayer2onBoard = 0;
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0; j < 8; j++){
                if (board[i][j].getPiece() != null) {
                    if (board[i][j].getPiece().getColor() == Color.WHITE) {
                        numberOfPiecesFromPlayer2onBoard++;
                    }
                }
            }
        }
        return numberOfPiecesFromPlayer2onBoard;
    }

    //Prüfen ob der currentPlayer eine NEUE Mühle gebildet hat
    public boolean checkForMill() {
        boolean mill = false;
        //CHECK SECTORS
        mill = checkSector(0,1,2,mill); //TOP
        mill = checkSector(2,3,4,mill); //RIGHT
        mill = checkSector(4,5,6,mill); //BOTTOM
        mill = checkSector(6,7,0,mill); //LEFT
        //CHECK SIDE MILLS
        mill = checkSideMill(mill);

        return mill;
    }

    public void changeGameStatus() {

    }

    //Verschiebt den Spielstein
    public void movePiece(Piece piece, Point point){
        if(getNumberPiecesOnBoardCurrentPlayer() == 9){
            for(int i = 0 ; i < 3 ; i++){
                for(int j = 0; j < 8; j++){
                    if (board[i][j].getPiece() == piece){
                        board[i][j].removePiece();
                    }
                }
            }
        }else{
            if (currentPlayer == player1){
                numberPiecesPlacedPlayer1++;
            }else{
                numberPiecesPlacedPlayer2++;
            }
        }
        point.setPiece(piece);
    }

    private void checkForWin(){
        if(getNumberPiecesOnBoardPlayer1() < 3){
            JOptionPane.showMessageDialog(null, player2.getName(), "Sieg", JOptionPane.INFORMATION_MESSAGE);
        }else if(getNumberPiecesOnBoardPlayer2() < 3){
            JOptionPane.showMessageDialog(null, player1.getName(), "Sieg", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public boolean allowedToRemovePiece(Piece piece){
        if (piece.getColor() != currentPlayer.getColor()){
            return true;
        }else{
            return false;
        }
    }

    //Prüft ob die Position, von der verschoben wurde neben der neuen Position ist
    public boolean isOldPointNext(Point oldPoint, Point newPoint) {
        int oldRow = 0;
        int oldCol = 0;
        int newRow = 0;
        int newCol = 0;
        boolean oldPointNext = false;
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0; j < 8; j++){
                if (oldPoint == board[i][j]){
                    oldRow = i;
                    oldCol = j;
                }else if (newPoint == board[i][j]){
                    newRow = i;
                    newCol = j;
                }
            }
        }

        if(Math.abs(oldRow - newRow) == 0){
            if (Math.abs(oldCol - newCol) == 1 || Math.abs(oldCol - newCol) == 7 ){
                //Auf dem gleichen Quadrat und direkt nebeneinander
                oldPointNext = true;
            }
        }else if(Math.abs(oldRow - newRow) == 1){
            if (oldCol == newCol && oldCol % 2 == 1){
                //Auf verschiedenen Quadraten jedoch genug nahe und bei einem "Übergang"
                oldPointNext = true;
            }
        }
        return oldPointNext;
    }

    //TOP = 1, LEFT = 3, RIGHT = 5, BOTTOM = 7
    private boolean checkSideMill(boolean mill){
        for(int side = 1; side < 8; side+=2){
            if(board[0][side].getPiece() != null && board[1][side].getPiece() != null && board[2][side].getPiece() != null) {
                if (board[0][side].getPiece().getColor() == currentPlayer.getColor()
                        && board[1][side].getPiece().getColor() == currentPlayer.getColor()
                        && board[2][side].getPiece().getColor() == currentPlayer.getColor()) {
                    mill = true;
                }
            }
        }
        return mill;
    }

    private boolean checkSector(int a, int b, int c, boolean mill){
        for (int i = 0; i < 3; i++) {
            if(board[i][a].getPiece() != null && board[i][b].getPiece() != null && board[i][c].getPiece() != null){
                if (board[i][a].getPiece().getColor() == currentPlayer.getColor()
                        && board[i][b].getPiece().getColor() == currentPlayer.getColor()
                        && board[i][c].getPiece().getColor() == currentPlayer.getColor()) {
                    mill = true;
                }
            }
        }
        return mill;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getNumberPiecesPlacedCurrentPlayer() {
        if (currentPlayer == player1){
            return numberPiecesPlacedPlayer1;
        }else {
            return numberPiecesPlacedPlayer2;
        }
    }

    public Piece[] getPiecesOfCurrentPlayer() {

        if (currentPlayer == player1){
            return piecesOfPlayer1;
        }else {
            return piecesOfPlayer2;
        }
    }

    public Point getPoint(int row, int col){
        return board[row][col];
    }

    public void changePlayer() {
        if (currentPlayer == player1){
            currentPlayer = player2;
        }else {
            currentPlayer = player1;
        }
    }

    public Point[][] getBoard() {
        return board;
    }

    public int getNumberPiecesPlacedPlayer1() {
        return numberPiecesPlacedPlayer1;
    }

    public int getNumberPiecesPlacedPlayer2() {
        return numberPiecesPlacedPlayer2;
    }
}
