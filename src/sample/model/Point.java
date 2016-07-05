package sample.model;

/**
 * Created by Benjamin on 28.06.2016.
 */
public class Point {
    private Piece piece;

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void removePiece() {
        piece = null;
    }
}
