package org.praktikum.edit_user_data_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.user.*;

import static org.hamcrest.CoreMatchers.equalTo;

public class NonAuthEditUserDataTest {
    private String accessToken;
    private final String email = "some-email0894@yandex.ru",
            password = "SoMeP4$sW0rD",
            name = "User2353";
    private final String newEmail = "some-new-email8889@yandex.ru",
            newPassword = "SoMeNeWP4$sW0rD",
            newName = "NewUser8883";
    User user = new User(email, password, name);
    User newUserData = new User(newEmail, newPassword, newName);
    RegisterUser registerUser = new RegisterUser(user);
    UserEmail userEmailNew = new UserEmail(newEmail);
    UserName userNameNew = new UserName(newName);
    UserPassword userPasswordNew = new UserPassword(newPassword);
    EditUser editUser = new EditUser(user);

    @Before
    public void createUser() {
        registerUser.registerUser();
        accessToken = "";
    }

    @Test
    @DisplayName("Изменение адреса эл.почты пользователя без авторизации")
    @Description("Не авторизовавшись невозможно внести изменения в адрес эл.почты пользователя")
    public void nonAuthEditUserMailTest() {
        editUser.editData(userEmailNew, accessToken)
                .assertThat()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("You should be authorised"));
    }

    @Test
    @DisplayName("Изменение имени пользователя без авторизации")
    @Description("Не авторизовавшись невозможно внести изменения в имя пользователя")
    public void nonAuthEditUserLoginTest() {
        editUser.editData(userNameNew, accessToken)
                .assertThat()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("You should be authorised"));
    }

    @Test
    @DisplayName("Изменение пароля пользователя без авторизации")
    @Description("Не авторизовавшись невозможно внести изменения в пароль пользователя")
    public void nonAuthEditUserPasswordTest() {
        editUser.editData(userPasswordNew, accessToken)
                .assertThat()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("You should be authorised"));
    }

    @Test
    @DisplayName("Изменение всех персональных данных пользователя с авторизацией")
    @Description("Авторизовавшись можно внести изменения во все персональные данные пользователя сразу")
    public void nonAuthEditUserDataTest() {
        editUser.editData(newUserData, accessToken)
                .assertThat()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("You should be authorised"));
    }

    @After
    public void clearTestData() {
        registerUser.deleteUser(accessToken);
    }
}
