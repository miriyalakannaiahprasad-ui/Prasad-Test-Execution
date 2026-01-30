package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;

public class GalaxyTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("browserVersion", "latest");
        caps.setCapability("platformName", "Windows 11");

        caps.setCapability("build", "Amazon Selenium Assignment");
        caps.setCapability("name", "Galaxy Test");

        caps.setCapability("selenium_version", "4.8.3");

        String username = System.getenv("LT_USERNAME");
        String accessKey = System.getenv("LT_ACCESS_KEY");

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
