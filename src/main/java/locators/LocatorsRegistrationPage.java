package locators;

public class LocatorsRegistrationPage {

    public static final String NAME_INPUT               = "//label[text()='Имя']/parent::div/child::input";
    public static final String EMAIL_INPUT              = "//label[text()='Email']/parent::div/child::input";
    public static final String PASSWORD_INPUT           = "//label[text()='Пароль']/parent::div/child::input";
    public static final String REGISTER_BUTTON          = "//button[text()='Зарегистрироваться']";
    public static final String INCORRECT_PASSWORD_TEXT  = "//p[contains(@class, 'input__error')]";
    public static final String SIGN_IN_LINK             = "//a[text()='Войти']";
}
