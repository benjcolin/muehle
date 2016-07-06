package sample.model;

import java.awt.*;

/**
 * Created by Benjamin on 28.06.2016.
 */
public class Game {
    public Player player1;
    public Player player2;
    private int numberOfPicesFromPlayer1onBoard;
    private int numberOfPicesFromPlayer2onBoard;

    private Player currentPlayer;
    private Piece[] piecesOfPlayer1 = new Piece[9];
    private Piece[] piecesOfPlayer2 = new Piece[9];

    private Point[][] board = new Point[3][8];

    public Game(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = player1;
    }

    public void getPiecesFromPlayer1(Piece piece){
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0; j < 8; j++){
                if (board[i][j].getPiece().getColor() == Color.black){
                    numberOfPicesFromPlayer1onBoard++;
                }
            }
        }

    }

    public void getPiecesFromPlayer2(Piece piece){
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0; j < 8; j++){
                if (board[i][j].getPiece().getColor() == Color.white){
                    numberOfPicesFromPlayer2onBoard++;
                }
            }
        }


    }

    //Prüfen ob der currentPlayer eine NEUE Mühle gebildet hat
    public boolean checkForMill() {
        boolean mill = false;
        //CHECK SECTORS
        checkSector(0,1,2,mill); //TOP
        checkSector(2,3,4,mill); //RIGHT
        checkSector(4,5,6,mill); //BOTTOM
        checkSector(6,7,0,mill); //LEFT
        //CHECK SIDE MILLS
        checkSideMill(mill);
        return mill;
    }

    public void changeGameStatus() {

    }

    //Verschiebt den Spielstein
    public void movePiece(Piece piece, Point point){
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0; j < 8; j++){
                if (board[i][j].getPiece() == piece){
                    board[i][j].removePiece();
                }
            }
        }
        point.setPiece(piece);
    }

    public boolean allowedToRemovePiece(Piece piece) {
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
            if (Math.abs(oldCol - oldRow) == 1 || Math.abs(oldCol - oldRow) == 7 ){
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
            for (int i = 0; i < 3; i++) {
                if(board[i][side].getPiece().getColor() == currentPlayer.getColor()
                        && board[i][side].getPiece().getColor() == currentPlayer.getColor()
                        && board[i][side].getPiece().getColor() == currentPlayer.getColor()){
                    mill = true;
                }
            }
        }
        return mill;
    }

    private boolean checkSector(int a, int b, int c, boolean mill){
        for (int i = 0; i < 3; i++) {
            if (board[i][a].getPiece().getColor() == currentPlayer.getColor()
                    && board[i][b].getPiece().getColor() == currentPlayer.getColor()
                    && board[i][c].getPiece().getColor() == currentPlayer.getColor()) {
                mill = true;
            }
        }
        return mill;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
