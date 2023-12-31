package org.praktikum.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.praktikum.constants.Requests;

import java.util.ArrayList;
import java.util.List;

public class GetIngredientsIDList extends Requests {
    public List<String> IngredientsArray = new ArrayList<>();
    @Step("Получение ID всех ингредиентов")
    public List<String> getIngredientsID() {
        ValidatableResponse response = doGetRequest(INGREDIENT_LIST);
        List<IngredientID> idArray = response.extract().body().as(IngredientsList.class).getData();
        for (IngredientID ingredientID : idArray) {
            IngredientsArray.add(ingredientID.get_id());
        }
        return IngredientsArray;
    }

    @Step("Получение ID невалидных ингредиентов")
    public List<String> getNotValidIngredientsID() {
        ValidatableResponse response = doGetRequest(INGREDIENT_LIST);
        List<IngredientID> idArray = response.extract().body().as(IngredientsList.class).getData();
        for (IngredientID ingredientID : idArray) {
            IngredientsArray.add(ingredientID.get_id()+"Not_valid");
        }
        return IngredientsArray;
    }
}
