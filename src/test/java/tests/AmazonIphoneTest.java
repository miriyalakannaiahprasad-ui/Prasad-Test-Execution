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
    public void searchIphoneAndPrintPrice() {
        driver.get("https://www.amazon.com");
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("iPhone");
        searchBox.sendKeys(Keys.ENTER);

        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        List<WebElement> products = driver.findElements(By.cssSelector("h2 a"));
        if (!products.isEmpty()) products.get(0).click();
        else { System.out.println("No products found for iPhone."); return; }

        List<WebElement> prices = driver.findElements(By.cssSelector("span.a-price-whole"));
        if (!prices.isEmpty()) System.out.println("iPhone Price: " + prices.get(0).getText());
        else System.out.println("Price not found for the iPhone.");
    }

    @AfterMethod
    public void tearDown() { if (driver != null) driver.quit(); }
}
