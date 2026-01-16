package pomTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pom.LoginPage;
import pom.ProfilePage;
import pom.RegisterPage;
import pom.StartPage;
import utils.EnvData;
import utils.TestDataGenerator;
import utils.WebDriverFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogOutTest {
    private WebDriver driver;
    private String testEmail;
    private String testPassword;
    private String testName;

    @BeforeEach
    public void setUp() {
        //браузер вынести
        String browser = System.getProperty("browser", "chrome");
        driver = WebDriverFactory.createDriver(browser);

        //Генерируем случайные данные для тестового пользователя
        testEmail = TestDataGenerator.generateUsersEmail();
        testPassword = TestDataGenerator.generateUsersPassword();
        testName = testEmail + " name";

        //Регистрируем и входим в систему
        driver.get(EnvData.getBaseUrl());
        StartPage startPage = new StartPage(driver);
        startPage.waitForLoadPage();
        startPage.clickLogInToAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadPage();
        loginPage.clickRegistrateLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitForLoadPage();
        registerPage.inputName(testName);
        registerPage.inputEmail(testEmail);
        registerPage.inputPassword(testPassword);
        registerPage.clickRegistrationButton();

        //Входим в систему
        LoginPage loginPageAfterReg = new LoginPage(driver);
        loginPageAfterReg.waitForLoadPage();
        loginPageAfterReg.inputEmail(testEmail);
        loginPageAfterReg.inputPassword(testPassword);
        loginPageAfterReg.clickLoginButton();

        //Ждем загрузки главной страницы
        StartPage mainPage = new StartPage(driver);
        mainPage.waitForLoadPage();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Выход из личного кабинета")
    void testLogoutFromPersonalAccount() {
        //Переходим в личный кабинет
        StartPage startPage = new StartPage(driver);
        startPage.clickProfileLink();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoadPage();

        //Кликаем на кнопку Выход
        profilePage.clickLogoutButton();

        //Проверяем, что перенаправлены на страницу логина
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadPage();

        //Проверяем, что кнопка входа отображается
        assertTrue(loginPage.isLoginButtonDisplayed(),
                "После выхода должна отображаться страница входа");
    }
}
