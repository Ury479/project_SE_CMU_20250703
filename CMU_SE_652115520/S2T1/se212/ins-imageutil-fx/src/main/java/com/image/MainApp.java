package com.image;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try {
            // Read file fxml and draw interface.
            Parent root = FXMLLoader.load(getClass()
                    .getResource("/Main.fxml"));

            stage.setTitle("My Application");
            stage.setScene(new Scene(root));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}