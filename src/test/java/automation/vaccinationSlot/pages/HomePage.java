package automation.vaccinationSlot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import automation.vaccinationSlot.testBase.TestBase;

public class HomePage extends TestBase {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement signIn() {

		WebElement signIn = driver.findElement(By.xpath("//a[@title='Register/Sign In youself']"));
		return signIn;
	}
}
