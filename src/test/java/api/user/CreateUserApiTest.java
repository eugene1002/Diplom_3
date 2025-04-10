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


@DisplayName("[API] Создание пользователя (позитивные сценарии)")
public class CreateUserApiTest extends BaseApiTest {

    private CreateUser createUser;
    private LoginUser loginUser;

    @Before
    public void setUp() {
        createUser = TestDataFactory.getValidUser();
        loginUser = TestDataFactory.getLoginFrom(createUser);

        // На случай, если пользователь уже существует
        if (UserController.loginUser(loginUser).getStatusCode() == SC_OK) {
            UserController.deleteUser(loginUser);
        }
    }

    @Test
    @DisplayName("Создание уникального пользователя")
    public void successCreateUserTest() {
        Response response = UserController.createUser(createUser);
        assertSuccessfulRegistration(response);
    }

    @Test
    @DisplayName("Попытка повторной регистрации пользователя")
    public void failCreateDuplicateUserTest() {
        UserController.createUser(createUser);
        Response response = UserController.createUser(createUser);
        assertDuplicateRegistration(response);
    }

    @Step("Проверка успешной регистрации")
    private void assertSuccessfulRegistration(Response response) {
        response.then().assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("user.email", equalTo(createUser.getEmail()))
                .body("user.name", equalTo(createUser.getName()))
                .body("accessToken", startsWith("Bearer"))
                .body("refreshToken", notNullValue());
    }

    @Step("Проверка ошибки при регистрации существующего пользователя")
    private void assertDuplicateRegistration(Response response) {
        response.then().assertThat()
                .statusCode(SC_FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo("User already exists"));
    }

    @After
    public void tearDown() {
        UserController.deleteUser(loginUser);
    }
}
