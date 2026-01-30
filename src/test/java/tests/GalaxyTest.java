package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.HashMap;

public class GalaxyTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() throws Exception {

        String username = System.getenv("LT_USERNAME");
        String accessKey = System.getenv("LT_ACCESS_KEY");

        System.out.println("LT_USERNAME = " + username);
        System.out.println("LT_ACCESS_KEY = " + (accessKey != null ? "FOUND" : "MISSING"));

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("browserVersion", "latest");
        caps.setCapability("platformName", "Windows 11");

        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "Amazon Selenium Assignment");
        ltOptions.put("name", "Galaxy Test");
        ltOptions.put("selenium_version", "4.8.3");

        caps.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(
                new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"),
                caps
        );
    }

    @Test
    public void searchGalaxy() {
        driver.get("https://www.amazon.in/");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Samsung Galaxy");
        driver.findElement(By.id("nav-search-submit-button")).click();

        String price = driver.findElement(By.cssSelector("span.a-price-whole")).getText();
        System.out.println("Galaxy price: " + price);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
