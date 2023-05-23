package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private WebDriver driver;

    private By nameField = By.xpath(".//input[contains(@placeholder,'Имя')]");
    private By lastNameField = By.xpath(".//input[contains(@placeholder,'Фамилия')]");
    private By addressField = By.xpath(".//input[contains(@placeholder,'Адрес')]");
    private By metroField = By.className("select-search__input");
    private By phoneField = By.xpath(".//input[contains(@placeholder,'Телефон')]");
    private By nextButton = By.xpath(".//div[contains(@class,'Order_NextButton')]//button");
    private By rentalStartDateField = By.xpath(".//input[contains(@placeholder,'Когда привезти самокат')]");
    private By todayDatepickerButton = By.xpath(".//div[contains(@class,'datepicker__day--keyboard-selected')]");
    private By rentalPeriodDropdown = By.className("Dropdown-placeholder");
    private By blackColorCheckbox = By.id("black");
    private By grayColorCheckbox = By.id("grey");
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private By orderButton = By.xpath(".//div[contains(@class,'Order_Buttons')]//button[text()='Заказать']");
    private By confirmOrderButton = By.xpath(".//button[text()='Да']");
    private By orderProcessedLabel = By.xpath(".//div[text()='Заказ оформлен']");
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillContactInfo(String name, String lastName, String address, String metro, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(addressField).sendKeys(address);
        selectMetroStation(metro);
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void fillContactInfo(String name, String lastName, String address, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void fillRentalInfo(String rentalPeriod, String colorScooter) {
        selectTodayInDatepicker();
        selectRentalPeriod(rentalPeriod);
        selectScooterByColor(colorScooter);
        driver.findElement(commentField).sendKeys("Комментарий для курьера");
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void confirmOrder() {
        driver.findElement(confirmOrderButton).click();
    }

    public void checkThatOrderProcessed() {
        driver.findElement(orderProcessedLabel);
    }

    public void checkElementWithErrorMessage(String text) {
        String elementXpath = String.format("//div[contains(@class,'Error') and text()='%s']", text);
        driver.findElement(By.xpath(elementXpath));
    }

    private void selectMetroStation(String name) {
        driver.findElement(metroField).click();
        driver.findElement(By.xpath(".//div[@class='select-search__select']"));
        driver.findElement(By.xpath(String.format(".//div[@class='select-search__select']//*[text()='%s']", name))).click();
    }

    private void selectRentalPeriod(String period) {
        driver.findElement(rentalPeriodDropdown).click();
        String periodOptionXpath = String.format("//div[@class='Dropdown-option' and text()='%s']", period);
        driver.findElement(By.xpath(periodOptionXpath)).click();
    }

    private void selectTodayInDatepicker() {
        driver.findElement(rentalStartDateField).click();
        driver.findElement(todayDatepickerButton).click();
    }

    private void selectScooterByColor(String color) {
        if(color.equals("черный")) {
            driver.findElement(blackColorCheckbox).click();
        } else if(color.equals("серый")) {
            driver.findElement(grayColorCheckbox).click();
        }

    }
}
