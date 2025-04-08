package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import models.CreateUser;
import models.LoginUser;
import net.datafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.AuthPage;
import pages.MainPage;
import utils.BrowserConfigurator;
import utils.ConfigReader;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static controllers.UserController.*;

public class BaseUiTest {

    protected final Faker faker = new Faker();
    protected final String userName = faker.name().firstName();
    protected final String userEmail = faker.internet().emailAddress();
    protected final String userPassword = faker.internet().password();

    protected static CreateUser createUser;
    protected static String token;

    protected MainPage mainPage;
    protected AuthPage authPage;

    @Before
    public void initPages() {
        mainPage = new MainPage();
        authPage = new AuthPage();
    }

    @Before
    @Step("Инициализация браузера и переход на сайт")
    public void initBrowser() {
        String browser = ConfigReader.get("browser");

        BrowserConfigurator.configure(browser);
        open(ConfigReader.get("baseUrl"));

        if (!Configuration.headless) {
            WebDriverRunner.getWebDriver().manage().window().maximize();
            System.out.println("[BaseTest] Окно браузера развёрнуто через .maximize()");
        }

        RestAssured.baseURI = ConfigReader.get("baseUrl");
    }

    @Step("Удалить модальное окно, если оно перекрывает элементы")
    protected void removeModalOverlayIfExists() {
        executeJavaScript(
                "const el = document.querySelector('.Modal_modal_overlay__x2ZCr'); if (el) el.remove();"
        );
    }

    @Step("Создание тестового пользователя и получение токена")
    protected void createTestUser() {
        createUser = new CreateUser(userEmail, userPassword, userName);
        createUser(createUser);
        token = getAccessToken(new LoginUser(createUser.getEmail(), createUser.getPassword()));
    }

    @Step("Вход в систему через UI")
    protected void loginUser(String email, String password) {
        new AuthPage().loginFromMainPage(email, password);
    }

    @After
    @Step("Закрытие браузера")
    public void closeBrowser() {
        Selenide.closeWebDriver();
    }

    // Снимок экрана при падении
    @Attachment(value = "Скриншот при ошибке", type = "image/png")
    public byte[] attachScreenshot() {
        try {
            File screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver())
                    .getScreenshotAs(OutputType.FILE);
            return FileUtils.readFileToByteArray(screenshot);
        } catch (IOException e) {
            return new byte[0];
        }
    }

    @Rule
    public TestWatcher watcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            attachScreenshot();
        }
    };
}