package com.faleknatalia.randomrecipegenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Cuisine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cuisineId;

    private String cuisine;

    public Cuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public Cuisine() {
    }


    public long getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(long cuisineId) {
        this.cuisineId = cuisineId;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
}
