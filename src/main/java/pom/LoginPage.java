package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class LoginPage {
    private WebDriver driver;

    //конструктор
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //поле Email
    private By emailInputField = By.name("name");
    //поле Password
    private By passwordInputField = By.name("Пароль");

    // метод ожидания загрузки страницы --- проверить еще раз
    public void waitForLoadPage(){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@text()='Вход']")));
    }
    //заполняем поле с Email
    public void inputEmail (String email) {
        driver.findElement(emailInputField).sendKeys(email);
    }
}
