package api.user;

import api.BaseApiTest;
import controllers.UserController;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.ChangeUser;
import models.CreateUser;
import models.LoginUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.TestDataFactory;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("[API] Изменение данных пользователя")
public class ChangeUserDataApiTest extends BaseApiTest {

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
    @DisplayName("Изменение e-mail пользователя с авторизацией")
    public void changeEmailWithAuthTest() {
        ChangeUser newEmail = new ChangeUser("dio_brando1453a@yandex.ru", createUser.getName());
        Response response = UserController.changeUserData(loginUser, newEmail, true);
        assertSuccessChange(response, newEmail.getEmail(), createUser.getName());
    }

    @Test
    @DisplayName("Изменение имени пользователя с авторизацией")
    public void changeNameWithAuthTest() {
        ChangeUser newName = new ChangeUser(createUser.getEmail(), "Enrico Pucci");
        Response response = UserController.changeUserData(loginUser, newName, true);
        assertSuccessChange(response, createUser.getEmail(), newName.getName());
    }

    @Test
    @DisplayName("Изменение e-mail пользователя без авторизации")
    public void changeEmailWithoutAuthTest() {
        ChangeUser newEmail = new ChangeUser("dio_brando1453a@yandex.ru", createUser.getName());
        Response response = UserController.changeUserData(loginUser, newEmail, false);
        assertUnauthorizedChange(response);
    }

    @Test
    @DisplayName("Изменение имени пользователя без авторизации")
    public void changeNameWithoutAuthTest() {
        ChangeUser newName = new ChangeUser(createUser.getEmail(), "Enrico Pucci");
        Response response = UserController.changeUserData(loginUser, newName, false);
        assertUnauthorizedChange(response);
    }

    @Step("Проверка успешного изменения данных")
    private void assertSuccessChange(Response response, String expectedEmail, String expectedName) {
        response.then().assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("user.email", equalTo(expectedEmail))
                .body("user.name", equalTo(expectedName));
    }

    @Step("Проверка ошибки при отсутствии авторизации")
    private void assertUnauthorizedChange(Response response) {
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
