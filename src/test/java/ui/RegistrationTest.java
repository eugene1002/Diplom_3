package ui;

import controllers.UserController;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import models.LoginUser;
import org.junit.Test;
import pages.RegistrationPage;

import static locators.LocatorsAuthPage.SIGN_IN_BUTTON;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;

import static com.codeborne.selenide.Selenide.$x;
import static locators.LocatorsRegistrationPage.INCORRECT_PASSWORD_TEXT;

@DisplayName("[UI] Регистрация пользователя")
public class RegistrationTest extends BaseUiTest {

    private final RegistrationPage registrationPage = new RegistrationPage();

    @Test
    @DisplayName("Успешная регистрация")
    public void successRegistrationTest() {
        LoginUser loginUser = new LoginUser(userEmail, userPassword);

        if (UserController.loginUser(loginUser).getStatusCode() == SC_OK) {
            UserController.deleteUser(UserController.getAccessToken(loginUser));
        }

        registrationPage.register(userName, userEmail, userPassword);
        assertLoginButtonVisibleAfterRegistration();
        UserController.deleteUser(UserController.getAccessToken(loginUser));
    }

    @Test
    @DisplayName("Некорректный пароль")
    public void failRegistrationTest() {
        registrationPage.register("Ichigo", "kurasaki_ichigo23@yandex.ru", "123");
        assertIncorrectPasswordMessage("Некорректный пароль");
    }

    @Step("Проверка: после регистрации отображается кнопка 'Войти'")
    private void assertLoginButtonVisibleAfterRegistration() {
        String actualText = $x(SIGN_IN_BUTTON).text();
        assertEquals("Войти", actualText);
    }

    @Step("Проверка ошибки при некорректном пароле: '{expected}'")
    private void assertIncorrectPasswordMessage(String expected) {
        String actual = $x(INCORRECT_PASSWORD_TEXT).text();
        assertEquals(expected, actual);
    }
}
