import org.openqa.selenium.WebDriver;

import static PageObject.LocatorsAuthPage.registerLink;
import static PageObject.LocatorsMainPage.personalCabinetButton;
import static PageObject.LocatorsRegistrationPage.*;

public class RegisterPage {
    private WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnPersonalCabinetButton() {
        driver.findElement(personalCabinetButton).click();
    }

    public void clickOnRegisterLink() {
        driver.findElement(registerLink).click();
    }

    public void clickOnFinalRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void register(String name, String email, String password) {
        clickOnPersonalCabinetButton();
        clickOnRegisterLink();
        setName(name);
        setEmail(email);
        setPassword(password);
        clickOnFinalRegisterButton();
    }
}
