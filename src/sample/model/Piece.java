package sample.model;

import javafx.scene.paint.Color;

/**
 * Created by Benjamin on 28.06.2016.
 */
public class Piece {
    private Boolean selected;
    private Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
