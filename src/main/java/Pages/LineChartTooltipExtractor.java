package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import org.testng.Assert;
import java.util.List;

public class LineChartTooltipExtractor {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public LineChartTooltipExtractor(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void takeScreenshot(String fileName) {
        if (driver == null) {
            System.out.println("WebDriver is not initialized. Skipping screenshot.");
            return;
        }
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            String folderPath = System.getProperty("user.dir") + "/screenshots/";
            File folder = new File(folderPath);
            if (!folder.exists()) folder.mkdirs();

            String safeFileName = fileName.replaceAll("[\\\\/:*?\"<>|]", "_");
            File destination = new File(folderPath + safeFileName);

            Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("***************************************************");
            System.out.println("Screenshot saved: " + destination.getAbsolutePath());
            System.out.println("***************************************************");

        } catch (WebDriverException e) {
            System.out.println("WebDriver exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error while taking screenshot: " + e.getMessage());
        }
    }

    
    
    public void extractTooltipValues() {
        try {
            // Locate all data points
        	Thread.sleep(5000);
            List<WebElement> dataPoints = driver.findElements(By.xpath("//*[@class='points']/*[@class='point']"));

            if (dataPoints.isEmpty()) {
                System.out.println(" No data points found.");
                return;
            }
            System.out.println("***************************************************");
            System.out.println("Found " + dataPoints.size() + " data points.");
            System.out.println("***************************************************");

            // Loop through each data point and print its value
            for (int i = 0; i < dataPoints.size(); i++) {
                WebElement point = dataPoints.get(i);
                
                //mouse hover
                Actions actions = new Actions(driver);
                actions.moveToElement(point).perform();
                Thread.sleep(500);
                
                WebElement tooltip;
                try {
                    tooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@class='user-select-none svg-container']//svg//g[contains(@class, 'scatter')]//g[contains(@class, 'points')]//path"))); // Update with actual tooltip class
                } catch (TimeoutException e) {
                    System.out.println("ERROR: Tooltip not found for Data Point " + (i + 1));
                    takeScreenshot("tooltip_error_" + (i + 1) + ".png"); // Capture screenshot
                    continue;
                }
                
                //Assert.assertFalse(tooltip.getText().isEmpty(), "Tooltip text is empty!");

                System.out.println("***************************************************");
                System.out.println("Data Point " + (i + 1));
                System.out.println("-------------------------------------");
                System.out.println(" Value: " + tooltip.getText());
                System.out.println("***************************************************");
            }
        } catch (Exception e) {
            System.out.println("ERROR extracting tooltip values: " + e.getMessage());
            takeScreenshot("tooltip_exception.png");
        }
        
    }
}

// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                
                //WebElement tooltip;
               /* WebElement tooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//g[@class=\"scatterlayer mlayer\"]//g[contains(@class, 'trace')]//path[@class=\"point\"][@transform=\"translate(x,y)\"]")));
                try {
                    tooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//g[@class=\\\"scatterlayer mlayer\\\"]//g[contains(@class, 'trace')]//path[@class=\\\"point\\\"][@transform=\\\"translate(x,y)\\"))); // Update this XPath
                } catch (TimeoutException e) {
                    System.out.println("ERROR: Tooltip not found for Data Point " + (i + 1));
                    takeScreenshot("tooltip_error_" + (i + 1) + ".png"); // Capture screenshot
                    continue;
                }
                
               // WebElement tooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(
                //     By.xpath("//g[@class=\"scatterlayer mlayer\"]//g[contains(@class, 'trace')]//path[@class=\"point\"][@transform=\"translate(x,y)\"]"))); 
                //   //g[@class="scatterlayer mlayer"]//g[contains(@class, 'trace')]//path[@class="point"][@transform="translate(x,y)"]
                //  //div[@class='points']
                // //div[contains(@class,'points')]
                //Assert.assertFalse(tooltip.getText().isEmpty(), "Tooltip text is empty!");
                
                //String actualTooltip = ele.getAttribute("title");
                System.out.println("***************************************************");
                System.out.println("Data Point " + (i + 1));
                System.out.println("-------------------------------------");
                System.out.println(" Value: " + tooltip.getText());
                System.out.println("***************************************************");
            }
        } catch (TimeoutException e) {
            System.out.println("ERROR: Tooltip not found!");
        } catch (Exception e) {
            System.out.println("ERROR extracting tooltip values: " + e.getMessage());
        }
    }*/

