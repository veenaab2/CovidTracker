package stepDefinitions;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import Pages.HomePage;

public class SearchSteps {
    WebDriver driver;
    HomePage homePage;
    WebDriverWait wait;

    @Given("I launch the browser")
    public void i_launch_the_browser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        driver.manage().window().maximize();
        homePage = new HomePage(driver); // Initialize the HomePage
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    @When("I open the URL {string}")
    public void i_open_the_url(String url) {
        driver.get(url);
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Then("The page title should contain {string}")
    public void the_page_title_should_contain(String expectedTitle) {
        String actualTitle = driver.getTitle();
        System.out.println("Page Title: " + actualTitle);
        Assert.assertTrue(actualTitle.contains(expectedTitle), 
                "Expected title to contain: " + expectedTitle + " but found: " + actualTitle);
    }
    
    @When("I select Kerala from the state dropdown")
    public void i_select_kerala_from_dropdown() {
        homePage.selectStateKerala();
    }


    @Then("I scroll down to the line chart")
    public void i_scroll_to_the_line_chart() {
        homePage.scrollToLineChart();
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Then("I print the values at each point from the line chart")
    public void i_print_line_chart_values() {
        homePage.printLineChartValues();
    }
    
    public void forceQuitDriver() {
        try {
            if (driver != null) {
                System.out.println("Forcing WebDriver to quit...");
                driver.quit();
                driver = null;
            }
        } catch (Exception e) {
            System.out.println("Error while force quitting WebDriver: " + e.getMessage());
        }
    }
    
    @AfterSuite
    public void tearDown() {
        System.out.println("Closing WebDriver...");
        forceQuitDriver();
    }
    
   /* @AfterSuite
    public void tearDown() {
        try {
            if (driver != null) {
                System.out.println("Closing the browser...");
                driver.quit();
                System.out.println("Browser closed successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error while closing browser: " + e.getMessage());
        }
    }*/
}
