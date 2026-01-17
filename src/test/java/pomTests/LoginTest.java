package pomTests;

import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pom.*;
import utils.EnvData;
import utils.TestDataGenerator;
import utils.UserApiClient;
import utils.WebDriverFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;
@DisplayName("Проверка логина")
public class LoginTest {
    private WebDriver driver;
    private String testEmail;
    private String testPassword;
    private String testName;
    private String accessToken;


    //потом исправить когда браузерами буду заниматься
    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = WebDriverFactory.createDriver(browser);

        //Сгенерируем креды
         testEmail = TestDataGenerator.generateUsersEmail();
         testPassword = TestDataGenerator.generateUsersPassword();
         testName = testEmail + " name";

         //Зарегистрируем пользователя через API
        User user = new User(testEmail, testPassword, testName);
        accessToken = UserApiClient.registerUser(user);
    }

    @AfterEach
    public void tearDown() {
        //Удаляем пользователя через API
        UserApiClient.deleteUser(accessToken);
        driver.quit();
    }

    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной странице")
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

    @Test
    @DisplayName("Вход через кнопку 'Личный Кабинет'")
    void userIsAbleToLoginViaPersonalAccountButton() {
        //Переходим на главную страницу
        driver.get(EnvData.getBaseUrl());
        StartPage startPage = new StartPage(driver);
        startPage.waitForLoadPage();

        //Кликаем на кнопку Личный Кабинет
        startPage.clickProfileLink();

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

    @Test
    @DisplayName("Вход через ссылку на форме регистрации")
    void userIsAbleToLoginViaRegistrationFormButton() {
        //Переходим на главную страницу
        driver.get(EnvData.getBaseUrl());
        StartPage startPage = new StartPage(driver);
        startPage.waitForLoadPage();
        startPage.clickLogInToAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadPage();

        //Переходим на страницу регистрации
        loginPage.clickRegistrateLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitForLoadPage();

        //Кликаем на ссылку Войти
        registerPage.clickLoginLink();

        //Вводим данные для входа
        LoginPage loginPageFromReg = new LoginPage(driver);
        loginPageFromReg.waitForLoadPage();
        loginPageFromReg.inputEmail(testEmail);
        loginPageFromReg.inputPassword(testPassword);
        loginPageFromReg.clickLoginButton();

        //Проверяем успешный вход
        StartPage startPageAfterLogin = new StartPage(driver);
        startPageAfterLogin.waitForLoadPage();
        startPageAfterLogin.clickProfileLink();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoadPage();

        assertTrue(profilePage.isProfileDisplayed(),
                "Страница профиля должна отображаться после успешного входа");
    }

    @Test
    @DisplayName("Вход через ссылку на форме восстановления пароля")
    void userIsAbleToLoginViaPasswordRecoveryButton() {
        //Переходим на главную страницу
        driver.get(EnvData.getBaseUrl());
        StartPage startPage = new StartPage(driver);
        startPage.waitForLoadPage();
        startPage.clickLogInToAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadPage();

        //Переходим на страницу восстановления пароля
        loginPage.clickForgotPasswordLink();

        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        passwordRecoveryPage.waitForLoadPage();

        //Кликаем на ссылку Войти
        passwordRecoveryPage.clickLoginLink();

        //Вводим данные для входа
        LoginPage loginPageFromRecovery = new LoginPage(driver);
        loginPageFromRecovery.waitForLoadPage();
        loginPageFromRecovery.inputEmail(testEmail);
        loginPageFromRecovery.inputPassword(testPassword);
        loginPageFromRecovery.clickLoginButton();

        //Проверяем успешный вход
        StartPage startPageAfterLogin = new StartPage(driver);
        startPageAfterLogin.waitForLoadPage();
        startPageAfterLogin.clickProfileLink();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoadPage();

        assertTrue(profilePage.isProfileDisplayed(),
                "Страница профиля должна отображаться после успешного входа");
    }
}
