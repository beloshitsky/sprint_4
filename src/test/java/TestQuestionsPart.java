import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobjects.HomePage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestQuestionsPart {

    private WebDriver webDriver;

    private final String MAIN_PAGE = "https://qa-scooter.praktikum-services.ru/";

    private int questionId;
    private int answerId;
    private String answer;

    public TestQuestionsPart(int questionId, int answerId, String answer) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.answer = answer;
    }

    @Parameterized.Parameters
    public static Object[][] getAnswers() {
        return new Object[][] {
                {0, 0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, 1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, " +
                        "можете просто сделать несколько заказов — один за другим."},
                {2, 2,  "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. " +
                        "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
                        "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, 3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, 4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, 5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток " +
                        "— даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, 6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. " +
                        "Все же свои."},
                {7, 7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        webDriver = new ChromeDriver(chromeOptions);
//        WebDriverManager.firefoxdriver().setup();
//        webDriver = new FirefoxDriver();
        webDriver.get(MAIN_PAGE);


        HomePage homePage = new HomePage(webDriver);
        homePage.clickCookieButton();
        homePage.scrollToQuestionsPart();
    }

    @Test
    public void checkAnswers() {
        webDriver.findElement(By.id("accordion__heading-" + this.questionId)).click();

        assertEquals(webDriver.findElement(By.id("accordion__panel-" + this.answerId)).getText(), answer);
    }

    @After
    public void teardown() {
        webDriver.quit();
    }
}
