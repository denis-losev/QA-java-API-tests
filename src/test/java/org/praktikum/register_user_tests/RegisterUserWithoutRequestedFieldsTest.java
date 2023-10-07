package org.praktikum.register_user_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.praktikum.user.RegisterUser;
import org.praktikum.user.User;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class RegisterUserWithoutRequestedFieldsTest {
    private String email, password, name;

    public RegisterUserWithoutRequestedFieldsTest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Parameterized.Parameters(name = "Email: {0}, Password: {1}, Name: {2}")
    public static Object[][] getParams() {
        return new Object[][]{
                {"my-email@yandex.ru", "SuPeRp@s$w0Rd", ""},
                {"my-email01@yandex.ru", "", "MyUniqueName"},
                {"", "SuPeRp@s$w0Rd", "MySecondUniqueName"},
        };
    }

    @Test
    @DisplayName("Попытки создать пользователя и не заполнить одно из обязательных полей")
    @Description("Если нет одного из полей, вернётся код ответа 403 Forbidden")
    public void registerUserWithoutRequestedFieldsTest() {
        User user = new User(email, password, name);
        RegisterUser registeredUser = new RegisterUser(user);
        registeredUser.registerUser()
                .assertThat()
                .statusCode(403)
                .and()
                .body("message", equalTo("Email, password and name are required fields"));
    }
}
