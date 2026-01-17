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
@DisplayName("Проверка выхода из системы")
public class LogoutTest {
    private WebDriver driver;
    private String testEmail;
    private String testPassword;
    private String testName;
    private String accessToken;

    @BeforeEach
    public void setUp() {
        //браузер вынести
        driver = WebDriverFactory.createDriver();

        //Сгенерируем креды
        testEmail = TestDataGenerator.generateUsersEmail();
        testPassword = TestDataGenerator.generateUsersPassword();
        testName = testEmail + " name";

        //Зарегистрируем пользователя через API
        User user = new User(testEmail, testPassword, testName);
        accessToken = UserApiClient.registerUser(user);


        //Входим в систему
        driver.get(EnvData.getBaseUrl());
        StartPage startPage = new StartPage(driver);
        startPage.waitForLoadPage();
        startPage.clickLogInToAccountButton();

        //вводим креды и логинимся
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
