package pom;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    //конструктор
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //поле Email
    private By emailInputField = By.xpath("//input[@name='name']");
    //поле Password
    private By passwordInputField = By.xpath("//input[@type='password']");
    //кнопка Войти
    private By loginButton = By.xpath("//button[text()='Войти']");
    //ссылка Зарегистрироваться
    private By registrateLink = By.linkText("Зарегистрироваться");
    //Ссылка восстановить пароль
    private By forgotPasswordLink = By.linkText("Восстановить пароль");


    // метод ожидания загрузки страницы --- проверить еще раз
    @Step("Ожидание загрузки страницы входа")
    public void waitForLoadPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    //метод клика по линке Зарегистрироваться
    @Step("Клик по ссылке 'Зарегистрироваться'")
    public void clickRegistrateLink() {
        driver.findElement(registrateLink).click();
    }

    //заполняем поле с Email
    @Step("Ввод email: {email}")
    public void inputEmail (String email) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(emailInputField));
        input.sendKeys(email);
    }
    //заполняем поле с password
    @Step("Ввод пароля")
    public void inputPassword(String password) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(passwordInputField));
        input.sendKeys(password);
    }

    //нажимаем кнопку Войти
    @Step("Клик по кнопке 'Войти'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    //проверяем отображение кнопки Войти
    @Step("Проверка отображения кнопки 'Войти'")
    public boolean isLoginButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
    }

    //метод клика по ссылке Восстановить пароль
    @Step("Клик по ссылке 'Восстановить пароль'")
    public void clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
    }
}



