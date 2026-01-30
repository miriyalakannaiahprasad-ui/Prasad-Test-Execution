package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.URL;
public class GalaxyTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void searchAndAddGalaxyToCart() {
        driver.get("https://www.amazon.com");

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Galaxy phone");
        driver.findElement(By.id("twotabsearchtextbox")).submit();

        driver.findElement(By.cssSelector("[data-component-type='s-search-result'] h2 a")).click();

        driver.findElement(By.id("add-to-cart-button")).click();

        String priceWhole = driver.findElement(By.cssSelector(".a-price-whole")).getText();
        String priceFraction = driver.findElement(By.cssSelector(".a-price-fraction")).getText();

        System.out.println("Galaxy Price: $" + priceWhole + "." + priceFraction);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
