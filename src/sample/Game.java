package sample;

import java.util.ArrayList;

/**
 * Created by Benjamin on 28.06.2016.
 */
public class Game {
    public static boolean player1 = true;
    public static boolean player2 = false;
    private boolean currentPlayer = player1;

    public boolean checkForMill(){
        boolean mill = false;

        //prüfen ob der currentPlayer eine NEUE Mühle gebildet hat

        return mill;
    }

    public void changeGameStatus(){

    }

    public boolean allowedToRemovePiece(Piece piece){
        boolean isAllowed = true;

        //abfrage Logik

        return isAllowed;
    }
}
