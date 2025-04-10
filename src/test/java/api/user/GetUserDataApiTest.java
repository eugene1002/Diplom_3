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

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("[API] Получение данных пользователя")
public class GetUserDataApiTest extends BaseApiTest {

    private CreateUser createUser;
    private LoginUser loginUser;
    private String token;

    @Before
    public void setUp() {
        createUser = new CreateUser(userEmail, userPassword, userName);
        loginUser = new LoginUser(createUser.getEmail(), createUser.getPassword());

        UserController.createUser(createUser);
        token = UserController.getAccessToken(loginUser);
    }

    @Test
    @DisplayName("Получение данных пользователя с авторизацией")
    public void getEmailWithAuthTest() {
        Response response = UserController.getUserData(loginUser, true);
        response.then().assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("user.email", equalTo(userEmail))
                .body("user.name", equalTo(userName));
    }

    @Test
    @DisplayName("Получение данных пользователя без авторизации")
    public void getEmailWithoutAuthTest() {
        Response response = UserController.getUserData(loginUser, false);
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
