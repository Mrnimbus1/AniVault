package com.example.anivault;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DetailsController {

    @FXML
    private ImageView animePoster;

    @FXML
    private Text animeTitle;

    @FXML
    private Label animeSynopsis;

    @FXML
    private Label animeEpisodes;

    @FXML
    private Label animeScore;

    public void setAnimeDetails(String title, String posterUrl, String synopsis, int episodes, double score) {
        animeTitle.setText(title);
        animePoster.setImage(new Image(posterUrl));
        animeSynopsis.setText("Synopsis: " + synopsis);
        animeEpisodes.setText("Episodes: " + episodes);
        animeScore.setText("Score: " + (score > 0 ? score : "N/A"));
    }

    @FXML
    public void onBackButtonClick() {
        Stage stage = (Stage) animePoster.getScene().getWindow();
        stage.close(); // Close the detailed view window
    }
}
