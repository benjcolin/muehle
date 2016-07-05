package sample.model;

/**
 * Created by Benjamin on 28.06.2016.
 */
public class Point {

    private Boolean occupied;
    private Piece piece;

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    //Verschiebt den Spielstein
    public void movePiece(Piece piece){

    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
