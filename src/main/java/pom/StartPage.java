package pom;
import io.qameta.allure.Step;
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
    //вкладка Булки
    private By bunsTab = By.xpath("//span[text()='Булки']");
    //вкладка Соусы
    private By saucesTab = By.xpath("//span[text()='Соусы']");
    //вкладка Начинки
    private By fillingsTab = By.xpath("//span[text()='Начинки']");
    //активная вкладка (родитель с классом current)
    private By activeBunsTab = By.xpath("//span[text()='Булки']/parent::div[contains(@class,'current')]");
    private By activeSaucesTab = By.xpath("//span[text()='Соусы']/parent::div[contains(@class,'current')]");
    private By activeFillingsTab = By.xpath("//span[text()='Начинки']/parent::div[contains(@class,'current')]");

    @Step("Клик по кнопке 'Войти в аккаунт'")
    //метод клика по кнопке Войти в аккаунт
    public void clickLogInToAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logInToAccountButton)).click();
    }

    @Step("Ожидание загрузки главной страницы")
    //метод ожидания загрузки странцы - заголовка собери бургер
    public void waitForLoadPage(){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Соберите бургер']")));
    }

    //метод клика по Профиль
    @Step("Клик по ссылке 'Личный Кабинет'")
    public void clickProfileLink() {
        driver.findElement(profileLink).click();
    }

    //проверка активности вкладки Булки
    @Step("Проверка активности вкладки 'Булки'")
    public boolean isBunsTabActive() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(activeBunsTab)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    //метод клика по вкладке Булки
    @Step("Клик по вкладке 'Булки'")
    public void clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
    }

    //метод клика по вкладке Соусы
    @Step("Клик по вкладке 'Соусы'")
    public void clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
    }

    //метод клика по вкладке Начинки
    @Step("Клик по вкладке 'Начинки'")
    public void clickFillingsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
    }

    //проверка активности вкладки Соусы
    @Step("Проверка активности вкладки 'Соусы'")
    public boolean isSaucesTabActive() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(activeSaucesTab)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //проверка активности вкладки Начинки
    @Step("Проверка активности вкладки 'Начинки'")
    public boolean isFillingsTabActive() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(activeFillingsTab)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


}
