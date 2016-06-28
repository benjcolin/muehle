package sample;

/**
 * Created by Benjamin on 28.06.2016.
 */
public class Point {

    private Boolean occupied;

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    //Prüft ob die Position von der verschoben wurde neben der neuen Position ist
    public boolean isOldPointNext(Point oldPoint){
        boolean oldPointNext = true;

        //abfrage Logik

        return oldPointNext;
    }

    //Verschiebt den Spielstein
    public void movePiece(Piece piece){

    }

}
