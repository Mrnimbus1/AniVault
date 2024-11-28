package com.example.anivault;

import com.example.anivault.models.Anime;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class FavoritesController {

    @FXML
    private GridPane favoritesGrid;

    private static List<Anime> favoriteAnimeList = new ArrayList<>();

    public void initialize() {
        loadFavorites();
    }

    public static void addToFavorites(Anime anime) {
        if (!favoriteAnimeList.contains(anime)) {
            favoriteAnimeList.add(anime);
        }
    }

    public static void removeFromFavorites(Anime anime) {
        favoriteAnimeList.remove(anime);
    }

    private void loadFavorites() {
        favoritesGrid.getChildren().clear();

        int row = 0, col = 0;
        for (Anime anime : favoriteAnimeList) {
            // Create ImageView for anime poster
            ImageView poster = new ImageView(new Image(anime.getImageUrl()));
            poster.setFitWidth(150);
            poster.setFitHeight(225);
            poster.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 10, 0, 0, 5);");

            // Create label for anime title
            Label animeTitle = new Label(anime.getTitle());
            animeTitle.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-wrap-text: true;");
            animeTitle.setMaxWidth(150);

            // Create Remove Button
            Button removeButton = new Button("Remove");
            removeButton.setStyle("-fx-background-color: #FF3333; -fx-text-fill: white; -fx-font-size: 12px;");
            removeButton.setOnAction(event -> {
                removeFromFavorites(anime);
                loadFavorites(); // Refresh the favorites view
            });

            // Create a VBox to hold poster, title, and button
            VBox animeCard = new VBox(10, poster, animeTitle, removeButton);
            animeCard.setAlignment(Pos.CENTER);

            // Add to the grid
            favoritesGrid.add(animeCard, col, row);

            col++;
            if (col >= 4) { // Wrap to the next row after 4 columns
                col = 0;
                row++;
            }
        }
    }

    @FXML
    private void onBackButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);

            Stage stage = (Stage) favoritesGrid.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("AniVault Home");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);

            Stage stage = (Stage) favoritesGrid.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("AniVault Home");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
