import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {

    private WebDriver driver;

    @BeforeAll
    public static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "./driver/win/chromedriver.exe");
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldOrderForm() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type='text']")).sendKeys("Гончиков Болот");
        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("+79994996468");
//        List<WebElement> textFileds = driver.findElements(By.className("input__control"));
//        textFileds.get(0).sendKeys("Болот");
//        textFileds.get(1).sendKeys("+79994996468");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actual = driver.findElement(By.cssSelector(".paragraph_theme_alfa-on-white")).getText().trim();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expected, actual);
    }
}
