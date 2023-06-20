import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static PageObject.LocatorsAuthPage.forgotPasswordLink;
import static PageObject.LocatorsMainPage.*;
import static PageObject.LocatorsRegistrationPage.buttonRegister1;
import static PageObject.LocatorsRegistrationPage.signInlink;
import static org.junit.Assert.assertEquals;

public class LoginTest {
    private WebDriver driver;
    private static CreateUser createUser;
    private static String token;
    @Before
    public void init(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver"); // запуск в Хроме
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Убрать, если необходимо видеть прогон автотестов в браузере.
//        Для запуска тестов в Яндекс.Браузере выполнить (предварительно закомментировав строку запуск в Хроме:
//        System.setProperty("webdriver.chrome.driver","src/test/resources/yandexdriver");
//        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");

        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site/");
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        createUser = new CreateUser("kira_yoshikage777@yandex.ru","password12345","Kira Yoshikage");
        UserController.executeCreate(createUser);
        token = UserController.getUserToken(new LoginUser(createUser.getEmail(), createUser.getPassword()));
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void successLoginFromSignInPersonalCabinetTest() {
        AuthPage authPage = new AuthPage(driver);
        driver.findElement(buttonPersonalCabinet).click();
        authPage.loginFromMainPage(createUser.getEmail(), createUser.getPassword());
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(buttonMakeOrder));
        String actualResult = driver.findElement(buttonMakeOrder).getText();
        String expectedResult = "Оформить заказ";
        assertEquals(expectedResult,actualResult);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void successLoginFromPersonalCabinetTest() {
        AuthPage authPage = new AuthPage(driver);
        driver.findElement(buttonSignInAccount).click();
        authPage.loginFromMainPage(createUser.getEmail(), createUser.getPassword());
        String actualResult = driver.findElement(buttonMakeOrder).getText();
        String expectedResult = "Оформить заказ";
        assertEquals(expectedResult,actualResult);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void successLoginFromRegisterFormTest() {
        AuthPage authPage = new AuthPage(driver);
        driver.findElement(buttonPersonalCabinet).click();
        driver.findElement(buttonRegister1).click();
        driver.findElement(signInlink).click();
        authPage.loginFromMainPage(createUser.getEmail(), createUser.getPassword());
        String actualResult = driver.findElement(buttonMakeOrder).getText();
        String expectedResult = "Оформить заказ";
        assertEquals(expectedResult,actualResult);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void successLoginFromForgotPasswordFormTest() {
        AuthPage authPage = new AuthPage(driver);
        driver.findElement(buttonPersonalCabinet).click();
        driver.findElement(forgotPasswordLink).click();
        driver.findElement(signInlink).click();
        authPage.loginFromMainPage(createUser.getEmail(), createUser.getPassword());
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(buttonMakeOrder));
        String actualResult = driver.findElement(buttonMakeOrder).getText();
        String expectedResult = "Оформить заказ";
        assertEquals(expectedResult,actualResult);
    }

    @After
    public void teardown() {
        driver.quit();
        UserController.executeDelete(token);
    }
}
