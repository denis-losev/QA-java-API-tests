package org.praktikum.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.praktikum.constants.Requests;

public class GetUserOrder extends Requests {
    @Step("Получение заказов конкретного пользователя с авторизацией")
    public ValidatableResponse getUserOrder(String token) {
        return doGetRequest(ORDERS_URL, token);
    }
    @Step("Получение заказов конкретного пользователя без авторизации")
    public ValidatableResponse getUserOrder() {
        return doGetRequest(ORDERS_URL);
    }
}
