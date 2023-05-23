import helpers.WebDriverHelper;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobject.MainPage;
import pageobject.OrderPage;

@RunWith(Parameterized.class)
public class TestOrderScooter extends TestScooterApp {

    private final String positionOnPageOrderButton;
    private final String name;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;
    private final String rentalPeriod;
    private final String colorScooter;

    public TestOrderScooter(String positionOnPageOrderButton, String name, String lastName, String address, String metro, String phone, String rentalPeriod, String colorScooter) {
        this.positionOnPageOrderButton = positionOnPageOrderButton;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.rentalPeriod = rentalPeriod;
        this.colorScooter = colorScooter;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"верхняя кнопка", "Иван", "Иванов", "Ленина 34", "Черкизовская", "+79168626743", "трое суток", "черный"},
                {"нижняя кнопка", "Петров", "Петр", "Пушкина 34", "Саларьево", "+79168622243", "двое суток", "серый"}
        };
    }

    // №2. Заказ самоката. Весь флоу позитивного сценария.
    // Обрати внимание, что есть две точки входа в сценарий: кнопка «Заказать» вверху страницы и внизу.
    @Test
    public void checkOrderScooter() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderButton(positionOnPageOrderButton);
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillContactInfo(name, lastName, address, metro, phone);
        orderPage.clickNextButton();
        orderPage.fillRentalInfo(rentalPeriod, colorScooter);
        orderPage.clickOrderButton();
        orderPage.confirmOrder();
        orderPage.checkThatOrderProcessed();
    }

    @After
    public void teardown() {
        WebDriverHelper.quitDriver(driver);
    }
}
