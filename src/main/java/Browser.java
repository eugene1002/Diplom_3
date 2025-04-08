import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Browser {
    private static ChromeOptions optionsChrome = new ChromeOptions();
    private static FirefoxOptions optionsFirefox = new FirefoxOptions();

    // Метод без параметра headless
    public static WebDriver getWebDriver(String browserName) {
        return getWebDriver(browserName, null); // По умолчанию headless = null
    }

    // Метод с параметром headless
    public static WebDriver getWebDriver(String browserName, String mode) {
        boolean headless = mode != null && mode.equalsIgnoreCase("headless");

        switch (browserName.toLowerCase()) {
            case "chrome":
                setupChromeOptions(headless);
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(optionsChrome);
            case "yandex":
                setupChromeOptions(headless);
                optionsChrome.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(optionsChrome);
            case "firefox":
                setupFirefoxOptions(headless);
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(optionsFirefox);
            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browserName);
        }
    }

    private static void setupChromeOptions(boolean headless) {
        if (headless) {
            optionsChrome.addArguments("--headless");
        }
        optionsChrome.addArguments("--no-sandbox");
        optionsChrome.addArguments("--disable-dev-shm-usage");
    }

    private static void setupFirefoxOptions(boolean headless) {
        if (headless) {
            optionsFirefox.addArguments("--headless");
        }
        optionsFirefox.addArguments("--no-sandbox");
        optionsFirefox.addArguments("--disable-dev-shm-usage");
    }
}