import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Zapta {
        WebDriver driver;
@BeforeTest
        void Launch() {
    WebDriverManager.chromedriver();
    driver = new ChromeDriver();
    driver.manage().window().maximize();

    driver.get("https://stg.zaptatech.com/");

}
@Test(priority = 1)
        void test(){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
         JavascriptExecutor executor = (JavascriptExecutor) driver;
        //Scroll to Feature listing section
        WebElement feature=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='swiper-slide swiper-slide-active']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", feature);

        //Click on Midland apartment
        WebElement Midland=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='img-fluid'])[2]")));
        executor.executeScript("arguments[0].click();", Midland);

        // Scroll down
    JavascriptExecutor js1 = (JavascriptExecutor) driver;
    js1.executeScript("window.scrollBy(0,350)");

    // Take a screenshot
    File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

    // Generate the timestamp for the filename
    SimpleDateFormat formatter = new SimpleDateFormat("HH-mm-ss");
    String timestamp = formatter.format(new Date());

    // Define the screenshot filename
    String filename = "FIRST_" + timestamp + ".jpg";

    // Save the screenshot
    try {
        FileUtils.copyFile(screenshotFile, new File(filename));
        System.out.println("Screenshot saved as: " + filename);
    } catch (Exception e) {
        System.out.println("Failed to save screenshot: " + e.getMessage());
    }
    }

}