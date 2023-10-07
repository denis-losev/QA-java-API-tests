package org.praktikum.login_user_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.praktikum.user.LoginUser;
import org.praktikum.user.User;

import static org.hamcrest.CoreMatchers.equalTo;

public class LoginWithNotValidDataTest {
    private final String email = "neverniy-login@te-st.ru",
            password = "neverniy-parol";

    User notValidUserData = new User(email, password);

    @Test
    @DisplayName("Логин с неверными логином и паролем")
    @Description("Нельзя войти в систему с неверными данными")
    public void loginWithNotValidDataTest() {
        LoginUser loginUser = new LoginUser(notValidUserData);
        loginUser.loginUser()
                .assertThat()
                .statusCode(401)
                .and()
                .body("message", equalTo("email or password are incorrect"));
    }
}
