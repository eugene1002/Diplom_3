package api.user;

import api.BaseApiTest;
import controllers.UserController;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.CreateUser;
import models.LoginUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.TestDataFactory;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

@DisplayName("[API] Авторизация пользователя")
public class LoginUserApiTest extends BaseApiTest {

    private CreateUser createUser;
    private LoginUser loginUser;
    private String token;

    @Before
    public void setUp() {
        createUser = TestDataFactory.getValidUser();
        loginUser = TestDataFactory.getLoginFrom(createUser);

        UserController.createUser(createUser);
        token = UserController.getAccessToken(loginUser);
    }

    @Test
    @DisplayName("Логин под существующим пользователем")
    public void successLoginTest() {
        Response response = UserController.loginUser(loginUser);
        assertLoginSuccess(response);
    }

    @Test
    @DisplayName("Логин с неверным email")
    public void failLoginWithWrongEmailTest() {
        LoginUser wrongEmail = new LoginUser("wrong_" + loginUser.getEmail(), loginUser.getPassword());
        assertInvalidLogin(wrongEmail);
    }

    @Test
    @DisplayName("Логин с неверным паролем")
    public void failLoginWithWrongPasswordTest() {
        LoginUser wrongPassword = new LoginUser(loginUser.getEmail(), "wrong" + loginUser.getPassword());
        assertInvalidLogin(wrongPassword);
    }

    @Step("Проверка успешной авторизации")
    private void assertLoginSuccess(Response response) {
        response.then().assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("accessToken", startsWith("Bearer"))
                .body("refreshToken", notNullValue())
                .body("user.email", equalTo(loginUser.getEmail()))
                .body("user.name", equalTo(createUser.getName()));
    }

    @Step("Проверка ошибки авторизации с неверными данными")
    private void assertInvalidLogin(LoginUser invalid) {
        Response response = UserController.loginUser(invalid);
        response.then().assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }

    @After
    public void tearDown() {
        UserController.deleteUser(token);
    }
}
