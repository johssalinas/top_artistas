package com.johs.top_artistas.models;

public class Song {

    private String image;
    private String name;
    private String listeners;
    private String plays;

    public Song(String image, String name, String listeners, String plays) {
        this.image = image;
        this.name = name;
        this.listeners = listeners;
        this.plays = plays;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public String getPlays() {
        return plays;
    }

    public void setPlays(String plays) {
        this.plays = plays;
    }
}
