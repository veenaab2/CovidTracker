package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    
    WebDriverWait wait;
    private LineChartTooltipExtractor tooltipExtractor;
    
   // Locate the dropdown for state selection
    @FindBy(css = ".data-filter-input")  
    private WebElement stateDropdown;
    
    // Locator for the line chart
    @FindBy(xpath = "//*[@id='root']/div/div[3]/div")
    private WebElement lineChart;

    // Constructor
    public HomePage(WebDriver driver) {
    	super(driver);
    	this.driver=driver;
    	this.tooltipExtractor = new LineChartTooltipExtractor(driver);
        
        PageFactory.initElements(driver, this); // Initialize WebElements
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    // Select Kerala from dropdown
    public void selectStateKerala() {
    	wait.until(ExpectedConditions.elementToBeClickable(stateDropdown));
    	Select select = new Select(stateDropdown);
        select.selectByVisibleText("Kerala");
        
    }
    
    public void scrollToLineChart() {
        wait.until(ExpectedConditions.visibilityOf(lineChart));
        scrollToLineChart(lineChart);
    }
    
    public void printLineChartValues() {
        tooltipExtractor.extractTooltipValues();
    } 
}
