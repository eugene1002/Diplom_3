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

@DisplayName("[API] Создание пользователя (негативные сценарии)")
@RunWith(Parameterized.class)
public class CreateUserNegativeApiTest extends BaseApiTest {

    private final CreateUser invalidUser;
    private final String missingField;

    public CreateUserNegativeApiTest(CreateUser invalidUser, String missingField) {
        this.invalidUser = invalidUser;
        this.missingField = missingField;
    }


    @Parameterized.Parameters(name = "Отсутствует поле: {1}")
    public static Object[][] invalidUsers() {
        return new Object[][]{
                {TestDataFactory.getUserWithoutEmail(), "email"},
                {TestDataFactory.getUserWithoutPassword(), "password"},
                {TestDataFactory.getUserWithoutName(), "name"}
        };
    }

    @Test
    @DisplayName("Негативный сценарий: создание пользователя без поля")
    public void shouldNotCreateUserWithoutRequiredField() {
        Response response = UserController.createUser(invalidUser);
        assertMissingFieldError(response, missingField);
    }

    @Step("Проверка ошибки: отсутствует поле {missingField}")
    private void assertMissingFieldError(Response response, String missingField) {
        response.then().assertThat()
                .statusCode(SC_FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }
}
