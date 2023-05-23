import helpers.CookieHelper;
import helpers.WebDriverHelper;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;

public class TestScooterApp {

    protected WebDriver driver;

    @Before
    public void beforeRun() {
        driver = WebDriverHelper.createDriver();
        driver.get(MainPage.URL_MAIN_PAGE);
        CookieHelper.setCookieAndRefreshPage(driver);
    }

    @After
    public void teardown() {
        WebDriverHelper.quitDriver(driver);
    }

}
