package sample.model;

import java.awt.*;

/**
 * Created by Benjamin on 28.06.2016.
 */
public class Player {

    private String name;
    private Color color;

    public Player(Color color, String name){
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
