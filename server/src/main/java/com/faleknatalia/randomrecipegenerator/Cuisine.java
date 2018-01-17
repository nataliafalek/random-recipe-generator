package com.faleknatalia.randomrecipegenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


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
