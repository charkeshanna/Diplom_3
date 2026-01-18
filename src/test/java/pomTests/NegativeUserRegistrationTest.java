package pomTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pom.LoginPage;
import pom.RegisterPage;
import pom.StartPage;
import utils.EnvData;
import utils.TestDataGenerator;
import utils.WebDriverFactory;
import static org.junit.jupiter.api.Assertions.assertTrue;
@DisplayName("Проверка регистрации пользователя с невалидными данными")
public class NegativeUserRegistrationTest {
    private WebDriver driver;
    private String email;
    private String shortPassword;
    private String name;


    @BeforeEach
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        //формирую тестовые данные
        email = TestDataGenerator.generateUsersEmail();
        shortPassword = TestDataGenerator.generateShortPassword();
        name = email + " Name";
    }

    @AfterEach
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }


    @DisplayName("Пользователь не может зарегистрироваться со слишком коротким паролем")
    @Test
    void userIsNotAbleToRegisterWithShortPassword() {

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
