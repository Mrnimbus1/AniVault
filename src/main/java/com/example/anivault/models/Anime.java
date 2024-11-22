package com.example.anivault.models;

public class Anime {
    private int mal_id;
    private String title;
    private String synopsis;
    private int episodes;

    public Anime(int mal_id, String title, String synopsis, int episodes) {
        this.mal_id = mal_id;
        this.title = title;
        this.synopsis = synopsis;
        this.episodes = episodes;
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

    @Override
    public String toString() {
        return title + " (" + episodes + " episodes)";
    }
}