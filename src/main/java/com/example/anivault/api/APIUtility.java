package com.example.anivault.api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIUtility {
    public static JSONObject fetchAnimeData(String query) throws Exception {
        String urlString = "https://api.jikan.moe/v4/anime?q=" + query;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(response.toString());
    }
}