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

import static PageObject.LocatorsMainPage.*;
import static org.junit.Assert.assertEquals;

public class ConstructorTest {
    private WebDriver driver;
    @Before
    public void init(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver"); // запуск в Хроме
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
//        Для запуска тестов в Яндекс.Браузере выполнить (предварительно закомментировав строку запуск в Хроме:
//        System.setProperty("webdriver.chrome.driver","src/test/resources/yandexdriver");
//        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");

        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site/");
    }
    @Test
    @DisplayName("Переход к разделу Булки")
    public void successTransferToBunInConstructorTest() {
        driver.findElement(sauces).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(bun));
        driver.findElement(bun).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(bunInConstructor));
        String actualResult = driver.findElement(activeElementConstructor).getText();
        String expectedResult = "Булки";
        assertEquals(expectedResult,actualResult);
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    public void successTransferToSaucesInConstructorTest() {
        driver.findElement(sauces).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(saucesInConstructor));
        String actualResult = driver.findElement(activeElementConstructor).getText();
        String expectedResult = "Соусы";
        assertEquals(expectedResult,actualResult);
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    public void successTransferToFillingsInConstructorTest() {
        driver.findElement(filling).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(fillingInConstructor));
        String actualResult = driver.findElement(activeElementConstructor).getText();
        String expectedResult = "Начинки";
        assertEquals(expectedResult,actualResult);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
