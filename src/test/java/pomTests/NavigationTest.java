package pomTests;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pom.LoginPage;
import pom.ProfilePage;
import pom.StartPage;
import utils.EnvData;
import utils.TestDataGenerator;
import utils.UserApiClient;
import utils.WebDriverFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;
@DisplayName("Проверка основной навигации на портале")
public class NavigationTest {
    private WebDriver driver;
    private String testEmail;
    private String testPassword;
    private String testName;
    private String accessToken;

    @BeforeEach
    public void setUp() {
        driver = WebDriverFactory.createDriver();

        //Генерируем случайные данные для тестового пользователя
        testEmail = TestDataGenerator.generateUsersEmail();
        testPassword = TestDataGenerator.generateUsersPassword();
        testName = testEmail + " name";

        //регистрируем пользователя через API
        User user = new User(testEmail, testPassword, testName);
        accessToken = UserApiClient.registerUser(user);

        //Входим в систему через UI
        driver.get(EnvData.getBaseUrl());
        StartPage startPage = new StartPage(driver);
        startPage.waitForLoadPage();
        startPage.clickLogInToAccountButton();

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
        //Удаляем пользователя через API
        UserApiClient.deleteUser(accessToken);
        driver.quit();
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    void userIsAbleToNavigateToPersonalAccount() {
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
    void userIsAbleToNavigateToConstructorFromProfile() {
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
    void userIsAbleToNavigateToConstructorViaLogo() {
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
