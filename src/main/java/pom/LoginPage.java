package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

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

    // метод ожидания загрузки страницы --- проверить еще раз
    public void waitForLoadPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }
    //метод клика по линке Зарегистрироваться
    public void clickRegistrateLink() {
        driver.findElement(registrateLink).click();
    }
    //заполняем поле с Email
    public void inputEmail (String email) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(emailInputField));
        input.sendKeys(email);
    }
    //заполняем поле с password
    public void inputPassword(String password) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(passwordInputField));
        input.sendKeys(password);
    }

    //нажимаем кнопку Войти
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isLoginButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
    }
}



