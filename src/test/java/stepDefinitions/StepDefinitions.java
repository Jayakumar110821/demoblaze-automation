package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert; // ✅ Correct import

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class StepDefinitions {

    WebDriver driver;

    @Given("user opens the browser")
    public void user_opens_browser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @When("user navigates to {string}")
    public void user_navigates_to(String url) {
        driver.get(url);
    }

    @Then("user should see the homepage")
    public void user_should_see_homepage() {
        Assert.assertTrue(driver.getTitle().contains("STORE")); // ✅ Works now
        driver.quit();
    }

    @When("user clicks on Sign Up")
    public void user_clicks_sign_up() {
        driver.findElement(By.id("signin2")).click();
    }

    @Then("user should see the Sign Up modal")
    public void user_sees_signup_modal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signInModal")));
        Assert.assertTrue(modal.isDisplayed());
        driver.quit();
    }
}
