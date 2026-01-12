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
    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of("Кто-то",
                        "f42№o00o9977-+a-l000012@email.com", "sdfs23456")
                /*Arguments.of("middle",
                        "Жорж", "Санд", "Вольная", "Университет",
                        "79454874560", "15.01.2026", "семеро суток")
                */
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void userIsAbleToRegister(String name, String email, String password) {
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
        // Первая проверка, что мы находимся на странице логина
        //assertTrue(driver.getCurrentUrl().contains("/login"));
        // проверяем, что мы действительно на странице логина - кнопка залогиниться отображается
        //assertTrue(loginPageAfterRegistration.isLoginButtonDisplayed());
        //возможно теперь должна быть проверка что можно залогиниться с использованием этих логина и пароля
        //вводим логин
        loginPageAfterRegistration.inputEmail(email);
        //вводим пароль
        loginPageAfterRegistration.inputPassword(password);
        //кликаем войти
        loginPageAfterRegistration.clickLoginButton();
        //создаем объект стартовой страницы
        StartPage startPageAfterRegistration = new StartPage(driver);
        //дожидаемся ее загрузки
        startPageAfterRegistration.waitForLoadPage();
        //переходим в личный кабинет
        startPageAfterRegistration.clickProfileLink();
        //создаем объект страницы личный кабинет
        ProfilePage profilePage = new ProfilePage(driver);
        //дожидаемся загрузки страницы
        profilePage.waitForLoadPage();
    }

}
