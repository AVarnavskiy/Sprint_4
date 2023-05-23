package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class TrackingOrderPage {

    private WebDriver driver;

    private By orderNotFoundImage = By.xpath("//img[@alt='Not found']");

    public TrackingOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkOrderNotFound() {
        driver.findElement(orderNotFoundImage);
    }
}