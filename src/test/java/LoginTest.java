import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static PageObject.LocatorsAuthPage.*;
import static PageObject.LocatorsMainPage.*;
import static PageObject.LocatorsRegistrationPage.signInlink;
import static org.junit.Assert.assertEquals;

public class LoginTest extends BaseTest {
    @Before
    public void beforeTest() {
        createTestUser();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void successLoginFromSignInPersonalCabinetTest() {
        loginUser(createUser.getEmail(), createUser.getPassword());
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(buttonMakeOrder));
        String actualResult = driver.findElement(buttonMakeOrder).getText();
        String expectedResult = "Оформить заказ";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void successLoginFromPersonalCabinetTest() {
        AuthPage authPage = new AuthPage(driver);
        driver.findElement(signInAccountButton).click();
        authPage.loginFromMainPage(createUser.getEmail(), createUser.getPassword());
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(buttonMakeOrder));
        String actualResult = driver.findElement(buttonMakeOrder).getText();
        String expectedResult = "Оформить заказ";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void successLoginFromRegisterFormTest() {
        AuthPage authPage = new AuthPage(driver);
        driver.findElement(personalCabinetButton).click();
        driver.findElement(registerLink).click();
        driver.findElement(signInlink).click();
        authPage.loginFromMainPage(createUser.getEmail(), createUser.getPassword());
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(buttonMakeOrder));
        String actualResult = driver.findElement(buttonMakeOrder).getText();
        String expectedResult = "Оформить заказ";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void successLoginFromForgotPasswordFormTest() {
        AuthPage authPage = new AuthPage(driver);
        driver.findElement(personalCabinetButton).click();
        driver.findElement(forgotPasswordLink).click();
        driver.findElement(signInlink).click();
        authPage.loginFromMainPage(createUser.getEmail(), createUser.getPassword());
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(buttonMakeOrder));
        String actualResult = driver.findElement(buttonMakeOrder).getText();
        String expectedResult = "Оформить заказ";
        assertEquals(expectedResult, actualResult);
    }

    @After
    public void afterTest() {
        UserController.executeDelete(token);
    }
}
