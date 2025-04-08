package utils;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class YandexWebDriverProvider implements WebDriverProvider {

    @Override
    public ChromeDriver createDriver(org.openqa.selenium.Capabilities capabilities) {

    ChromeOptions options = new ChromeOptions();

        // Укажи путь к Yandex Browser
        String binaryPath = getYandexBinaryPath();
        String driverPath = getYandexChromedriverPath();

        options.setBinary(binaryPath);
        System.setProperty("webdriver.chrome.driver", driverPath);

        // Применяем capabilities
        options.merge(capabilities);

        return new ChromeDriver(options);
    }

    private static String getYandexBinaryPath() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac")) {
            return "/Applications/Yandex.app/Contents/MacOS/Yandex";
        } else if (os.contains("win")) {
            return "C:\\Program Files (x86)\\Yandex\\YandexBrowser\\Application\\browser.exe";
        } else {
            return "/usr/bin/yandex-browser";
        }
    }

    private static String getYandexChromedriverPath() {
        File file = new File("src/test/resources/drivers/yandexdriver-132");
        if (!file.exists()) {
            throw new RuntimeException("yandexdriver-132 не найден по пути: " + file.getAbsolutePath());
        }
        return file.getAbsolutePath();
    }
}