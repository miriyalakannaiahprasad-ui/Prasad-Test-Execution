package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.URL;

public class AmazonGalaxyTest {

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
    }

    @Test
    public void searchGalaxyAndPrintPrice() {
        driver.get("https://www.amazon.com");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Samsung Galaxy", Keys.ENTER);
        driver.findElements(By.cssSelector("h2 a")).get(0).click();

        String price = driver.findElement(By.cssSelector("span.a-price-whole")).getText();
        System.out.println("Galaxy Price: " + price);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
