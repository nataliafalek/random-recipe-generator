package com.faleknatalia.randomrecipegenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomDish {

    public RandomDish() {
    }

    @Autowired
    private RecipeRepository recipeRepository;


    public Recipe findRandomDishByCuisine(String cuisine) {
        List<Recipe> recipesByCuisine = recipeRepository.findAllByCountryEquals(cuisine);
        int randomNumber = ThreadLocalRandom.current().nextInt(0, recipesByCuisine.size());
        return recipesByCuisine.get(randomNumber);
    }

}
