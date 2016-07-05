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

    //Prüft ob die Position von der verschoben wurde neben der neuen Position ist
    public boolean isOldPointNext(Point oldPoint, Point newPoint) {
        boolean oldPointNext = false;
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0; j < 8; j++){
                if (oldPoint == board[i][j]){
                    int oldRow = i;
                    int oldCol = j;
                }else if (newPoint == board[i][j]){
                    int newRow = i;
                    int newCol = j;
                }
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
