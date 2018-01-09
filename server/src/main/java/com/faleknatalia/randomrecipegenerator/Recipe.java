package com.faleknatalia.randomrecipegenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recipeId;

    private String country;
    private String dish;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ingredient> ingredients;
    private String method;

    public Recipe() {
    }

    public Recipe(String country, String dish, List<Ingredient> ingredients, String method) {
        this.country = country;
        this.dish = dish;
        this.ingredients = ingredients;
        this.method = method;
    }

    public static Recipe newRecipe(Recipe recipe) {
        Recipe recipe1 = new Recipe();

        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredient.setRecipe(recipe);
        }

        return recipe;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public String getCountry() {
        return country;
    }

    public String getDish() {
        return dish;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getMethod() {
        return method;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        String result =
                String.format("Recipe [recipeId=%d, country=%s, dish=%s, method=%s]", recipeId, country, dish, method);
        if (ingredients != null) {
            for (Ingredient ingredient : ingredients) {
                result += String.format(
                        "Ingredient[ingredientId=%d, item=%s, unit=%s, quantity=%d]", ingredient.getIngredientId(), ingredient.getItem(), ingredient.getUnit(), ingredient.getQuantity()
                );
            }
        }
        return result;
    }


}
