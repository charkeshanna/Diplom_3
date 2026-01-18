package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

public class ProfilePage {
    private WebDriver driver;
    private WebDriverWait wait;

    //конструктор
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //ссылка Профиль
    private By profileLink = By.xpath("//a[text()='Профиль']");
    //кнопка Конструктор
    private By constructorButton = By.xpath("//p[text()='Конструктор']");
    //логотип Stellar Burgers
    private By stellarBurgersLogo = By.xpath("//div[contains(@class,'AppHeader_header__logo')]");
    //кнопка Выход
    private By logoutButton = By.xpath("//button[text()='Выход']");
    // метод ожидания загрузки страницы --- проверить еще раз

    // метод ожидания загрузки страницы
    @Step("Ожидание загрузки страницы профиля")
    public void waitForLoadPage(){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Профиль']")));
    }

    //метод клика по кнопке Выход
    @Step("Клик по кнопке 'Выход'")
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    //метод клика по кнопке Конструктор
    @Step("Клик по кнопке 'Конструктор'")
    public void clickConstructorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
    }

    //метод клика по логотипу
    @Step("Клик по логотипу Stellar Burgers")
    public void clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(stellarBurgersLogo)).click();
    }

    //проверка отображения страницы профиля
    @Step("Проверка отображения страницы профиля")
    public boolean isProfileDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(profileLink)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }



}
