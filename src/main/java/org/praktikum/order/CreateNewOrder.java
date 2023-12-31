package org.praktikum.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.praktikum.constants.Requests;
import java.util.List;
public class CreateNewOrder extends Requests {
    @Step("Авторизованный пользователь: Создание заказа")
    public ValidatableResponse createOrder(String token, List<String> ingredients) {
        Order newOrder = new Order(ingredients);
        return doPostRequest(ORDERS_URL, newOrder, token);
    }
    @Step("Неавторизованный пользователь: Создание заказа")
    public ValidatableResponse createOrder(List<String> ingredients) {
        Order newOrder = new Order(ingredients);
        return doPostRequest(ORDERS_URL, newOrder);
    }

    @Step("Авторизованный пользователь: Создание заказа без ингредиентов")
    public ValidatableResponse createOrder(String token) {
        Order newOrder = new Order();
        return doPostRequest(ORDERS_URL, newOrder, token);
    }
    @Step("Неавторизованный пользователь: Создание заказа без ингредиентов")
    public ValidatableResponse createOrder() {
        Order newOrder = new Order();
        return doPostRequest(ORDERS_URL, newOrder);
    }
}
