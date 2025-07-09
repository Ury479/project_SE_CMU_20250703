package com.beginsecure.demo;

// 必要的 JavaFX 导入
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.EventHandler;

public class KeyboardEventExample extends Application {
    public KeyboardEventExample() {
        super();
    }

    @Override
    public void start(Stage stage){
        stage.setTitle("KeyBoard Event Example");
        Group root = new Group();
        Scene scene = new Scene(root,500,200);
        scene.setFill(Color.WHITE);
        FigureGroup obj = new FigureGroup(200,50,50,Color.CORAL,"FIGURE");

        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){
                if(event.getCode() == KeyCode.UP){
                    obj.setPosition(0,-5);
                }else if(event.getCode() == KeyCode.DOWN){
                    obj.setPosition(0,5);
                }else if(event.getCode() == KeyCode.LEFT){
                    obj.setPosition(-5,0);
                }else if(event.getCode() == KeyCode.RIGHT){
                    obj.setPosition(5,0);
                }
            }
        });
        root.getChildren().add(obj.getFigure());
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
        Application.launch(args);
    }
}
