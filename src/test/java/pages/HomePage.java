package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    By signUpButton = By.id("signin2");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSignUp() {
        driver.findElement(signUpButton).click();
    }
}
