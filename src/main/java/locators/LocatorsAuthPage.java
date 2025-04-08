package locators;

public class LocatorsAuthPage {

    public static final String EMAIL_INPUT        = "//label[text()='Email']/parent::div/child::input";
    public static final String PASSWORD_INPUT     = "//input[@type='password']";
    public static final String SIGN_IN_BUTTON     = "//button[text()='Войти']";
    public static final String FORGOT_PASSWORD    = "//a[contains(@href, 'forgot-password')]";
    public static final String REGISTER_LINK      = "//a[text()='Зарегистрироваться']";
}
