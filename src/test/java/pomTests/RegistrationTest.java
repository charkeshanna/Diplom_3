package pomTests;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pom.LoginPage;
import pom.StartPage;
import utils.WebDriverFactory;

public class RegistrationTest {
    private WebDriver driver;

    //потом исправить когда браузерами буду заниматься
    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = WebDriverFactory.createDriver(browser);
    }

    void userIsAbleToRegister() {
        //переходим на главную страницу
        //разобраться потом, где хранить адрес стартовой страницы
        driver.get("https://stellarburgers.education-services.ru");
        //создаем объект класса стартовой страницы
        StartPage startPage = new StartPage(driver);
        //кликаем на кнопку Войти в аккаунт
        startPage.clickLogInToAccountButton();
        //создаем объект класса страницы логина
        LoginPage loginPage = new LoginPage(driver);
        //дожидаемся загрузки страницы
        loginPage.waitForLoadPage();

    }
}
