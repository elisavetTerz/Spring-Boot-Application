package com.koerber.demo1.model;

public class Article {
    private Integer id;
    private String description;
    private Double weight;
    private Double volume;
    private Boolean active;

    public Article() {
    }

    public Article(Integer id, String description, Double weight, Double volume, Boolean active) {
        this.id = id;
        this.description = description;
        this.weight = weight;
        this.volume = volume;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", volume=" + volume +
                ", active=" + active +
                '}';
    }
}
