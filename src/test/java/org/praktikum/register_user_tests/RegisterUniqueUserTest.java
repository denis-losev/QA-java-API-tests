package org.praktikum.register_user_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.user.RegisterUser;
import org.praktikum.user.User;

public class RegisterUniqueUserTest {
    private final String email = "some-email0777723@yandex.ru",
            password = "SoMeP4$sW0rD",
            name = "New8765User";

    User newUser = new User(email, password, name);
    RegisterUser registeredUser = new RegisterUser(newUser);
    private String accessToken;
    @Before
    public void createUser() {
        accessToken = registeredUser.getAccessToken();
    }
    @Test
    @DisplayName("Регистрация уникального пользователя")
    @Description("Можно создать пользователя с уникальными данными")
    public void registerUniqueUserTest() {
        Assert.assertTrue(accessToken.startsWith("Bearer"));
    }

    @After
    public void clearTestData() {
        registeredUser.deleteUser(accessToken);
    }
}
