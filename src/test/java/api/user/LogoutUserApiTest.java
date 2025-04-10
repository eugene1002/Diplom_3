package api.user;

import api.BaseApiTest;
import controllers.UserController;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.CreateUser;
import models.LoginUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.TestDataFactory;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

@DisplayName("[API] Выход из системы")
public class LogoutUserApiTest extends BaseApiTest {

    private CreateUser createUser;
    private LoginUser loginUser;

    @Before
    public void setUp() {
        createUser = TestDataFactory.getValidUser();
        loginUser = TestDataFactory.getLoginFrom(createUser);
        UserController.createUser(createUser);
        UserController.loginUser(loginUser);
    }

    @Test
    @DisplayName("Выход из системы под существующим пользователем")
    public void successLogoutTest() {
        Response response = UserController.logoutUser(loginUser);

        response.then().assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("message", equalTo("Successful logout"));
    }

    @After
    public void tearDown() {
        UserController.deleteUser(loginUser);
    }
}
