package com.beginsecure.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button; // Import for Button
import javafx.event.ActionEvent; // Import for ActionEvent
import javafx.event.EventHandler; // Import for EventHandler
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Button btn = new Button();
        btn.setText("Hi");
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                System.out.println("Are you ok?");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root,300,275);

        scene.heightProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observableValue,
                            Number oldSceneHeight,Number newSceneHeight){
            System.out.println("Height:" + newSceneHeight);
        }
    });

        stage.setScene(scene);
        stage.setTitle("Hello");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}