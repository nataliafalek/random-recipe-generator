package com.faleknatalia.randomrecipegenerator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @RequestMapping(value = "read/recipe", method = RequestMethod.POST)
    public ResponseEntity<Void> readRecipe(@RequestBody Recipe recipe) {
        Recipe.newRecipe(recipe);
        recipeRepository.save(recipe);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
