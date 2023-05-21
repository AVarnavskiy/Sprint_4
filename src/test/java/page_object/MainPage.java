package page_object;

import org.junit.Assert;
import java.util.Map;
import static java.util.Map.entry;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {

    private WebDriver driver;

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

    public void checkAllAnswersInQuestionsAboutImportant() {

        for(Map.Entry<String, String> entry : questionsAndAnswers().entrySet()) {
            String questionXpath = String.format(".//div[@class='accordion__button' and text()='%s']", entry.getKey());
            WebElement question = driver.findElement(By.xpath(questionXpath));
            question.click();
            String answerXpath = "./ancestor::div[@class='accordion__item']//p";
            WebElement answer = question.findElement(By.xpath(answerXpath));
            Assert.assertEquals(entry.getValue(), answer.getText());
        }
    }

    public void clickOrderTopButton() {
        driver.findElement(orderTopButton).click();
    }

    public void clickOrderBottomButton() {
        driver.findElement(orderBottomButton).click();
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

    private Map<String, String> questionsAndAnswers() {
        return Map.ofEntries(
            entry("Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."),
            entry("Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."),
            entry("Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."),
            entry("Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
            entry("Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."),
            entry("Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."),
            entry("Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."),
            entry("Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области.")
        );
    }


}
