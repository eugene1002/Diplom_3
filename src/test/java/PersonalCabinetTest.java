import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static PageObject.LocatorsAuthPage.signInButton;
import static PageObject.LocatorsMainPage.*;
import static PageObject.LocatorsPersonalCabinetPage.exitButton;
import static PageObject.LocatorsPersonalCabinetPage.saveButton;
import static org.junit.Assert.assertEquals;

public class PersonalCabinetTest extends BaseTest {

    @Before
    public void beforeTest() {
        createTestUser();
        loginUser(createUser.getEmail(), createUser.getPassword());
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void successTransferToPersonalCabinetTest() {
        driver.findElement(personalCabinetButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(saveButton));
        String actualResult = driver.findElement(saveButton).getText();
        String expectedResult = "Сохранить";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на Конструктор")
    public void successTransferToConstructorTest() {
        driver.findElement(personalCabinetButton).click();
        driver.findElement(constructorButton).click();
        String actualResult = driver.findElement(makeBurgerTitle).getText();
        String expectedResult = "Соберите бургер";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void successLogoutTest() {
        driver.findElement(personalCabinetButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(exitButton));
        driver.findElement(exitButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(signInButton));
        String actualResult = driver.findElement(signInButton).getText();
        String expectedResult = "Войти";
        assertEquals(expectedResult, actualResult);
    }

    @After
    public void afterTest() {
        UserController.executeDelete(token);
    }
}
