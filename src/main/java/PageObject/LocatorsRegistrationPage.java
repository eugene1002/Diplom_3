package PageObject;

import org.openqa.selenium.By;

public class LocatorsRegistrationPage {
    public static final By nameField = By.xpath("//label[text()='Имя']/parent::div/child::input");
    public static final By emailField = By.xpath("//label[text()='Email']/parent::div/child::input");
    public static final By passwordField = By.xpath("//label[text()='Пароль']/parent::div/child::input");
    public static final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    public static final By incorrectPasswordText = By.xpath("//p[contains(@class, 'input__error')]");
    public static final By signInlink = By.xpath("//a[text()='Войти']");


}
