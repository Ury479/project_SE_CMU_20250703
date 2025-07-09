package com.beginsecure.demo;

// Required JavaFX imports
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class FigureGroup {
    private StackPane figure;
    private double origin_x,origin_y;

    public FigureGroup(int x,int y,int radius,Color color,String str){
        Circle circle = new Circle(radius,color);
        Text text = new Text(str);
        this.figure = new StackPane(circle,text);

        this.figure.setLayoutX(x);
        this.figure.setLayoutY(y);
        this.origin_x = x;
        this.origin_y = y;
    }

    public void setBackOrigin(){
        this.figure.setLayoutX(origin_x);
        this.figure.setLayoutY(origin_y);
    }

    public void setPosition(double x,double y){
        this.figure.setLayoutX(this.figure.getLayoutX() + x);
        this.figure.setLayoutY(this.figure.getLayoutY() + y);
    }

    public StackPane getFigure() { return figure; }
}
