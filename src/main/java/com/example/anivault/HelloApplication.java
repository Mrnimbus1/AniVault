package com.example.anivault;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/anivault/welcome-view.fxml"));
        if (fxmlLoader.getLocation() == null) {
            throw new IllegalStateException("Cannot find FXML file.");
        }
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Welcome to AniVault");

        // Load the icon file
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/anivault/otaku.png")));

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
