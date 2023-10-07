package org.praktikum.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.praktikum.constants.Requests;

public class LoginUser extends Requests {
    User user;

    public LoginUser(User user) {
        this.user = user;
    }

    @Step("Логин пользователя")
    public ValidatableResponse loginUser() {
        return doPostRequest(LOGIN_USER_URL, user);
    }
}
