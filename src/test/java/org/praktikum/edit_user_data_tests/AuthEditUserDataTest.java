package org.praktikum.edit_user_data_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.user.*;

import static org.hamcrest.CoreMatchers.equalTo;

public class AuthEditUserDataTest {
    private String accessToken;
    private final String email = "some-email74@yandex.ru",
            password = "SoMeP4$sW0rD",
            name = "User93";
    private final String newEmail = "some-new-email359@yandex.ru",
            newPassword = "SoMeNeWP4$sW0rD",
            newName = "NewUser0543";
    User user = new User(email, password, name);
    User newUserData = new User(newEmail, newPassword, newName);
    RegisterUser registerUser = new RegisterUser(user);
    UserEmail userEmailNew = new UserEmail(newEmail);
    UserName userNameNew = new UserName(newName);
    UserPassword userPasswordNew = new UserPassword(newPassword);
    EditUser editUser = new EditUser(user);
    @Before
    public void createUser() {
        accessToken = registerUser.getAccessToken();
    }

    @Test
    @DisplayName("Изменение адреса эл.почты пользователя с авторизацией")
    @Description("Авторизовавшись можно внести изменения в адрес эл.почты пользователя")
    public void authEditUserMailTest() {
        editUser.editData(userEmailNew, accessToken)
                .assertThat()
                .body("success", equalTo(true))
                .and()
                .body("user.email", equalTo(newEmail));
    }

    @Test
    @DisplayName("Изменение имени пользователя с авторизацией")
    @Description("Авторизовавшись можно внести изменения в имя пользователя")
    public void authEditUserLoginTest() {
        editUser.editData(userNameNew, accessToken)
                .assertThat()
                .body("success", equalTo(true))
                .and()
                .body("user.name", equalTo(newName));
    }

    @Test
    @DisplayName("Изменение пароля пользователя с авторизацией")
    @Description("Авторизовавшись можно внести изменения в пароль пользователя")
    public void authEditUserPasswordTest() {
        editUser.editData(userPasswordNew, accessToken)
                .assertThat()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Изменение всех персональных данных пользователя с авторизацией")
    @Description("Авторизовавшись можно внести изменения во все персональные данные пользователя сразу")
    public void authEditUserDataTest() {
        editUser.editData(newUserData, accessToken)
                .assertThat()
                .body("success", equalTo(true))
                .and()
                .body("user.name", equalTo(newName))
                .and()
                .body("user.email", equalTo(newEmail));
    }

    @After
    public void clearTestData() {
        registerUser.deleteUser(accessToken);
    }
}
