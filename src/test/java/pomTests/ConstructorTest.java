package pomTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pom.StartPage;
import utils.EnvData;
import utils.WebDriverFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;
@DisplayName("Проверка переключений между вкладками Соусы, Начинки, Булки")
public class ConstructorTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = WebDriverFactory.createDriver();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    void testNavigateToBunsSection() {
        //Переходим на главную страницу
        driver.get(EnvData.getBaseUrl());
        StartPage startPage = new StartPage(driver);
        startPage.waitForLoadPage();

        //Кликаем на вкладку Соусы (чтобы уйти с Булок)
        startPage.clickSaucesTab();

        //Проверяем, что вкладка Соусы активна
        assertTrue(startPage.isSaucesTabActive(),
                "Вкладка Соусы должна быть активной");

        //Кликаем на вкладку Булки
        startPage.clickBunsTab();

        //Проверяем, что вкладка Булки активна
        assertTrue(startPage.isBunsTabActive(),
                "Вкладка Булки должна быть активной");
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    void testNavigateToSaucesSection() {
        //Переходим на главную страницу
        driver.get(EnvData.getBaseUrl());
        StartPage startPage = new StartPage(driver);
        startPage.waitForLoadPage();

        //Кликаем на вкладку Соусы
        startPage.clickSaucesTab();

        //Проверяем, что вкладка Соусы активна
        assertTrue(startPage.isSaucesTabActive(),
                "Вкладка Соусы должна быть активной");
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    void testNavigateToFillingsSection() {
        //Переходим на главную страницу
        driver.get(EnvData.getBaseUrl());
        StartPage startPage = new StartPage(driver);
        startPage.waitForLoadPage();

        //Кликаем на вкладку Начинки
        startPage.clickFillingsTab();

        //Проверяем, что вкладка Начинки активна
        assertTrue(startPage.isFillingsTabActive(),
                "Вкладка Начинки должна быть активной");
    }
}
