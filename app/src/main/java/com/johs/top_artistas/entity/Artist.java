package com.johs.top_artistas.entity;

public class Artist {

    private String id;
    private String image;
    private String name;
    private String listeners;

    public Artist(String id, String image, String name, String listeners) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.listeners = listeners;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
