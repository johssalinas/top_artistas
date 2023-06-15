package com.johs.top_artistas.models;

public class Artist {

    private int id;
    private String image;
    private String name;
    private String listeners;

    public Artist(int id, String image, String name, String listeners) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.listeners = listeners;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
