import org.openqa.selenium.WebDriver;

import static PageObject.LocatorsAuthPage.*;
import static PageObject.LocatorsMainPage.buttonSignInAccount;

public class AuthPage {
    private WebDriver driver;

    public AuthPage(WebDriver driver) {
        this.driver = driver;
    }
    public void setEmail(String email) {
        driver.findElement(fieldEmailAuth).sendKeys(email);
    }
    public void setPassword(String password) {
        driver.findElement(fieldPasswordAuth).sendKeys(password);
    }
    public void clickOnAuthButton() {
        driver.findElement(buttonSignIn).click();
    }
    public void loginFromMainPage(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickOnAuthButton();
    }
}
