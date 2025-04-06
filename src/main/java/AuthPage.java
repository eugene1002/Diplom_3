import org.openqa.selenium.WebDriver;

import static PageObject.LocatorsAuthPage.*;

public class AuthPage {
    private WebDriver driver;

    public AuthPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setEmail(String email) {
        driver.findElement(emailAuthField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordAuthField).sendKeys(password);
    }

    public void clickOnAuthButton() {
        driver.findElement(signInButton).click();
    }

    public void loginFromMainPage(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickOnAuthButton();
    }
}
