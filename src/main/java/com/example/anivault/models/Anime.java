package com.example.anivault.models;

public class Anime {
    private int mal_id;
    private String title;
    private String synopsis;
    private int episodes;
    private String imageUrl;
    private double score;

    public Anime(int mal_id, String title, String synopsis, int episodes, String imageUrl, double score) {
        this.mal_id = mal_id;
        this.title = title;
        this.synopsis = synopsis;
        this.episodes = episodes;
        this.imageUrl = imageUrl;
        this.score = score;
    }

    public int getMal_id() {
        return mal_id;
    }

    public String getTitle() {
        return title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public int getEpisodes() {
        return episodes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return title + " (" + episodes + " episodes)";
    }
}
