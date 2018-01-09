package com.faleknatalia.randomrecipegenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomDishController {

    @Autowired
    RandomDish randomDish;

    @RequestMapping(value = "random/recipe", method = RequestMethod.GET)
    public ResponseEntity<Recipe> readRecipe() {
        final Recipe randomDish = this.randomDish.findRandomDish();
        randomDish.getIngredients().forEach(i -> i.setRecipe(null));
        return new ResponseEntity<>(randomDish, HttpStatus.OK);
    }
}
