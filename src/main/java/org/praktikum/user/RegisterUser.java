package org.praktikum.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.praktikum.constants.Requests;
import org.praktikum.token.Token;

public class RegisterUser extends Requests {
    User user;

    public RegisterUser(User user) {
        this.user = user;
    }

    @Step("Регистрация пользователя")
    public ValidatableResponse registerUser() {
        return doPostRequest(REGISTER_USER_URL, user);
    }

    @Step("Получение  accessToken")
    public String getAccessToken() {
        return registerUser().extract().body().as(Token.class).getAccessToken();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String token) {
        return doDeleteRequest(AUTH_USER_URL, token);
    }
}
