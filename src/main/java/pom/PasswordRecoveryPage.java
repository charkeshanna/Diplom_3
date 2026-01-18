package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PasswordRecoveryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    //конструктор
    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //ссылка Войти
    private By loginLink = By.linkText("Войти");
    //кнопка Восстановить
    private By recoverButton = By.xpath("//button[text()='Восстановить']");

    // метод ожидания загрузки страницы
    @Step("Ожидание загрузки страницы восстановления пароля")
    public void waitForLoadPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(recoverButton));
    }




    //метод клика по ссылке Войти
    @Step("Клик по ссылке 'Войти'")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

}
