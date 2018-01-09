package com.faleknatalia.randomrecipegenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.*;

@Component
public class RandomDish {

    public RandomDish() {
    }

    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe findRandomDish() {
        long numberOfRecords = recipeRepository.count();
        long randomNumber = (long) (Math.random() * numberOfRecords + 1);
        return recipeRepository.getOne(randomNumber);
    }

}
