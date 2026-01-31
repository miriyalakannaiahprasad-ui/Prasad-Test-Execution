package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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

        // Initialize RemoteWebDriver with LambdaTest credentials from environment variables
        driver = new RemoteWebDriver(
                new URL("https://" + System.getenv("LT_USERNAME") + ":" +
                        System.getenv("LT_ACCESS_KEY") +
                        "@hub.lambdatest.com/wd/hub"),
                caps
        );

        // Optional: implicit wait to avoid element not found errors
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void searchIphoneAndPrintPrice() {
        // Open Amazon
        driver.get("https://www.amazon.com");

        // Search for iPhone
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("iPhone");
        searchBox.sendKeys(Keys.ENTER);

        // Wait briefly to let results load
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Click the first product
        List<WebElement> products = driver.findElements(By.cssSelector("h2 a"));
        if (products.size() > 0) {
            products.get(0).click();
        } else {
            System.out.println("No products found for iPhone.");
            return;
        }

        // Get price and print
        List<WebElement> prices = driver.findElements(By.cssSelector("span.a-price-whole"));
        if (prices.size() > 0) {
            String price = prices.get(0).getText();
            System.out.println("iPhone Price: " + price);
        } else {
            System.out.println("Price not found for the iPhone.");
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
