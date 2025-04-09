package api.user;

import api.BaseApiTest;
import controllers.UserController;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import models.CreateUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import io.restassured.response.Response;
import utils.TestDataFactory;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Создание пользователя через API (негативные сценарии)")
@RunWith(Parameterized.class)
public class CreateUserNegativeApiTest extends BaseApiTest {

    private final CreateUser invalidUser;

    public CreateUserNegativeApiTest(CreateUser invalidUser) {
        this.invalidUser = invalidUser;
    }

    @Parameterized.Parameters(name = "Негативный кейс: {0}")
    public static Object[][] invalidUsers() {
        return new Object[][]{
                {TestDataFactory.getUserWithoutEmail()},
                {TestDataFactory.getUserWithoutPassword()},
                {TestDataFactory.getUserWithoutName()}
        };
    }

    @Test
    @DisplayName("Ошибка при создании пользователя без обязательного поля")
    public void shouldNotCreateUserWithoutRequiredField() {
        Response response = UserController.createUser(invalidUser);
        assertMissingFieldError(response);
    }

    @Step("Проверка ошибки: обязательные поля не переданы")
    private void assertMissingFieldError(Response response) {
        response.then().assertThat()
                .statusCode(SC_FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }
}
