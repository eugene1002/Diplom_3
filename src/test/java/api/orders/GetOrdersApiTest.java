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

import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.Matchers.*;

@DisplayName("[API] Получение заказов")
public class GetOrdersApiTest extends BaseApiTest {

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
    @DisplayName("Получение всех заказов")
    public void getAllOrders() {
        Response response = OrderController.getAllOrders();
        assertOrderResponse(response);
    }

    @Test
    @DisplayName("Получение заказов авторизованного пользователя")
    public void getOrdersWithAuthTest() {
        CreateOrder order = TestDataFactory.validOrderWithBunAndSauce();
        OrderController.createOrder(order);

        Response response = OrderController.getOrders(token, true);
        assertOrderResponse(response);
    }

    @Test
    @DisplayName("Получение заказов неавторизованного пользователя")
    public void getOrdersWithoutAuthTest() {
        Response response = OrderController.getOrders(token, false);
        assertUnauthorizedOrderResponse(response);
    }

    @Step("Проверка успешного получения заказов")
    private void assertOrderResponse(Response response) {
        response.then().assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("orders", notNullValue());
    }

    @Step("Проверка ошибки при получении заказов без авторизации")
    private void assertUnauthorizedOrderResponse(Response response) {
        response.then().assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }

    @After
    public void tearDown() {
        UserController.deleteUser(token);
    }
}
