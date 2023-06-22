package PageObject;

import org.openqa.selenium.By;

public class LocatorsMainPage {
    public static final By buttonPersonalCabinet = By.xpath("//*[@id=\"root\"]/div/header/nav/a");
    public static final By buttonSignInAccount = By.xpath("//*[@id=\"root\"]/div/main/section[2]/div/button");
    public static final By buttonMakeOrder = By.xpath("/html/body/div/div/main/section[2]/div/button");
    public static final By buttonConstructor = By.xpath("//*[@id=\"root\"]/div/header/nav/ul/li[1]/a");
    public static final By textAssembleBurger = By.xpath("//*[@id=\"root\"]/div/main/section[1]/h1");
    public static final By bun = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[1]");
    public static final By bunInConstructor = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[1]");
    public static final By sauces = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[2]");
    public static final By saucesInConstructor = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[2]");
    public static final By filling = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[3]");
    public static final By fillingInConstructor = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[3]");
    public static final By activeElementConstructor = By.xpath("//div[contains(@class, 'tab_tab_type_current')]/span[contains(@class, 'text_type_main-default')]");
}
