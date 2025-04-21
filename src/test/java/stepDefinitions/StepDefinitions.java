package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;

public class StepDefinitions {
    WebDriver driver;
    WebDriverWait wait;

    @Given("user opens the browser")
    public void user_opens_browser() {
        System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @When("user navigates to {string}")
    public void user_navigates_to(String url) {
        driver.get(url);
    }

    @Then("page title should be {string}")
    public void verify_page_title(String expectedTitle) {
        Assert.assertEquals(driver.getTitle(), expectedTitle);
        driver.quit();
    }

    @When("user logs in with username {string} and password {string}")
    public void user_logs_in(String username, String password) {
        driver.findElement(By.id("login2")).click(); // Open login modal

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).clear();
        driver.findElement(By.id("loginusername")).sendKeys(username);
        driver.findElement(By.id("loginpassword")).clear();
        driver.findElement(By.id("loginpassword")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        try {
            // Wait for UI to respond
            Thread.sleep(1000);

            // Wait for either the greeting or alert
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")),
                    ExpectedConditions.alertIsPresent()
            ));

            // Handle alert if present
            if (isAlertPresent()) {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                alert.accept();
                Assert.fail("Login failed due to alert: " + alertText);
            }

            // Verify greeting
            WebElement greeting = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
            Assert.assertTrue(greeting.isDisplayed(), "Greeting message not displayed");

        } catch (TimeoutException e) {
            Assert.fail("Login failed: neither greeting nor alert appeared.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

    @Then("user should see greeting message with username {string}")
    public void verify_greeting_message(String username) {
        WebElement greeting = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        Assert.assertTrue(greeting.isDisplayed());
        Assert.assertTrue(greeting.getText().contains(username), "Greeting does not contain username");
        driver.quit();
    }

    @Then("category list should contain {string}, {string}, and {string}")
    public void verify_category_list(String cat1, String cat2, String cat3) {
        Assert.assertTrue(driver.getPageSource().contains(cat1));
        Assert.assertTrue(driver.getPageSource().contains(cat2));
        Assert.assertTrue(driver.getPageSource().contains(cat3));
        driver.quit();
    }

    @When("user adds {string} to the cart")
    public void user_adds_item_to_cart(String productName) {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(productName))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add to cart"))).click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur"))).click();
    }

    @Then("cart should contain {string}")
    public void cart_should_contain(String productName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'" + productName + "')]")));
        Assert.assertTrue(driver.getPageSource().contains(productName));
        driver.quit();
    }

    @When("user opens contact form")
    public void open_contact_form() {
        driver.findElement(By.linkText("Contact")).click(); // Opens modal
    }

    @Then("contact form should be visible")
    public void verify_contact_form_visible() {
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("exampleModal")));
        Assert.assertTrue(modal.isDisplayed());
        driver.quit();
    }

    @Then("user should see the homepage")
    public void user_should_see_the_homepage() {
        boolean homeVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nava"))).isDisplayed();
        Assert.assertTrue(homeVisible, "Homepage is not visible.");
        driver.quit();
    }
}
