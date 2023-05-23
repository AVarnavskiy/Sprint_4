package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Cookie;

public class CookieHelper {

    public static void setCookieAndRefreshPage(WebDriver driver) {
        Cookie cookie1 = new Cookie("Cartoshka","true");
        Cookie cookie2 = new Cookie("Cartoshka-legacy","true");
        driver.manage().addCookie(cookie1);
        driver.manage().addCookie(cookie2);
        driver.navigate().refresh(); // Для применения cookie
    }

}
