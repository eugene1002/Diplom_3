package utils;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.annotation.Nonnull;
import java.io.File;

import static utils.ConfigReader.getBoolean;

public class YandexWebDriverProvider implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        String driverPath = "src/test/resources/drivers/yandexdriver-132"; // путь к драйверу
        String browserBinaryPath = getYandexBrowserPath();

        System.setProperty("webdriver.chrome.driver", new File(driverPath).getAbsolutePath());

        ChromeOptions options = new ChromeOptions();
        options.setBinary(browserBinaryPath);

        if (getBoolean("isHeadless")) {
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
        }

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        return new ChromeDriver(options);
    }

    private String getYandexBrowserPath() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            return "C:\\Program Files (x86)\\Yandex\\YandexBrowser\\Application\\browser.exe";
        } else if (os.contains("mac")) {
            return "/Applications/Yandex.app/Contents/MacOS/Yandex";
        } else {
            return "/usr/bin/yandex-browser";
        }
    }
}
