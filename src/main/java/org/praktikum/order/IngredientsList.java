package org.praktikum.order;

import java.util.List;

public class IngredientsList {
    private List<IngredientID> data;
    public IngredientsList(List<IngredientID> ingredientsIDS) {
        this.data = ingredientsIDS;
    }

    public List<IngredientID> getData() {
        return data;
    }

    public void setData(List<IngredientID> data) {
        this.data = data;
    }
}
