package ui;

import controllers.UserController;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.$x;
import static locators.LocatorsMainPage.PERSONAL_CABINET_BUTTON;
import static locators.LocatorsMainPage.SIGN_IN_ACCOUNT_BUTTON;
import static org.junit.Assert.assertEquals;

@DisplayName("[UI] Авторизация пользователя")
public class LoginTest extends BaseUiTest {

    private final RegistrationPage registrationPage = new RegistrationPage();

    @Before
    public void beforeTest() {
        createTestUser();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void successLoginFromPersonalCabinetButton() {
        $x(PERSONAL_CABINET_BUTTON).click();
        authPage.loginFromMainPage(createUser.getEmail(), createUser.getPassword());
        assertOrderButtonVisible();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void successLoginFromSignInAccountButton() {
        $x(SIGN_IN_ACCOUNT_BUTTON).click();
        authPage.loginFromMainPage(createUser.getEmail(), createUser.getPassword());
        assertOrderButtonVisible();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void successLoginFromRegisterForm() {
        $x(PERSONAL_CABINET_BUTTON).click();
        authPage.goToRegistration();
        registrationPage.goToLogin();
        authPage.loginFromMainPage(createUser.getEmail(), createUser.getPassword());
        assertOrderButtonVisible();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void successLoginFromForgotPasswordForm() {
        $x(PERSONAL_CABINET_BUTTON).click();
        authPage.goToForgotPassword();
        registrationPage.goToLogin();
        authPage.loginFromMainPage(createUser.getEmail(), createUser.getPassword());
        assertOrderButtonVisible();
    }

    @Step("Проверка отображения кнопки 'Оформить заказ'")
    private void assertOrderButtonVisible() {
        String expected = "Оформить заказ";
        String actual = mainPage.getMakeOrderButtonText();
        assertEquals(expected, actual);
    }

    @After
    public void afterTest() {
        UserController.deleteUser(token);
    }
}
