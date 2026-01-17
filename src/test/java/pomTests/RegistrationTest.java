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
        driver.get(EnvData.getBaseUrl());

        //создаем объект класса стартовой страницы
        StartPage startPage = new StartPage(driver);
        startPage.waitForLoadPage();

        //кликаем на кнопку Войти в аккаунт
        startPage.clickLogInToAccountButton();

        //создаем объект класса страницы логина
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadPage();

        //кликаем на линку Зарегистрироваться
        loginPage.clickRegistrateLink();

        //создаем объект класса страницы регистрации
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitForLoadPage();

        //вводим creadentials
        registerPage.inputName(name);
        registerPage.inputEmail(email);
        registerPage.inputPassword(password);

        //нажимаем на кнопку Зарегистрироваться
        registerPage.clickRegistrationButton();

        //после регистрации мы попадаем на страницу логина
        LoginPage loginPageAfterRegistration = new LoginPage(driver);
        loginPageAfterRegistration.waitForLoadPage();

        //Первая проверка, что мы находимся на странице логина
        assertTrue(loginPageAfterRegistration.isLoginButtonDisplayed(),
                "Кнопка входа должна отображаться, если была успешная регистрации");

        //Теперь проверяем, что можем залогиниться с зарегистрированными данными
        //Вводим credentials
        loginPageAfterRegistration.inputEmail(email);
        loginPageAfterRegistration.inputPassword(password);
        loginPageAfterRegistration.clickLoginButton();

        //дожидаемся загрузки стартовой страницы
        StartPage startPageAfterRegistration = new StartPage(driver);
        startPageAfterRegistration.waitForLoadPage();

        //переходим в личный кабинет
        startPageAfterRegistration.clickProfileLink();

        //создаем объект страницы Личный кабинет
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoadPage();

        //Проверяем, что страница профиля отображается
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
        driver.get(EnvData.getBaseUrl());

        //создаем объект класса стартовой страницы
        StartPage startPage = new StartPage(driver);
        startPage.waitForLoadPage();

        //кликаем на кнопку Войти в аккаунт
        startPage.clickLogInToAccountButton();

        //создаем объект класса страницы логина
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadPage();

        //кликаем на линку Зарегистрироваться
        loginPage.clickRegistrateLink();

        //создаем объект класса страницы регистрации
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitForLoadPage();

        //Вводим данные для регистрации
        registerPage.inputName(name);
        registerPage.inputEmail(email);
        registerPage.inputPassword(shortPassword);

        //нажимаем на кнопку Зарегистрироваться
        registerPage.clickRegistrationButton();

        //Проверяем, что отображается ошибка
        assertTrue(registerPage.isPasswordErrorDisplayed(),
                "Сообщение об ошибке должно появиться, если введен короткий пароль");

        //Проверяю текст сообщения об ошибке
        String errorMessage = registerPage.getPasswordErrorMessage();
        assertTrue(errorMessage.contains("Некорректный пароль"),
                "Сообщение об ошибке должно быть 'Некорректный пароль'");
    }
}
