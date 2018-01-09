package com.faleknatalia.randomrecipegenerator;

import javax.persistence.*;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ingredientId;

    private String item;
    private String unit;
    private int quantity;

    //TODO zamienić obiekt na obiektID
    @ManyToOne
    @JoinColumn(name = "RECIPE_ID")
    private Recipe recipe;


    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient() {
    }

    public Ingredient(String item, String unit, int quantity) {
        this.item = item;
        this.unit = unit;
        this.quantity = quantity;
    }

    public long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getItem() {
        return item;
    }

    public String getUnit() {
        return unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
