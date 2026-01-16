package pom;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

public class StartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public StartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    //кнопка Войти в аккаунт
    private By logInToAccountButton = By.xpath("//button[text()='Войти в аккаунт']");

    //Профиль
    private By profileLink = By.xpath("//p[contains(text(),'Личный Кабинет')]");

    //активная вкладка (родитель с классом current)
    private By activeBunsTab = By.xpath("//span[text()='Булки']/parent::div[contains(@class,'current')]");
    //метод клика по кнопке Войти в аккаунт
    public void clickLogInToAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logInToAccountButton)).click();
    }

    //метод ожидания загрузки странцы - заголовка собери бургер
    public void waitForLoadPage(){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Соберите бургер']")));
    }

    //метод клика по Профиль
    public void clickProfileLink() {
        driver.findElement(profileLink).click();
    }
    //проверка активности вкладки Булки
    public boolean isBunsTabActive() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(activeBunsTab)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
