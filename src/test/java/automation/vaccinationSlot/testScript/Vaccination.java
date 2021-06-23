package automation.vaccinationSlot.testScript;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import automation.vaccinationSlot.pages.HomePage;
import automation.vaccinationSlot.testBase.TestBase;

public class Vaccination extends TestBase {


	@Test(priority=0)
	public void navigatoHomepage() {

		driver = intializeBrowser();
		driver.get(config.getProperty("url"));
	}

	@Test(priority=1)
	public void login() {

		HomePage home= new HomePage(driver);
		home.signIn().click();
	}
	
	@AfterSuite
	public void closBrowser() throws IOException
	{
		Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
	}
}
