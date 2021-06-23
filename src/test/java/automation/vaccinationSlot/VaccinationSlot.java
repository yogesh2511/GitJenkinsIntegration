package automation.vaccinationSlot;


import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VaccinationSlot {

	WebDriver driver = null;

	@Test(priority = 0)
	public void openBrowser() {
		driver.get("https://www.cowin.gov.in/home");
		driver.manage().window().maximize();
		assertEquals(driver.getTitle(), "CoWIN");
	}

	
	@Test(groups= {"SearchByPIN"},enabled=false)
	public void searchByPIN() throws InterruptedException {

		driver.findElement(waitForElementToBeClickable(By.xpath("//input[contains(@placeholder,'Enter your PIN')]")))
				.sendKeys("39007" + Keys.ENTER);
		

	}
	@Test(groups= {"SearchByDistrict"})
	public void searchByDistrict() throws InterruptedException {

		WebElement sbdOption = driver.findElement(waitForElementToBeClickable(By.xpath("//*[@data-checked='Search By District']")));
		sbdOption.click();
		WebElement stateDrpdown = driver.findElement(waitForElementToBeClickable(By.xpath("//div[contains(@class,'mat-select-arrow-wrapper ng-tns-c64-1')]")));
		stateDrpdown.click();
		
		findElementUsingText("Gujarat").click();
		
		
		WebElement cityDrpdown = driver.findElement(waitForElementToBeClickable(By.xpath("//div[contains(@class,'mat-select-arrow-wrapper ng-tns-c64-3')]")));
		
		
		cityDrpdown.click();
	     findElementUsingText("Vadodara Corporation").click();
		
	 
		findElementUsingText("Search").click();
		Thread.sleep(5000);
		//findElementUsingText("Age 18+").click();	
		
		emailSent();
		

	}

	@BeforeTest
	public void beforeTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@AfterTest
	public void afterTest() throws IOException, InterruptedException {
		Thread.sleep(5000);
		driver.close();
		driver.quit();
	}

	@AfterSuite
	public void afterSuite() throws IOException {
		Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
	}

	public By waitForElementToBeClickable(By element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(200));
		wait.until(ExpectedConditions.elementToBeClickable(element)) ;
		return element;
	}
	
	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public WebElement findElementUsingText(String elementName) {
		
		WebElement SearchedElement = driver.findElement(waitForElementToBeClickable(By.xpath("//*[contains(text(),'"+elementName+"')]")));
		scrollToElement(SearchedElement);
		return SearchedElement;
	}
	
	
public void emailSent() {
		
		 List<WebElement> SearchedElement = driver.findElements(By.xpath("//div[@class='vaccine-box vaccine-box1 vaccine-padding']/a"));
		for (WebElement webElement : SearchedElement) {
			scrollToElement(webElement);
			int value =isNumeric(webElement.getText());
			if(value>0)
			{
				//MonitoringMail mail= new MonitoringMail();
				System.out.println(value+" slots available and mail should be triggered");
				//mail.sendmail(host, port, from, password, to, cc, username, subject, messagebody, attachmentpath, attachmentname);
				
			}
		}
		
	}
public static int isNumeric(String string) {
    int intValue = 0;
		
    //System.out.println(String.format("Parsing string: \"%s\"", string));
		
    if(string == null || string.equals("") || string.equals(" NA ")|| string.equals("  Booked  ")) {
       // System.out.println("String cannot be parsed, it is null or empty.");
       // return intValue;
    }
    
    try {
        intValue = Integer.parseInt(string);
        return intValue;
    } catch (NumberFormatException e) {
       // System.out.println("Input String cannot be parsed to Integer.");
    }
    return intValue;
}
	//div[@class='vaccine-box vaccine-box1 vaccine-padding']/a
}
