package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;
import org.testng.annotations.*;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class AmazonIphoneTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("Chrome");
        caps.setCapability("browserVersion", "latest");
        caps.setCapability("platformName", "Windows 11");

        driver = new RemoteWebDriver(
            new URL("https://" + System.getenv("LT_USERNAME") + ":" +
                    System.getenv("LT_ACCESS_KEY") +
                    "@hub.lambdatest.com/wd/hub"),
            caps
        );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void searchIphoneAndPrintPrice() throws InterruptedException {
        driver.get("https://www.amazon.com");
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("iPhone" + Keys.ENTER);
        Thread.sleep(3000);
        List<WebElement> products = driver.findElements(By.cssSelector("h2 a"));
        if (!products.isEmpty()) products.get(0).click();
        List<WebElement> prices = driver.findElements(By.cssSelector("span.a-price-whole"));
        if (!prices.isEmpty()) System.out.println("iPhone Price: " + prices.get(0).getText());
    }

    @AfterMethod
    public void tearDown() { if (driver != null) driver.quit(); }
}
