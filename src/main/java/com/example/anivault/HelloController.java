package com.example.anivault;

import com.example.anivault.api.APIUtility;
import com.example.anivault.models.Anime;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML
    private ListView<String> animeListView;

    public void initialize() {
        try {
            JSONObject response = APIUtility.fetchAnimeData("Bleach");
            if (response.containsKey("data")) {
                JSONArray data = (JSONArray) response.get("data");

                List<Anime> animeList = new ArrayList<>();
                for (Object obj : data) {
                    JSONObject animeJSON = (JSONObject) obj;

                    int mal_id = animeJSON.get("mal_id") != null ? ((Long) animeJSON.get("mal_id")).intValue() : -1;
                    String title = (String) animeJSON.getOrDefault("title", "Unknown Title");
                    String synopsis = (String) animeJSON.getOrDefault("synopsis", "No synopsis available.");
                    int episodes = animeJSON.get("episodes") != null ? ((Long) animeJSON.get("episodes")).intValue() : 0;

                    Anime anime = new Anime(mal_id, title, synopsis, episodes);
                    animeList.add(anime);
                }

                // Populate the ListView
                for (Anime anime : animeList) {
                    animeListView.getItems().add(anime.toString());
                }
            } else {
                System.out.println("Error: 'data' key is missing in the response.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}