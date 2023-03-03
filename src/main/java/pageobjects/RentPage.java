package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RentPage {
    private WebDriver webDriver;

    //поле когда
    private By calendarField = By.cssSelector("[placeholder='* Когда привезти самокат']");
    //срок аренды
    private By termField = By.className("Dropdown-placeholder");
    //серый цвет самоката
    private By greyCheckBox = By.xpath(".//input[@id = 'grey']");
    //чёрный цвет самоката
    private By blackCheckBox = By.xpath(".//input[@id = 'black']");
    //комментарий
    private By commentField = By.cssSelector("[placeholder='Комментарий для курьера']");
    //кнопка заказать
    private By orderButton = By.xpath(".//div[2]/div[3]/button[2]");
    //кнопка подтверждения заказа
    private By yesButton = By.xpath(".//div[2]/div[5]/div[2]/button[2]");

    //успех
    private By successMessage = By.className("Order_ModalHeader__3FDaJ");

    public RentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void setDate(String date) {
        webDriver.findElement(calendarField).click();
        webDriver.findElement(calendarField).sendKeys(date, (Keys.ENTER));
    }

    public void setTerm(int term) {
        webDriver.findElement(termField).click();
        webDriver.findElement(By.xpath(".//div[@class='Dropdown-option']"
                + "[" + term + "]")).click();
    }

    public void setColour(String colour) {
        if (colour.equals("black")) webDriver.findElement(blackCheckBox).click();
        else if (colour.equals("grey")) webDriver.findElement(greyCheckBox).click();
    }

    public void setComment(String comment) {
        webDriver.findElement(commentField).sendKeys(comment);
    }

    public void makeOrder(String date, int term, String colour, String comment) {
        setDate(date);
        setTerm(term);
        setColour(colour);
        setComment(comment);
        webDriver.findElement(orderButton).click();

        webDriver.findElement(yesButton).click();
    }

    public boolean checkOrder() {
        return webDriver.findElement(successMessage).isDisplayed();
    }
}
