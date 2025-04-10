package api.orders;

import api.BaseApiTest;
import controllers.OrderController;
import controllers.UserController;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.CreateOrder;
import models.CreateUser;
import models.LoginUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.TestDataFactory;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

@DisplayName("[API] Создание заказа")
public class CreateOrderApiTest extends BaseApiTest {

    private CreateUser createUser;
    private LoginUser loginUser;
    private String token;

    @Before
    public void setUp() {
        createUser = TestDataFactory.getValidUser();
        loginUser = new LoginUser(createUser.getEmail(), createUser.getPassword());

        UserController.createUser(createUser);
        token = UserController.getAccessToken(loginUser);
    }

    @Test
    @DisplayName("Создание заказа с авторизацией и ингредиентами")
    public void createOrderWithAuthAndIngredientsTest() {
        CreateOrder order = TestDataFactory.validOrderWithBunAndSauce();

        Response response = OrderController.createOrder(order, token);
        assertSuccessOrder(response);
    }

    @Test
    @DisplayName("Создание заказа без авторизации с ингредиентами")
    public void createOrderWithoutAuthAndWithIngredientsTest() {
        CreateOrder order = TestDataFactory.validOrderWithAnyIngredients();

        Response response = OrderController.createOrder(order); // без токена
        assertSuccessOrder(response);
    }

    @Test
    @DisplayName("Создание заказа с авторизацией и без ингредиентов")
    public void createOrderWithAuthAndWithoutIngredientsTest() {
        CreateOrder order = TestDataFactory.invalidOrderWithoutIngredients();

        Response response = OrderController.createOrder(order, token);
        assertMissingIngredients(response);
    }

    @Test
    @DisplayName("Создание заказа без авторизации и без ингредиентов")
    public void createOrderWithoutAuthAndWithoutIngredientsTest() {
        CreateOrder order = TestDataFactory.invalidOrderWithoutIngredients();

        Response response = OrderController.createOrder(order);
        assertMissingIngredients(response);
    }

    @Test
    @DisplayName("Создание заказа с авторизацией и некорректными ингредиентами")
    public void createOrderWithAuthAndWrongIngredientsTest() {
        CreateOrder order = TestDataFactory.invalidOrderWithWrongIngredients();

        Response response = OrderController.createOrder(order, token);
        assertServerError(response);
    }

    @Test
    @DisplayName("Создание заказа без авторизации и с некорректными ингредиентами")
    public void createOrderWithoutAuthAndWithWrongIngredientsTest() {
        CreateOrder order = TestDataFactory.invalidOrderWithWrongIngredients();

        Response response = OrderController.createOrder(order);
        assertServerError(response);
    }

    @Step("Проверка успешного создания заказа")
    private void assertSuccessOrder(Response response) {
        response.then().assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("name", notNullValue())
                .body("order.number", notNullValue());
    }

    @Step("Проверка ошибки: отсутствуют ингредиенты")
    private void assertMissingIngredients(Response response) {
        response.then().assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Step("Проверка ошибки сервера (невалидные ингредиенты)")
    private void assertServerError(Response response) {
        response.then().assertThat()
                .statusCode(SC_INTERNAL_SERVER_ERROR);
    }

    @After
    public void tearDown() {
        UserController.deleteUser(token);
    }
}
