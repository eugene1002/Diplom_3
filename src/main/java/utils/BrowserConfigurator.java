package utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;

public class BrowserConfigurator {

    public static void configure(String browser) {
        Configuration.headless = ConfigReader.getBoolean("isHeadless");
        Configuration.browserSize = null; // отключаем фиксированное окно

        switch (browser.toLowerCase()) {
            case "chrome":
                Configuration.browser = "chrome";
                io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
                log("Настроен WebDriver для Chrome");
                break;

            case "firefox":
                Configuration.browser = "firefox";
                io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
                log("Настроен WebDriver для Firefox");
                break;

            case "yandex":
                Configuration.browser = "utils.YandexWebDriverProvider";
                log("Настроен WebDriverProvider для Yandex Browser");
                break;

            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browser);
        }

        log("Браузер: " + browser);
        log("Режим: " + (Configuration.headless ? "headless" : "headed"));

        // Разворачиваем окно вручную, если не headless
        if (!Configuration.headless) {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    WebDriver driver = WebDriverRunner.getWebDriver();
                    driver.manage().window().maximize();
                    log("Окно браузера развёрнуто вручную через .maximize()");
                } catch (Exception ignored) {
                }
            }));
        }
    }

    private static void log(String message) {
        System.out.println("[BrowserConfigurator] " + message);
    }
}
