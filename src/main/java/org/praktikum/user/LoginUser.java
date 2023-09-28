package org.praktikum.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.praktikum.constants.RequestUrls;

public class LoginUser extends RequestUrls {
    User user;

    public LoginUser(User user) {
        this.user = user;
    }

    @Step("Логин пользователя")
    public ValidatableResponse loginUser() {
        return doPostRequest(getLOGIN_USER_URL(), user);
    }
}
