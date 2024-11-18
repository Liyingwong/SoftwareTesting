package SoftwareTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ValidTest {

    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C://Users//ASUS//chromedriver-win64//chromedriver.exe/");

        // Initialize the WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            System.out.println("Running Generate Report Invalid Testing ...");
            // Open a webpage
            driver.get("http://localhost/InventorySystem_PHP/index.php");

            // Initialize WebDriverWait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Perform login
            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
            usernameField.sendKeys("admin");

            WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
            passwordField.sendKeys("admin");

            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
            loginButton.click();

            // Navigate to the generate report page
            driver.get("http://localhost/InventorySystem_PHP/sales_report.php");
            
            Actions actions = new Actions(driver);
            WebElement startdateField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("start-date")));
            startdateField.clear();
            startdateField.sendKeys("");
            
            System.out.println("Start Date test with value: ");

            WebElement enddateField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("end-date")));
            enddateField.clear();
            enddateField.sendKeys("");
       
            System.out.println("End Date test with value: ");
            
            // Wait for the generate button to be clickable and click it
            WebElement generateButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
            generateButton.click();
            System.out.println("Button Generate Report clicked successfully");

            try {
                WebElement errorMessage = driver.findElement(By.cssSelector("div.alert.alert-danger"));
                if (errorMessage.isDisplayed()) {
                    System.out.println("Generate report failed with error message: " + errorMessage.getText());
                    takeScreenshot(driver, "generateFailed");
                }
            } catch (Exception e) {
                System.out.println("Generate report succeeded.");
            }

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();  // This will print the full stack trace of the exception
            takeScreenshot(driver, "exceptionOccurred");
        } finally {
            // Close the browser
            driver.quit();
        }
    }

    // Utility method to take a screenshot
    public static void takeScreenshot(WebDriver driver, String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(source, new File("C:/Users/User/Pictures/Screenshots/" + fileName + ".png"));
            System.out.println("Screenshot taken: " + fileName);
        } catch (IOException e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }
}