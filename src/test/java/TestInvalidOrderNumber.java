import org.junit.Test;
import pageobject.MainPage;
import pageobject.TrackingOrderPage;

public class TestInvalidOrderNumber extends TestScooterApp {

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

}
