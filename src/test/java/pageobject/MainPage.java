package pageobject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {

    private WebDriver driver;

    public static final String URL_MAIN_PAGE = "https://qa-scooter.praktikum-services.ru/";
    private By logoYandex = By.xpath(".//a[contains(@class,'LogoYandex')]");
    private By logoScooter = By.xpath(".//a[contains(@class,'LogoScooter')]");
    private By orderStatusButton = By.xpath(".//button[text()='Статус заказа']");
    private By orderNumberField = By.xpath(".//input[@placeholder='Введите номер заказа']");
    private By goButton = By.xpath(".//button[text()='Go!']");
    private By orderTopButton = By.xpath(".//div[contains(@class,'Header')]//button[text()='Заказать']");
    private By orderBottomButton = By.xpath(".//div[contains(@class,'Home_Finish')]//button[text()='Заказать']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkAnswerInQuestionsAboutImportant(String questionText, String answerText) {
        String questionXpath = String.format(".//div[@class='accordion__button' and text()='%s']", questionText);
        WebElement question = driver.findElement(By.xpath(questionXpath));
        question.click();
        String answerXpath = "./ancestor::div[@class='accordion__item']//p";
        WebElement answer = question.findElement(By.xpath(answerXpath));
        Assert.assertEquals(answerText, answer.getText());
    }

    public void clickOrderButton(String positionInPage) {
        if (positionInPage.equals("верхняя кнопка")) {
            driver.findElement(orderTopButton).click();
        } else if (positionInPage.equals("нижняя кнопка")) {
            driver.findElement(orderBottomButton).click();
        }
    }

    public void clickLogoScooter() {
        driver.findElement(logoScooter);
    }

    public void clickLogoYandex() {
        driver.findElement(logoYandex).click();
    }

    public void clickStatusOrderButton() {
        driver.findElement(orderStatusButton).click();
    }

    public void fillNumberOrder(String number) {
        driver.findElement(orderNumberField).sendKeys(number);
    }
    public void clickGoButton() {
        driver.findElement(goButton).click();
    }

}
