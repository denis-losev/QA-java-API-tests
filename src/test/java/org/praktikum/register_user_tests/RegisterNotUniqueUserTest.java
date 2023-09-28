package org.praktikum.register_user_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.user.RegisterUser;
import org.praktikum.user.User;

import static org.hamcrest.CoreMatchers.equalTo;

public class RegisterNotUniqueUserTest {
    private final String email = "some-email719@yandex.ru",
            password = "SoMeP4$sW0rD",
            name = "NewUser719";
    User newUser = new User(email, password, name);
    RegisterUser registeredUser = new RegisterUser(newUser);
    private String accessToken;

    @Before
    public void createUser() {
        accessToken = registeredUser.getAccessToken();
    }
    @Test
    @DisplayName("Создание пользователя, который уже зарегистрирован")
    @Description("Нельзя создать копию существующего пользователя")
    public void registerNotUniqueUserTest() {
        registeredUser.registerUser()
                .assertThat()
                .statusCode(403)
                .and()
                .body("message", equalTo("User already exists"));
    }

    @After
    public void clearTestData() {
        registeredUser.deleteUser(accessToken);
    }
}
