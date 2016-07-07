package sample.model;

/**
 * Created by Benjamin on 07.07.2016.
 */
public class Mill {
    private int aRow;
    private int aCol;
    private int bRow;
    private int bCol;
    private int cRow;
    private int cCol;

    public Mill(int aRow, int aCol, int bRow, int bCol, int cRow, int cCol) {
        this.aRow = aRow;
        this.aCol = aCol;
        this.bRow = bRow;
        this.bCol = bCol;
        this.cRow = cRow;
        this.cCol = cCol;
    }

    public int getaRow() {
        return aRow;
    }

    public int getaCol() {
        return aCol;
    }

    public int getbRow() {
        return bRow;
    }

    public int getbCol() {
        return bCol;
    }

    public int getcRow() {
        return cRow;
    }

    public int getcCol() {
        return cCol;
    }
}
