package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private WebDriver webDriver;

    //поле имя
    private By nameField = By.cssSelector("[placeholder='* Имя']");
    //поле фамилия
    private By surNameField = By.cssSelector("[placeholder='* Фамилия']");
    //поле адрес
    private By addressField = By.cssSelector("[placeholder='* Адрес: куда привезти заказ']");
    //метро
    private By metroButton = By.className("select-search");
    //поле телефон
    private By phoneField = By.cssSelector("[placeholder='* Телефон: на него позвонит курьер']");
    //кнопка далее
    private By okButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void setName(String name) {
        webDriver.findElement(nameField).sendKeys(name);
    }

    public void setSurname(String surname) {
        webDriver.findElement(surNameField).sendKeys(surname);
    }

    public void setAddress(String address) {
        webDriver.findElement(addressField).sendKeys(address);
    }

    public void setMetro(int stationNumber) {
        webDriver.findElement(metroButton).click();

        webDriver.findElement(By.xpath(".//div[@class='select-search__select']/ul/li"
                + "[" + stationNumber + "]")).click();
    }

    public void setPhone(String phoneNumber) {
        webDriver.findElement(phoneField).sendKeys(phoneNumber);
    }

    public void clickOrderButton() {
        webDriver.findElement(okButton).click();
    }

    public void fillClientData(String name, String surname, String address, int stationNumber, String phoneNumber) {
        setName(name);
        setSurname(surname);
        setAddress(address);
        setMetro(stationNumber);
        setPhone(phoneNumber);
        clickOrderButton();
    }
}
