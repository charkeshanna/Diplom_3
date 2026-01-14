package pomTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pom.LoginPage;
import pom.ProfilePage;
import pom.RegisterPage;
import pom.StartPage;
import utils.EnvData;
import utils.TestDataGenerator;
import utils.WebDriverFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest {
    private WebDriver driver;

    //потом исправить когда браузерами буду заниматься
    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = WebDriverFactory.createDriver(browser);
    }

    @AfterEach
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }

    @Test
    void userIsAbleToRegisterWithValidCredentials() {
        //формирую тестовые данные
        String email = TestDataGenerator.generateUsersEmail();
        String password = TestDataGenerator.generateUsersPassword();
        String name = email + " name";
        //переходим на главную страницу
        //разобраться потом, где хранить адрес стартовой страницы
        driver.get(EnvData.getBaseUrl());
        //создаем объект класса стартовой страницы
        StartPage startPage = new StartPage(driver);
        //кликаем на кнопку Войти в аккаунт
        startPage.clickLogInToAccountButton();
        //создаем объект класса страницы логина
        LoginPage loginPage = new LoginPage(driver);
        //дожидаемся загрузки страницы
        loginPage.waitForLoadPage();
        //кликаем на линку Зарегистрироваться
        loginPage.clickRegistrateLink();
        //создаем объект класса страницы регистрации
        RegisterPage registerPage = new RegisterPage(driver);
        //добавить метод ожидания загрузки страницы
        registerPage.waitForLoadPage();
        //вводим имя
        registerPage.inputName(name);
        //вводим email
        registerPage.inputEmail(email);
        //вводим пароль
        registerPage.inputPassword(password);
        //нажимаем на кнопку Зарегистрироваться
        registerPage.clickRegistrationButton();
        //после этого мы попадаем на страницу логина
        LoginPage loginPageAfterRegistration = new LoginPage(driver);
        //дожидаемся загрузки страницы
        loginPageAfterRegistration.waitForLoadPage();
        //Первая проверка, что мы находимся на странице логина
        assertTrue(loginPageAfterRegistration.isLoginButtonDisplayed(),
                "Кнопка входа должна отображаться, если была успешная регистрации");
        //теперь проверка что можем с этими кредами залогиниться
        loginPageAfterRegistration.inputEmail(email);
        loginPageAfterRegistration.inputPassword(password);
        loginPageAfterRegistration.clickLoginButton();
        //дожидаемся загрузки стартовой страницы
        StartPage startPageAfterRegistration = new StartPage(driver);
        startPageAfterRegistration.waitForLoadPage();
        //переходим в личный кабинет
        startPageAfterRegistration.clickProfileLink();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoadPage();
        //теперь убедиться что страница профиля отобразится
        assertTrue(profilePage.isProfileDisplayed(),
                "Страница профиля должна отображаться после успешного входа");
    }


    @Test
    void userIsNotAbleToRegisterWithShortPassword() {
        //формирую тестовые данные
        String email = TestDataGenerator.generateUsersEmail();
        String shortPassword = TestDataGenerator.generateShortPassword();
        String name = email + " name";
        //переходим на главную страницу
        //разобраться потом, где хранить адрес стартовой страницы
        driver.get(EnvData.getBaseUrl());
        //создаем объект класса стартовой страницы
        StartPage startPage = new StartPage(driver);
        //кликаем на кнопку Войти в аккаунт
        startPage.clickLogInToAccountButton();
        //создаем объект класса страницы логина
        LoginPage loginPage = new LoginPage(driver);
        //дожидаемся загрузки страницы
        loginPage.waitForLoadPage();
        //кликаем на линку Зарегистрироваться
        loginPage.clickRegistrateLink();
        //создаем объект класса страницы регистрации
        RegisterPage registerPage = new RegisterPage(driver);
        //добавить метод ожидания загрузки страницы
        registerPage.waitForLoadPage();
        //вводим имя
        registerPage.inputName(name);
        //вводим email
        registerPage.inputEmail(email);
        //вводим пароль
        registerPage.inputPassword(shortPassword);
        //нажимаем на кнопку Зарегистрироваться
        registerPage.clickRegistrationButton();
        //проверим что есть ошибка
        assertTrue(registerPage.isPasswordErrorDisplayed(),
                "Сообщение об ошибке должно появиться, если введен короткий пароль");

        //Проверяю текст сообщения об ошибке
        String errorMessage = registerPage.getPasswordErrorMessage();
        assertTrue(errorMessage.contains("Некорректный пароль"),
                "Сообщение об ошибке должно быть 'Некорректный пароль'");
    }
}
