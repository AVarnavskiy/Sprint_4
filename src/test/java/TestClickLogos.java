import helpers.WebDriverHelper;
import org.junit.Assert;
import org.junit.Test;
import pageobject.MainPage;

public class TestClickLogos extends TestScooterApp {

    // Дополнительный сценарий №1.
    // Проверить: если нажать на логотип «Самоката», попадёшь на главную страницу «Самоката».
    @Test
    public void checkClickOnLogoScooter() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLogoScooter();
        Assert.assertEquals(MainPage.URL_MAIN_PAGE, driver.getCurrentUrl());
    }

    // Дополнительный сценарий №2.
    // Проверить: если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса.
    @Test
    public void checkClickOnLogoYandex() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLogoYandex();
        WebDriverHelper.switchToNewWindow(driver);
        Assert.assertEquals("https://dzen.ru/?yredirect=true", driver.getCurrentUrl());
    }

}