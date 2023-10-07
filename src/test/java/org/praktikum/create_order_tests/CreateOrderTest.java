package org.praktikum.create_order_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.order.CreateNewOrder;
import org.praktikum.order.GetIngredientsIDList;
import org.praktikum.user.RegisterUser;
import org.praktikum.user.User;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateOrderTest {
    List<String> validIngredients = new GetIngredientsIDList().getIngredientsID();
    List<String> notValidIngredients = new GetIngredientsIDList().getNotValidIngredientsID();
    User user = new User("email_test777@test.test", "1234pas1234", "testName777");
    RegisterUser registerUser = new RegisterUser(user);
    String accessToken;

    @Before
    public void createUser() {
        accessToken = registerUser.getAccessToken();
    }
    @Test
    @DisplayName("Создание заказа с ингредиентами без авторизации")
    @Description("Можно создать заказ без авторизации")
    public void createNonAuthOrderWithIngredientsTest() {
        CreateNewOrder newOrder = new CreateNewOrder();
        newOrder.createOrder(validIngredients)
                .assertThat()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Создание заказа с невалидными ингредиентами без авторизации")
    @Description("Нельзя создать заказ без авторизации")
    public void createNonAuthOrderWithNotValidIngredientsTest() {
        CreateNewOrder newOrder = new CreateNewOrder();
        newOrder.createOrder(notValidIngredients)
                .assertThat()
                .statusCode(500);
    }

    @Test
    @DisplayName("Создание заказа с авторизацией и невалидными ингредиентами")
    @Description("Нельзя создать заказ с авторизациейи невалидными ингредиентами")
    public void createAuthOrderWithNotValidIngredientsTest() {
        CreateNewOrder newOrder = new CreateNewOrder();
        newOrder.createOrder(accessToken, notValidIngredients)
                .assertThat()
                .statusCode(500);
    }
    @Test
    @DisplayName("Создание заказа с авторизацией")
    @Description("Можно создать заказ с авторизацией")
    public void createAuthOrderWithIngredientsTest() {
        CreateNewOrder newOrder = new CreateNewOrder();
        newOrder.createOrder(accessToken, validIngredients)
                .assertThat()
                .body("success", equalTo(true));
    }
    @Test
    @DisplayName("Создание заказа без ингредиентов и без авторизации")
    @Description("Можно создать заказ без авторизации")
    public void createNonAuthOrderWithoutIngredientsTest() {
        CreateNewOrder newOrder = new CreateNewOrder();
        newOrder.createOrder()
                .assertThat()
                .statusCode(400)
                .and()
                .body("success", equalTo(false));
    }
    @Test
    @DisplayName("Создание заказа без ингредиентов с авторизацией")
    @Description("Нельзя создать заказ без ингредиентов")
    public void createAuthOrderWithoutIngredientsTest() {
        CreateNewOrder newOrder = new CreateNewOrder();
        newOrder.createOrder(accessToken)
                .assertThat()
                .statusCode(400)
                .and()
                .body("success", equalTo(false));
    }

    @After
    public void clearTestData() {
        registerUser.deleteUser(accessToken);
    }
}
