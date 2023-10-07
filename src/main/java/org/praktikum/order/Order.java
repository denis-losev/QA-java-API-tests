package org.praktikum.order;

import java.util.List;

public class Order {
    private List<String> ingredients;

    public Order(List<String> ingredientsIdArray) {
        this.ingredients = ingredientsIdArray;
    }

    public Order() {
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
