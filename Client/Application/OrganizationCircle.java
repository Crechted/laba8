package Client.Application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.awt.Color;

public class OrganizationCircle extends Circle {
    private Canvas canvas;
    private Color color;

    public OrganizationCircle(double x, double y, double r, Color color, Canvas c){
        super(x, y, r);
        this.color = color;
        this.canvas = c;
        setFill(new javafx.scene.paint.Color((double)color.getRed()/256, (double)color.getGreen()/256, (double)color.getBlue()/256, 1));
    }

    public void draw(){

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(new javafx.scene.paint.Color((double)color.getRed()/256, (double)color.getGreen()/256, (double)color.getBlue()/256, 1));
        gc.fillOval(getCenterX()-getRadius(), getCenterY()-getRadius(), getRadius()*2, getRadius()*2);


    }
}
