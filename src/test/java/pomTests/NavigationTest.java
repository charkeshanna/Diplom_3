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

public class NavigationTest {
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
    @DisplayName("Переход в личный кабинет")
    void testNavigateToPersonalAccount() {
        //Кликаем на ссылку Личный Кабинет
        StartPage startPage = new StartPage(driver);
        startPage.clickProfileLink();

        //Проверяем, что отображается страница профиля
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoadPage();

        assertTrue(profilePage.isProfileDisplayed(),
                "Страница профиля должна отображаться");
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по кнопке 'Конструктор'")
    void testNavigateToConstructorViaButton() {
        //Переходим в личный кабинет
        StartPage startPage = new StartPage(driver);
        startPage.clickProfileLink();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoadPage();

        //Кликаем на кнопку Конструктор
        profilePage.clickConstructorButton();

        //Проверяем, что отображается главная страница с конструктором
        StartPage mainPage = new StartPage(driver);
        mainPage.waitForLoadPage();

        //Проверяем, что вкладка Булки активна по умолчанию
        assertTrue(mainPage.isBunsTabActive(),
                "Главная страница с конструктором должна отображаться");
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по логотипу")
    void testNavigateToConstructorViaLogo() {
        //Переходим в личный кабинет
        StartPage startPage = new StartPage(driver);
        startPage.clickProfileLink();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoadPage();

        //Кликаем на логотип Stellar Burgers
        profilePage.clickLogo();

        //Проверяем, что отображается главная страница с конструктором
        StartPage mainPage = new StartPage(driver);
        mainPage.waitForLoadPage();

        //Проверяем, что вкладка Булки активна по умолчанию
        assertTrue(mainPage.isBunsTabActive(),
                "Главная страница с конструктором должна отображаться");
    }

}
