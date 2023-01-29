package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private WebDriver webDriver;

    //блок с вопросами
    private By questionsPart = By.className("Home_FourPart__1uthg");
    //верхняя кнопка заказа
    private By upOrderButton = By.className("Button_Button__ra12g");
    //нижняя кнопка заказа
    private By middleOrderButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    //кнопка куки
    private By cookieButton = By.className("App_CookieButton__3cvqF");

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickCookieButton() {
        webDriver.findElement(cookieButton).click();
    }

    public void scrollToQuestionsPart() {
        WebElement element = webDriver.findElement(questionsPart);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickUpOrderButton() {
        webDriver.findElement(upOrderButton).click();
    }

    public void clickMiddleOrderButton() {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();",
                webDriver.findElement(middleOrderButton));
        webDriver.findElement(middleOrderButton).click();
    }
}
