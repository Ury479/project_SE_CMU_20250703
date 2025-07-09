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
import javafx.scene.Group;

public class DragAndDrop extends Application {
    double mouse_x, mouse_y;  // Store mouse coordinates
    boolean isEntered;  // Track if the source is inside the target

    @Override
    public void start(Stage stage) {
        stage.setTitle("Hello Drag and Drop");
        Group root = new Group();  // Root group
        Scene scene = new Scene(root, 500, 200);
        scene.setFill(Color.WHITE);  // Set background color

        // Create two FigureGroups
        FigureGroup source = new FigureGroup(80, 50, 40, Color.LIGHTBLUE, "SOURCE");
        FigureGroup target = new FigureGroup(320, 50, 50, Color.RED, "DROP TARGET");

        root.getChildren().addAll(source.getFigure(), target.getFigure());  // Add to root
        isEntered = false;  // Initialize as false

        // Track mouse press to store coordinates
        source.getFigure().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouse_x = event.getScreenX();
                mouse_y = event.getScreenY();
            }
        });

        // Handle drag detection
        source.getFigure().setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                source.getFigure().setMouseTransparent(true);  // Make it invisible to mouse events
                source.getFigure().toFront();  // Bring to front
                source.getFigure().startFullDrag();  // Start full drag event
            }
        });

        // Handle dragging
        source.getFigure().setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double deltaX = event.getScreenX() - mouse_x;
                double deltaY = event.getScreenY() - mouse_y;
                source.setPosition(deltaX, deltaY);  // Move the source
                mouse_x = event.getScreenX();
                mouse_y = event.getScreenY();
            }
        });

        // Handle release of the dragged object
        source.getFigure().setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                source.getFigure().setMouseTransparent(false);  // Enable mouse events again

                if (isEntered) {
                    // Add source to target if released inside
                    root.getChildren().remove(source.getFigure());  // Remove from root
                    target.getFigure().getChildren().add(source.getFigure());  // Add to target
                } else {
                    source.setBackOrigin();  // Return to original position if not inside target
                }
            }
        });

        // Detect when the source enters the target area
        target.getFigure().setOnMouseDragEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                isEntered = true;
            }
        });

        // Detect when the source exits the target area
        target.getFigure().setOnMouseDragExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                isEntered = false;
            }
        });

        stage.setScene(scene);  // Set the scene to the stage
        stage.show();  // Show the stage
    }

    public static void main(String[] args) {
        Application.launch(args);  // Launch the JavaFX application
    }
}
