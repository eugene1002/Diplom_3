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

import static PageObject.LocatorsAuthPage.buttonSignIn;
import static PageObject.LocatorsMainPage.*;
import static PageObject.LocatorsPersonalCabinetPage.buttonExit;
import static PageObject.LocatorsPersonalCabinetPage.buttonSave;
import static org.junit.Assert.assertEquals;

public class PersonalCabinetTest {
    private WebDriver driver;
    private static CreateUser createUser;
    private static String token;

    public void loginUser(String email, String password) {
        AuthPage authPage = new AuthPage(driver);
        driver.findElement(buttonSignInAccount).click();
        authPage.loginFromMainPage(email, password);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(buttonSignInAccount));
    }

    public void createUser(String email, String password, String name) {
        createUser = new CreateUser(email, password, name);
        UserController.executeCreate(createUser);
        token = UserController.getUserToken(new LoginUser(createUser.getEmail(), createUser.getPassword()));
    }
    @Before
    public void init(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver"); // запуск в Хроме
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        /* Для запуска тестов в Яндекс.Браузере выполнить (предварительно закомментировав строку запуск в Хроме:
        System.setProperty("webdriver.chrome.driver","src/test/resources/yandexdriver");
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        */

        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site/");
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        createUser("kira_yoshikage777@yandex.ru","password12345","Kira Yoshikage");
        loginUser(createUser.getEmail(), createUser.getPassword());
    }
    @Test
    @DisplayName("Переход в личный кабинет")
    public void successTransferToPersonalCabinetTest() {
        driver.findElement(buttonPersonalCabinet).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(buttonSave));
        String actualResult = driver.findElement(buttonSave).getText();
        String expectedResult = "Сохранить";
        assertEquals(expectedResult,actualResult);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на Конструктор")
    public void successTransferToConstructorTest() {
        driver.findElement(buttonPersonalCabinet).click();
        driver.findElement(buttonConstructor).click();
        String actualResult = driver.findElement(textAssembleBurger).getText();
        String expectedResult = "Соберите бургер";
        assertEquals(expectedResult,actualResult);
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void successLogoutTest() {
        driver.findElement(buttonPersonalCabinet).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(buttonExit));
        driver.findElement(buttonExit).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(buttonSignIn));
        String actualResult = driver.findElement(buttonSignIn).getText();
        String expectedResult = "Войти";
        assertEquals(expectedResult,actualResult);
    }

    @After
    public void teardown() {
        driver.quit();
        UserController.executeDelete(token);
    }
}
