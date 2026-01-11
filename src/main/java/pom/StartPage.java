package pom;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public StartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    //кнопка Войти в аккаунт
    private By logInToAccountButton = By.xpath("//button[text()='Войти в аккаунт']");

    //метод клика по кнопке Войти в аккаунт
    public void clickLogInToAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logInToAccountButton)).click();
    }
}
