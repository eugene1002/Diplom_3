import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Browser {
    private static ChromeOptions optionsChrome = new ChromeOptions();
    private static FirefoxOptions optionsFirefox = new FirefoxOptions();

    public static WebDriver getWebDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                optionsChrome.addArguments("--no-sandbox");
                optionsChrome.addArguments("--disable-dev-shm-usage");
                return new ChromeDriver(optionsChrome);
            case "yandex":
//                WebDriverManager.chromedriver().setup();
                System.setProperty("webdriver.yandex.driver","src/test/resources/chromedriver");
                optionsChrome.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
                optionsChrome.addArguments("--no-sandbox");
                optionsChrome.addArguments("--disable-dev-shm-usage");
                return new ChromeDriver(optionsChrome);
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                optionsFirefox.addArguments("--no-sandbox");
                optionsFirefox.addArguments("--disable-dev-shm-usage");
                return new FirefoxDriver(optionsFirefox);
            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browserName);
        }
    }
}
