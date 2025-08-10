package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.io.FileHandler;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;

import java.time.Duration;
import java.util.List;


public class BasePage {
    protected WebDriver driver;
    WebDriverWait wait;

    // Constructor to initialize WebDriver
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Select a state from dropdown
    public void selectState(WebElement dropdownElement, String stateName) {
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(stateName);
    }
    
    public void scrollToLineChart(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    public void takeScreenshot(String fileName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            // Define the destination path inside the "screenshots" folder
            String filePath = System.getProperty("user.dir") + "/screenshots/" + fileName;
            File destination = new File(filePath);

            // Save the screenshot
            FileHandler.copy(source, destination);
            System.out.println("Screenshot saved: " + filePath);
        } catch (IOException e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }

}



