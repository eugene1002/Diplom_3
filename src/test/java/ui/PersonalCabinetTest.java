package ui;

import controllers.UserController;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.PersonalCabinetPage;

import static com.codeborne.selenide.Selenide.$x;
import static locators.LocatorsAuthPage.SIGN_IN_BUTTON;
import static locators.LocatorsMainPage.MAKE_BURGER_TITLE;
import static locators.LocatorsMainPage.CONSTRUCTOR_BUTTON;
import static org.junit.Assert.assertEquals;

@DisplayName("[UI] Личный кабинет пользователя")
public class PersonalCabinetTest extends BaseUiTest {

    private final PersonalCabinetPage cabinetPage = new PersonalCabinetPage();

    @Before
    public void beforeTest() {
        createTestUser();
        mainPage.goToPersonalCabinet();
        loginUser(createUser.getEmail(), createUser.getPassword());
        removeModalOverlayIfExists();
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void successTransferToPersonalCabinetTest() {
        mainPage.goToPersonalCabinet();
        String actual = cabinetPage.getSaveButtonText();
        assertEquals("Сохранить", actual);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void successTransferToConstructorTest() {
        mainPage.goToPersonalCabinet();
        $x(CONSTRUCTOR_BUTTON).click();
        String actual = $x(MAKE_BURGER_TITLE).text();
        assertEquals("Соберите бургер", actual);
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void successLogoutTest() {
        mainPage.goToPersonalCabinet();
        cabinetPage.clickExitButton();
        assertSignInVisible();
    }

    @Step("Проверка отображения кнопки 'Войти' после выхода")
    private void assertSignInVisible() {
        String actual = $x(SIGN_IN_BUTTON).text();
        assertEquals("Войти", actual);
    }

    @After
    public void afterTest() {
        UserController.deleteUser(token);
    }
}
