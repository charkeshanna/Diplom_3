package pomTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

public class LoginTest {
    private WebDriver driver;
    private String testEmail;
    private String testPassword;
    private String testName;


    //потом исправить когда браузерами буду заниматься
    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = WebDriverFactory.createDriver(browser);

        // сгенерим креды
         testEmail = TestDataGenerator.generateUsersEmail();
         testPassword = TestDataGenerator.generateUsersPassword();
         testName = testEmail + " name";
        //зарегистрируем пользователя
        driver.get(EnvData.getBaseUrl());
        StartPage startPage = new StartPage(driver);
        startPage.waitForLoadPage();
        startPage.clickLogInToAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadPage();
        loginPage.clickRegistrateLink();
        //потом возможно лучше отельный метод общий написать
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitForLoadPage();
        registerPage.inputName(testName);
        registerPage.inputEmail(testEmail);
        registerPage.inputPassword(testPassword);
        registerPage.clickRegistrationButton();

        //Ждем перенаправления на страницу логина
        LoginPage loginPageAfterReg = new LoginPage(driver);
        loginPageAfterReg.waitForLoadPage();
    }

    @AfterEach
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }

    @Test
    void userIsAbleToLoginViaMainPageButton() {
        //Переходим на главную страницу
        driver.get(EnvData.getBaseUrl());
        StartPage startPage = new StartPage(driver);
        startPage.waitForLoadPage();

        //Кликаем на кнопку Войти в аккаунт
        startPage.clickLogInToAccountButton();

        //Вводим данные для входа
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadPage();
        loginPage.inputEmail(testEmail);
        loginPage.inputPassword(testPassword);
        loginPage.clickLoginButton();

        //Проверяем успешный вход - переходим на страницу профиля
        StartPage startPageAfterLogin = new StartPage(driver);
        startPageAfterLogin.waitForLoadPage();
        startPageAfterLogin.clickProfileLink();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoadPage();

        assertTrue(profilePage.isProfileDisplayed(),
                "Страница профиля должна отображаться после успешного входа");
    }
}
