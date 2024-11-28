package com.example.anivault;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class WelcomeController {

    @FXML
    private void onSignup(ActionEvent event) {
        navigateTo(event, "signup-view.fxml", "Sign Up");
    }

    @FXML
    private void onLogin(ActionEvent event) {
        navigateTo(event, "login-view.fxml", "Log In");
    }

    private void navigateTo(ActionEvent event, String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load(), 800, 600);

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
