package sample.model;
/**
 * Created by Benjamin on 28.06.2016.
 */
public class Game {
    public Player player1;
    public Player player2;
    private Player currentPlayer = player1;
    private Point[][] board = new Point[3][8];

    //Prüfen ob der currentPlayer eine NEUE Mühle gebildet hat
    public boolean checkForMill() {
        boolean mill = false;
        //CHECK SECTORS
        checkSector(0,1,2,mill); //TOP
        checkSector(4,5,6,mill); //BOTTOM
        checkSector(6,7,0,mill); //LEFT
        checkSector(2,3,4,mill); //RIGHT
        //CHECK SIDE MILLS
        checkSideMill(mill);
        return mill;
    }

    public void changeGameStatus() {

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
}
