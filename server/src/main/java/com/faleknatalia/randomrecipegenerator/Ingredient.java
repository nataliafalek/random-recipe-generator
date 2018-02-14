package com.faleknatalia.randomrecipegenerator;

import javax.persistence.*;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ingredientId;

    private String item;
    private String unit;
    private double quantity;

    public Ingredient() {
    }

    public Ingredient(String item, String unit, double quantity) {
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

    public double getQuantity() {
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
