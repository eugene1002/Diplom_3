import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static PageObject.LocatorsAuthPage.signInButton;
import static PageObject.LocatorsRegistrationPage.incorrectPasswordText;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация")
    public void successRegistrationTest() {
        RegisterPage objRegisterPage = new RegisterPage(driver);
        LoginUser loginUser = new LoginUser(userEmail, userPassword);

        if (UserController.executeLogin(loginUser).getStatusCode() == SC_OK) {
            UserController.executeDelete(loginUser);
        }

        objRegisterPage.register(userName, userEmail, userPassword);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(signInButton));
        String actualResult = driver.findElement(signInButton).getText();
        String expectedResult = "Войти";

        assertEquals(expectedResult, actualResult);
        UserController.executeDelete(loginUser);
    }

    @Test
    @DisplayName("Некорректный пароль")
    public void failRegistrationTest() {
        RegisterPage objRegisterPage = new RegisterPage(driver);
        objRegisterPage.register("Ichigo", "kurasaki_ichigo23@yandex.ru", "123");
        String actualResult = driver.findElement(incorrectPasswordText).getText();
        String expectedResult = "Некорректный пароль";
        assertEquals(expectedResult, actualResult);
    }
}
