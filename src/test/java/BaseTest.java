import io.qameta.allure.Step;
import io.restassured.RestAssured;
import net.datafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static PageObject.LocatorsAuthPage.signInButton;
import static PageObject.LocatorsMainPage.signInAccountButton;

public class BaseTest {

    private final static String URL = "https://stellarburgers.nomoreparties.site/";
    protected static CreateUser createUser;
    protected static String token;
    protected WebDriver driver;
    protected Faker faker = new Faker();
    protected String userName = faker.name().firstName();
    protected String userEmail = faker.internet().emailAddress();
    protected String userPassword = faker.internet().password();

    /*

    Здесь нужно выбрать браузер, на котором будут запускаться тесты.
    В метод getWebDriver() в качестве аргумента передать один из вариантов ниже:
        - chrome - для запуска в браузере Google Chrome;
        - yandex - для запуска в браузере Yandex Browser;
        - firefox - для запуска в браузере Mozilla Firefox.

   Также есть возможность запуска в режиме headless. Для этого в метод getWebDriver()
   надо передать второй параметр со значением --headless.

     */

    @Before
    @Step("Инициализация драйвера браузера")
    public void initBrowser() {
        driver = Browser.getWebDriver("chrome");
        driver.manage().window().maximize();
        driver.get(URL);
        RestAssured.baseURI = URL;
    }

    @Step("Создать нового пользователя и получить его токен")
    protected void createTestUser() {
        createUser = new CreateUser(userEmail, userPassword, userName);
        UserController.executeCreate(createUser);
        token = UserController.getUserToken(new LoginUser(createUser.getEmail(), createUser.getPassword()));
    }

    @Step("Авторизоваться в системе")
    protected void loginUser(String email, String password) {
        AuthPage authPage = new AuthPage(driver);
        driver.findElement(signInAccountButton).click();
        authPage.loginFromMainPage(email, password);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(signInButton));
    }

    @After
    @Step("Закрыть браузер")
    public void closeBrowser() {
        driver.quit();
    }
}
