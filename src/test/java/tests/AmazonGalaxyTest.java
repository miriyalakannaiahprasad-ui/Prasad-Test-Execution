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

public class GalaxyTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() throws Exception {
        // Setup LambdaTest capabilities
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

        // Implicit wait and maximize window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void searchGalaxyAndPrintPrice() {
        // Open Amazon
        driver.get("https://www.amazon.com");

        // Search for Galaxy phone
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Samsung Galaxy");
        searchBox.sendKeys(Keys.ENTER);

        // Wait briefly for results to load
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Click the first product if available
        List<WebElement> products = driver.findElements(By.cssSelector("h2 a"));
        if (!products.isEmpty()) {
            products.get(0).click();
        } else {
            System.out.println("No products found for Samsung Galaxy.");
            return;
        }

        // Get the price and print it
        List<WebElement> prices = driver.findElements(By.cssSelector("span.a-price-whole"));
        if (!prices.isEmpty()) {
            String price = prices.get(0).getText();
            System.out.println("Samsung Galaxy Price: " + price);
        } else {
            System.out.println("Price not found for Samsung Galaxy.");
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
