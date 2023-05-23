import org.junit.Test;
import pageobject.MainPage;
import pageobject.OrderPage;

public class TestErrorsOnFormOrderScooter extends TestScooterApp {

    // Дополнительный сценарий №3
    // Проверить ошибки для всех полей формы заказа.
    @Test
    public void checkErrorsOnFormOrderScooter() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderButton("верхняя кнопка");;
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillContactInfo("И", "И", "Л", "s");
        orderPage.clickNextButton();
        orderPage.checkElementWithErrorMessage("Введите корректное имя");
        orderPage.checkElementWithErrorMessage("Введите корректную фамилию");
        orderPage.checkElementWithErrorMessage("Введите корректный адрес");
        orderPage.checkElementWithErrorMessage("Выберите станцию");
        orderPage.checkElementWithErrorMessage("Введите корректный номер");
    }

}
