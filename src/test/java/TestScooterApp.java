import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import page_object.MainPage;
import page_object.OrderPage;
import page_object.TrackingOrderPage;

public class TestScooterApp {

    private WebDriver driver;

    @Before
    public void beforeRun() {
        createDriver();
        goToMainPage();
        setCookieAndRefreshPage();
    }

    // № 1. Выпадающий список в разделе «Вопросы о важном».
    // Тебе нужно проверить: когда нажимаешь на стрелочку, открывается соответствующий текст.
    @Test
    public void checkAllAnswersInQuestionsAboutImportant() {
        MainPage mainPage = new MainPage(driver);
        mainPage.checkAllAnswersInQuestionsAboutImportant();
    }

    // № 2. Заказ самоката. Весь флоу позитивного сценария.
    // Обрати внимание, что есть две точки входа в сценарий: кнопка «Заказать» вверху страницы и внизу.
    @Test
    public void clickOrderTopButtonAndOrderScooter() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderTopButton();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillContactInfo("Иван", "Иванов", "Ленина 34", "Черкизовская", "+79168626743");
        orderPage.clickNextButton();
        orderPage.fillRentalInfo("трое суток", "черный");
        orderPage.clickOrderButton();
        orderPage.confirmOrder();
        orderPage.checkThatOrderProcessed();
    }

    @Test
    public void clickOrderBottomButtonAndOrderScooter() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderBottomButton();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillContactInfo("Петров", "Петр", "Пушкина 34", "Саларьево", "+79168622243");
        orderPage.clickNextButton();
        orderPage.fillRentalInfo("двое суток", "серый");
        orderPage.clickOrderButton();
        orderPage.confirmOrder();
        orderPage.checkThatOrderProcessed();
    }

    // Дополнительный сценарий №1.
    // Проверить: если нажать на логотип «Самоката», попадёшь на главную страницу «Самоката».
    @Test
    public void checkClickOnLogoScooter() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLogoScooter();
        Assert.assertEquals(getMainUrl(), driver.getCurrentUrl());
    }
    // Дополнительный сценарий №2.
    // Проверить: если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса.
    @Test
    public void checkClickOnLogoYandex() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLogoYandex();
        switchToNewWindow();
        Assert.assertEquals("https://dzen.ru", driver.getCurrentUrl());
    }

    // Дополнительный сценарий №3
    // Проверить ошибки для всех полей формы заказа.
    @Test
    public void checkErrorsOnFormOrderScooter() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderTopButton();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillContactInfo("И", "И", "Л", "s");
        orderPage.clickNextButton();
        orderPage.checkElementWithErrorMessage("Введите корректное имя");
        orderPage.checkElementWithErrorMessage("Введите корректную фамилию");
        orderPage.checkElementWithErrorMessage("Введите корректный адрес");
        orderPage.checkElementWithErrorMessage("Выберите станцию");
        orderPage.checkElementWithErrorMessage("Введите корректный номер");
    }

    // Дополнительный сценарий №4
    // Проверить: если ввести неправильный номер заказа, попадёшь на страницу статуса заказа.
    // На ней должно быть написано, что такого заказа нет.

    @Test
    public void checkInvalidOrderNumber() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickStatusOrderButton();
        mainPage.fillNumberOrder("111111111111111111");
        mainPage.clickGoButton();
        TrackingOrderPage trackingOrderPage = new TrackingOrderPage(driver);
        trackingOrderPage.checkOrderNotFound();
    }

    private void createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    private void goToMainPage() {
        driver.get(getMainUrl());
    }

    private void setCookieAndRefreshPage() {
        Cookie cookie1 = new Cookie("Cartoshka","true");
        Cookie cookie2 = new Cookie("Cartoshka-legacy","true");
        driver.manage().addCookie(cookie1);
        driver.manage().addCookie(cookie2);
        driver.navigate().refresh(); // Для применения cookie
    }

    private String getMainUrl() {
        return "https://qa-scooter.praktikum-services.ru/";
    }

    private void switchToNewWindow() {
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    @After
    public void teardown() {
        driver.quit();
    }
}