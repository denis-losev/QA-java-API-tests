package org.praktikum.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.praktikum.constants.Requests;

public class EditUser extends Requests {
    User user;

    public EditUser(User user) {
        this.user = user;
    }

    @Step("Изменение персональных данных пользователя")
    public ValidatableResponse editData(Object newData, String accessToken) {
        return doPatchRequest(AUTH_USER_URL, accessToken, newData);
    }
}
