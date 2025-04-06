package PageObject;

import org.openqa.selenium.By;

public class LocatorsAuthPage {

    public static final By emailAuthField = By.xpath("//label[text()='Email']/parent::div/child::input");
    public static final By passwordAuthField = By.xpath("//input[@type='password']");
    public static final By signInButton = By.xpath("//button[text()='Войти']");
    public static final By forgotPasswordLink = By.xpath("//a[contains(@href, 'forgot-password')]");
    public static final By registerLink = By.xpath("//a[text()='Зарегистрироваться']");

}
