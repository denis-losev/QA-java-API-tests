package org.praktikum.create_order_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.order.CreateNewOrder;
import org.praktikum.order.GetIngredientsIDList;
import org.praktikum.order.GetUserOrder;
import org.praktikum.user.RegisterUser;
import org.praktikum.user.User;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

public class GetUserOrderTest {
    List<String> ingredients = new GetIngredientsIDList().getIngredientsID();
    User user = new User("email777_test@test.test", "1234pas1234", "test777Name13");
    RegisterUser registerUser = new RegisterUser(user);
    CreateNewOrder newOrder = new CreateNewOrder();
    GetUserOrder getUserOrder = new GetUserOrder();
    String accessToken;

    @Before
    public void setUp() {
        accessToken = registerUser.getAccessToken();
        newOrder.createOrder(ingredients);
    }
    @Test
    @DisplayName("Получение заказов конкретного пользователя с авторизацией")
    @Description("Можно получить заказ с авторизацией")
    public void getUserOrdersAuthTest() {
        getUserOrder.getUserOrder(accessToken)
                .assertThat()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Получение заказов конкретного пользователя без авторизации")
    @Description("Нельзя получить заказ без авторизации")
    public void getUserOrdersNoAuthTest() {
        getUserOrder.getUserOrder()
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
