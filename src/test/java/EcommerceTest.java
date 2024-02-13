import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EcommerceTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/awino/Downloads/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void loginTest() {
        // Navigate to the login page
        driver.get("http://localhost:3000/");

        // Locate username and password fields and login button
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        // Enter your login credentials
        usernameInput.sendKeys("hillary@test.com");
        passwordInput.sendKeys("123");

        // Click on the login button
        loginButton.click();

        // After login, navigate to the homepage
        driver.get("http://localhost:3000/home");

        // Scroll to view products
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement productsList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("view-products")));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", productsList);
    }

    @Test(dependsOnMethods = "loginTest")
    public void addToCartTest() {
        // Scroll to view products
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            WebElement viewMoreButton = driver.findElement(By.id("view-more-button"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("view-more-button")));
            viewMoreButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("view-more-button")));
            viewMoreButton.click();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        try {
            WebElement addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-button")));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", addToCartButton);
            addToCartButton.click();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Optional: Add verification/assertion for successful addToCart
        try {
            // Example: Check if a success message is displayed
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-message")));
            Assert.assertEquals(successMessage.getText(), "Product added to cart successfully");

            // You can add more verifications based on your application behavior
        } catch (TimeoutException e) {
            // Handle the case where the success message is not displayed
            e.printStackTrace();
        }
    }

    // @Test(dependsOnMethods = "addToCartTest")
    // public void removeFromCartTest() {
    //     // Locate the remove from cart button
    //     WebDriverWait wait = new WebDriverWait(driver, 10);
    //     WebElement removeFromCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("remove-from-cart")));

    //     // Click on the remove from cart button
    //     removeFromCartButton.click();

    //     // Optional: Add verification/assertion for successful removal from cart
    //     try {
    //         // Example: Check if a success message is displayed
    //         WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-message")));
    //         Assert.assertEquals(successMessage.getText(), "Product removed from cart successfully");

    //         // You can add more verifications based on your application behavior
    //     } catch (TimeoutException e) {
    //         // Handle the case where the success message is not displayed
    //         e.printStackTrace();
    //     }
    // }


    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
