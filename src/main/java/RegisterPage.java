import org.openqa.selenium.WebDriver;

import static PageObject.LocatorsMainPage.buttonPersonalCabinet;
import static PageObject.LocatorsRegistrationPage.*;

public class RegisterPage {
    private WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnPersonalCabinetButton() {
        driver.findElement(buttonPersonalCabinet).click();
    }
    public void clickOnRegisterButton() {
        driver.findElement(buttonRegister1).click();
    }
    public void clickOnFinalRegisterButton() {
        driver.findElement(buttonRegister2).click();
    }

    public void setName(String name) {
        driver.findElement(fieldName).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(fieldPassword).sendKeys(password);
    }

    public void register(String name, String email, String password) {
        clickOnPersonalCabinetButton();
        clickOnRegisterButton();
        setName(name);
        setEmail(email);
        setPassword(password);
        clickOnFinalRegisterButton();
    }
}
