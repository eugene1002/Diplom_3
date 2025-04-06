import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static PageObject.LocatorsMainPage.*;
import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseTest {
    @Test
    @DisplayName("Переход к разделу Булки")
    public void successTransferToBunInConstructorTest() {
        driver.findElement(saucesTab).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(bunsTab));
        driver.findElement(bunsTab).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(bunInConstructor));
        String actualResult = driver.findElement(activeElementConstructor).getText();
        String expectedResult = "Булки";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    public void successTransferToSaucesInConstructorTest() {
        driver.findElement(saucesTab).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(saucesInConstructor));
        String actualResult = driver.findElement(activeElementConstructor).getText();
        String expectedResult = "Соусы";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    public void successTransferToFillingsInConstructorTest() {
        driver.findElement(fillingsTab).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(fillingsInConstructor));
        String actualResult = driver.findElement(activeElementConstructor).getText();
        String expectedResult = "Начинки";
        assertEquals(expectedResult, actualResult);
    }
}
