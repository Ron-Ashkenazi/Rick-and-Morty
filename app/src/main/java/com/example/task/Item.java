package com.example.task;

public class Item {
    String image;
    String name;
    String status;
    String species;

    public Item(String image, String name, String status, String species) {
        this.image = image;
        this.name = name;
        this.status = status;
        this.species = species;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getSpecies() {
        return species;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSpecies(String species) {
        this.species = species;
    }




}
