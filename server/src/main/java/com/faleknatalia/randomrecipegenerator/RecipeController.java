package com.faleknatalia.randomrecipegenerator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class RecipeController {


    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private RandomDish randomDish;


    @RequestMapping(value = "random/recipe", method = RequestMethod.GET)
    public ResponseEntity<Recipe> readRecipe(@RequestParam("cuisine") String cuisine) {
        final Recipe randomDish = this.randomDish.findRandomDishByCuisine(cuisine);
        return new ResponseEntity<>(randomDish, HttpStatus.OK);
    }


    @RequestMapping(value = "read/recipe", method = RequestMethod.POST)
    public ResponseEntity<Void> readRecipe(@RequestBody Recipe recipe) {
        recipeRepository.save(recipe);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/cuisine", method = RequestMethod.GET)
    public ResponseEntity<List<Cuisine>> readCuisine() {
        return new ResponseEntity<>(cuisineRepository.findAll(), HttpStatus.OK);
    }
}
