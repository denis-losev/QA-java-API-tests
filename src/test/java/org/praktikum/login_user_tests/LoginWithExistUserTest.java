package org.praktikum.login_user_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.user.LoginUser;
import org.praktikum.user.RegisterUser;
import org.praktikum.user.User;

import static org.hamcrest.CoreMatchers.equalTo;

public class LoginWithExistUserTest {
    private final String email = "some-email765@yandex.ru",
            password = "SoMeP4$sW0rD",
            name = "NewUser7772";

    User newUser = new User(email, password, name);
    User loginData = new User(email, password);
    RegisterUser registeredUser = new RegisterUser(newUser);
    private String accessToken;

    @Before
    public void createUser() {
        accessToken = registeredUser.getAccessToken();
    }

    @Test
    @DisplayName("Логин существующего пользователя")
    @Description("Можно войти в систему под существующим пользователем")
    public void loginWithExistUserTest() {
        LoginUser loginUser = new LoginUser(loginData);
        loginUser.loginUser()
                .assertThat()
                .statusCode(200)
                .and()
                .body("success", equalTo(true));
    }

    @After
    public void clearTestData() {
        registeredUser.deleteUser(accessToken);
    }
}
