package com.example.anivault;

import com.example.anivault.api.APIUtility;
import com.example.anivault.models.Anime;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class HelloController {

    @FXML
    private TextField searchField;

    @FXML
    private GridPane animeGalleryGrid;

    private static final int CARD_WIDTH = 150;
    private static final int CARD_HEIGHT = 225;

    public void initialize() {
        loadAnimeGallery("Bleach"); // Default anime displayed on launch
    }

    @FXML
    private void onFavorites() {
        navigateTo("favorites-view.fxml", "Favorites");
    }

    @FXML
    private void onHome() {
        navigateTo("hello-view.fxml", "AniVault");
    }

    @FXML
    private void onLogout() {
        navigateTo("welcome-view.fxml", "Welcome to AniVault");
    }

    @FXML
    private void onSearch() {
        String query = searchField.getText().trim();
        if (!query.isEmpty()) {
            loadAnimeGallery(query); // Fetch and display search results
        } else {
            showAlert("Search Error", "Please enter a valid anime name.");
        }
    }

    private void loadAnimeGallery(String query) {
        try {
            JSONObject response = APIUtility.fetchAnimeData(query);
            if (response != null && response.containsKey("data")) {
                JSONArray data = (JSONArray) response.get("data");

                animeGalleryGrid.getChildren().clear(); // Clear previous content

                int row = 0, col = 0;
                for (Object obj : data) {
                    JSONObject animeJSON = (JSONObject) obj;

                    // Parse anime data
                    int mal_id = animeJSON.get("mal_id") != null ? ((Long) animeJSON.get("mal_id")).intValue() : -1;
                    String title = (String) animeJSON.getOrDefault("title", "Unknown Title");
                    String synopsis = (String) animeJSON.getOrDefault("synopsis", "No synopsis available.");
                    int episodes = animeJSON.get("episodes") != null ? ((Long) animeJSON.get("episodes")).intValue() : 0;
                    double score = animeJSON.get("score") != null ? ((Double) animeJSON.get("score")) : 0.0;
                    String imageUrl = ((JSONObject) ((JSONObject) animeJSON.get("images")).get("jpg")).get("image_url").toString();

                    Anime anime = new Anime(mal_id, title, synopsis, episodes, imageUrl, score);

                    // Create ImageView for anime poster
                    ImageView poster = new ImageView(new Image(imageUrl));
                    poster.setFitWidth(CARD_WIDTH);
                    poster.setFitHeight(CARD_HEIGHT);
                    poster.setStyle("-fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 10, 0, 0, 5);");

                    // Add click event for detailed view
                    poster.setOnMouseClicked(event -> showAnimeDetails(anime));

                    // Create label for anime title
                    Label animeTitle = new Label(title);
                    animeTitle.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-wrap-text: true;");
                    animeTitle.setMaxWidth(CARD_WIDTH);

                    // Create "Add to Favorites" button
                    Button addToFavoritesButton = new Button("Add to Favorites");
                    addToFavoritesButton.setStyle("-fx-background-color: #FF9900; -fx-text-fill: white; -fx-font-size: 12px;");
                    addToFavoritesButton.setOnAction(event -> {
                        FavoritesController.addToFavorites(anime);
                    });

                    // Create a VBox to hold poster, title, and button
                    VBox animeCard = new VBox(10, poster, animeTitle, addToFavoritesButton);
                    animeCard.setAlignment(Pos.CENTER);

                    // Add to the grid
                    animeGalleryGrid.add(animeCard, col, row);

                    col++;
                    if (col >= 4) { // Wrap to the next row after 4 columns
                        col = 0;
                        row++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    private void showAnimeDetails(Anime anime) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("details-view.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);

            DetailsController controller = loader.getController();
            controller.setAnimeDetails(
                    anime.getTitle(),
                    anime.getImageUrl(),
                    anime.getSynopsis(),
                    anime.getEpisodes(),
                    anime.getScore()
            );

            Stage detailsStage = new Stage();
            detailsStage.setScene(scene);
            detailsStage.setTitle("Anime Details");
            detailsStage.setResizable(false); // Fixed size
            detailsStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load anime details.");
        }
    }

    private void navigateTo(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load(), 800, 600);

            Stage stage = (Stage) animeGalleryGrid.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(title);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Navigation Error", "Failed to navigate to " + title);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
