package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;




public class WebDriverFactory {

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome");
        //String browser = "yandex";

        WebDriver driver;
        WebDriverManager.chromedriver().setup();

        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;

            case "yandex":
                WebDriverManager.chromedriver().setup();

                // Здесь необходимо прописать путь к yandexdriver на локальной машине (версия браузера должна совпадать с версией драйвера)
                // https://github.com/yandex/YandexDriver
                System.setProperty("webdriver.chrome.driver", "/usr/local/bin/yandexdriver");

                driver = new ChromeDriver();
                break;

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        driver.manage().window().maximize(); // optional
        return driver;
    }
}
