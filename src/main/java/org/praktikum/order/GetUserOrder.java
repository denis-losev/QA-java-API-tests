package org.praktikum.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.praktikum.constants.RequestUrls;

public class GetUserOrder extends RequestUrls {
    @Step("Получение заказов конкретного пользователя с авторизацией")
    public ValidatableResponse getUserOrder(String token) {
        return doGetRequest(getORDERS_URL(), token);
    }
    @Step("Получение заказов конкретного пользователя без авторизации")
    public ValidatableResponse getUserOrder() {
        return doGetRequest(getORDERS_URL());
    }
}
