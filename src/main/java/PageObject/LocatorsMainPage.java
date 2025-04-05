package PageObject;

import org.openqa.selenium.By;

public class LocatorsMainPage {
    public static final By personalCabinetButton = By.xpath("//a[contains(@href, 'account')]");
    public static final By signInAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    public static final By buttonMakeOrder = By.xpath("//button[text()='Оформить заказ']");
    public static final By constructorButton = By.xpath("//p[text()='Конструктор']/parent::a");
    public static final By makeBurgerTitle = By.xpath("//h1[text()='Соберите бургер']");
    public static final By bunsTab = By.xpath("//span[text()='Булки']/parent::div");
    public static final By bunInConstructor = By.xpath("//h2[text()='Булки']");
    public static final By saucesTab = By.xpath("//span[text()='Соусы']/parent::div");
    public static final By saucesInConstructor = By.xpath("//h2[text()='Соусы']");
    public static final By fillingsTab = By.xpath("//span[text()='Начинки']/parent::div");
    public static final By fillingsInConstructor = By.xpath("//h2[text()='Начинки']");
    public static final By activeElementConstructor = By.xpath("//div[contains(@class, 'tab_tab_type_current')]");
}
