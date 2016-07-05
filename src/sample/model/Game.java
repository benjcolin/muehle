package sample.model;

/**
 * Created by Benjamin on 28.06.2016.
 */
public class Game {
    public Player player1;
    public Player player2;
    private Player currentPlayer = player1;
    private Point[][] board = new Point[3][8];

    public boolean checkForMill() {
        boolean mill = false;
        //Prüfen ob der currentPlayer eine NEUE Mühle gebildet hat

        //CHECK SECTORS
        checkSector(0,1,2,mill); //TOP
        checkSector(5,6,7,mill); //BOTTOM
        checkSector(0,3,5,mill); //LEFT
        checkSector(2,4,7,mill); //RIGHT
        //CHECK SIDE MILLS
        checkSideMill(1, mill);
        checkSideMill(3, mill);
        checkSideMill(4, mill);
        checkSideMill(6, mill);
        return mill;
    }

    public void changeGameStatus() {

    }

    public boolean allowedToRemovePiece(Piece piece) {
        boolean isAllowed = true;

        //abfrage Logik

        return isAllowed;
    }

    //Prüft ob die Position von der verschoben wurde neben der neuen Position ist
    public boolean isOldPointNext(Point oldPoint, Point newPoint) {
        boolean oldPointNext = true;

        return oldPointNext;
    }

    //TOP = 1, LEFT = 3, RIGHT = 4, BOTTOM = 6
    private boolean checkSideMill(int side, boolean mill){
        for (int i = 0; i < 3; i++) {
            if(board[i][side].getPiece().getColor() == currentPlayer.getColor()
                    && board[i][side].getPiece().getColor() == currentPlayer.getColor()
                    && board[i][side].getPiece().getColor() == currentPlayer.getColor()){
                mill = true;
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
