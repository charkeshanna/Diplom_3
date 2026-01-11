package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class RegisterPage {
    private WebDriver driver;

    //конструктор
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    //поле Имя
    private By nameInputField = By.xpath("(//input[contains(@class,'input__textfield')])[1]");
    //поле Email
    private By emailInputField = By.xpath("(//input[contains(@class,'input__textfield')])[2]");
    //поле Password
    private By passwordInputField = By.name("password");
    //кнопка Зарегистрироваться
    private By registrateButton = By.xpath("//button[text()='Зарегистрироваться']");

    // метод ожидания загрузки страницы --- проверить еще раз
    public void waitForLoadPage(){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Зарегистрироваться']")));
    }

    //заполняем поле Имя
    public void inputName(String name) {
        driver.findElement(nameInputField).sendKeys(name);
    }
    //заполняем поле с Email
    public void inputEmail (String email) {
        driver.findElement(emailInputField).sendKeys(email);
    }
    //заполняем поле с password
    public void inputPassword(String password) {
        driver.findElement(passwordInputField).sendKeys(password);
    }

    //метод клика по кнопке Зарегистрироваться
    public void clickRegistrationButton() {
        driver.findElement(registrateButton).click();
    }

}
