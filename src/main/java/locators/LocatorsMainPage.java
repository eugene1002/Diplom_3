package locators;

public class LocatorsMainPage {

    public static final String PERSONAL_CABINET_BUTTON     = "//a[contains(@href, 'account')]";
    public static final String SIGN_IN_ACCOUNT_BUTTON      = "//button[text()='Войти в аккаунт']";
    public static final String BUTTON_MAKE_ORDER           = "//button[text()='Оформить заказ']";
    public static final String CONSTRUCTOR_BUTTON          = "//p[text()='Конструктор']/parent::a";
    public static final String MAKE_BURGER_TITLE           = "//h1[text()='Соберите бургер']";

    public static final String BUNS_TAB                    = "//span[text()='Булки']/parent::div";
    public static final String SAUCES_TAB                  = "//span[text()='Соусы']/parent::div";
    public static final String FILLINGS_TAB                = "//span[text()='Начинки']/parent::div";

    public static final String ACTIVE_ELEMENT_CONSTRUCTOR  = "//div[contains(@class, 'tab_tab_type_current')]";
}
