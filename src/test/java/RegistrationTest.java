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
import static PageObject.LocatorsRegistrationPage.textIncorrectPassword;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;

public class RegistrationTest {
    private WebDriver driver;
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
        }

    @Test
    @DisplayName("Успешная регистрация")
    public void successRegistrationTest() {
        RegisterPage objRegisterPage = new RegisterPage(driver);
        String name = "Ichigo";
        String email = "kurasaki_ichigo2027@yandex.ru";
        String password = "123456!password";
        LoginUser loginUser = new LoginUser(email, password);
        if (UserController.executeLogin(loginUser).getStatusCode() == SC_OK) {
            UserController.executeDelete(loginUser);
        }
            objRegisterPage.register(name, email, password);
            new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(buttonSignIn));
            String actualResult = driver.findElement(buttonSignIn).getText();
            String expectedResult = "Войти";
            assertEquals(expectedResult, actualResult);
            UserController.apiDeleteUser(email, password);
    }

    @Test
    @DisplayName("Некорректный пароль")
    public void failRegistrationTest() {
        RegisterPage objRegisterPage = new RegisterPage(driver);
        objRegisterPage.register("Ichigo","kurasaki_ichigo23@yandex.ru","123");
        String actualResult = driver.findElement(textIncorrectPassword).getText();
        String expectedResult = "Некорректный пароль";
        assertEquals(expectedResult,actualResult);
    }
    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
