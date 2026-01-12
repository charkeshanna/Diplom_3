package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class ProfilePage {
    private WebDriver driver;

    //конструктор
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }



    // метод ожидания загрузки страницы --- проверить еще раз
    public void waitForLoadPage(){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Профиль']")));
    }
}
