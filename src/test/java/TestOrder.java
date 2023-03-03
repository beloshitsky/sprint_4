import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobjects.HomePage;
import pageobjects.OrderPage;
import pageobjects.RentPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestOrder {
    private WebDriver webDriver;

    private final String ORDER_PAGE = "https://qa-scooter.praktikum-services.ru/order";

    private String name;
    private String surname;
    private String address;
    private int metro;
    private String phoneNumber;
    private String date;
    private String colour;
    private int term;
    private String comment;

    public TestOrder(String name, String surname, String address, int metro, String phoneNumber,
                     String date, int term, String colour, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.term = term;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getClientData() {
        return new Object[][] {
                {"Клиент", "Первый", "Улица Пушкина", 7, "88001111111", "30.01.2023", 3, "grey", "ok"},
                {"Клиент", "Второй", "Улица Пушкина", 1, "88005553535", "15.02.2023", 1, "black", ""},
        };
    }

    @Before
    public void setUp() {
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
//        webDriver = new ChromeDriver(chromeOptions);

        //в Chrome тест не проходит из-за бага, оставил только Firefox
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
        webDriver.get(ORDER_PAGE);
    }

    @Test
    public void testUpOrderButton() {
        HomePage homePage = new HomePage(webDriver);
        OrderPage orderPage = new OrderPage(webDriver);
        RentPage rentPage = new RentPage(webDriver);

        homePage.clickCookieButton();
        homePage.clickUpOrderButton();

        orderPage.fillClientData(this.name, this.surname, this.address, this.metro, this.phoneNumber);
        rentPage.makeOrder(this.date, this.term, this.colour, this.comment);

        assertTrue(rentPage.checkOrder());

    }

    @Test
    public void testMiddleOrderButton() {
        HomePage homePage = new HomePage(webDriver);
        OrderPage orderPage = new OrderPage(webDriver);
        RentPage rentPage = new RentPage(webDriver);

        homePage.clickCookieButton();
        homePage.clickMiddleOrderButton();

        orderPage.fillClientData(this.name, this.surname, this.address, this.metro, this.phoneNumber);
        rentPage.makeOrder(this.date, this.term, this.colour, this.comment);

        assertTrue(rentPage.checkOrder());
    }

    @After
    public void teardown() {
        webDriver.quit();
    }
}
