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

public class CustomizedEvent extends Application{
    @Override
    public void start(Stage stage){
        FlowPane pane = new FlowPane();
        StackPane circle1 = createMonitoredCircle();
        StackPane circle2 = createMonitoredCircle();

        pane.getChildren().addAll(circle1);
        pane.getChildren().addAll(circle1,circle2);
        Scene scene = new Scene(pane);
        stage.setTitle("Customized Events");
        stage.setScene(scene);
        stage.show();
    }

    private StackPane createMonitoredCircle(){
        StackPane pane = new StackPane();
        VBox layout = new VBox();

        Label reporter = new Label("Outside Circle");
        Circle circle = new Circle(30);

        circle.setOnMouseMoved(new EventHandler<MouseEvent>(){
            @Override
              public void handle(MouseEvent event){
                reporter.setText("(x:" + event.getX() + ",y:" + event.getY() + ")");
            }
        });

        circle.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                reporter.setText("Outside Circle");
            }
        });

        layout.getChildren().setAll(circle,reporter);
        pane.getChildren().add(layout);
        return pane;
    }
    public static void main(String[] args) { launch(args); }
}
