package sample.view;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

/**
 * Created by Stuber Gregor on 05.07.2016.
 */
public class GameView {

    Circle[] circles = new Circle[24];

    public Pane controllStones(Pane pane){
        pane.setMaxWidth(500);
        pane.setMaxHeight(500);
        Random rand = new Random();
        for(Circle c : circles) {
            c = new Circle();
            double r = rand.nextDouble();
            double g = rand.nextDouble();
            double b = rand.nextDouble();
            c.setFill(new Color(r, g, b, 1));
            c.setRadius(50);
            c.setLayoutX(50);
            c.setLayoutY(rand.nextInt(1000));
            c.setVisible(true);
            c.setOnMouseClicked(this::handler);
            pane.getChildren().add(c);
        }

        return pane;
    }

    public void handler(MouseEvent event) {
        Circle c = (Circle) event.getSource();
        c.setVisible(false);
    }

}