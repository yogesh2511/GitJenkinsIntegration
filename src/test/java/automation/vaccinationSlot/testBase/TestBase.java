package automation.vaccinationSlot.testBase;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import automation.vaccinationSlot.helper.generic.ResourceHelper;
import automation.vaccinationSlot.helper.logger.LoggerHelper;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	Logger log = LoggerHelper.getLogger(TestBase.class);

	public WebDriver driver;
	public static Properties config = new Properties();
	public static FileInputStream fis;
	static {
		try {
			config.load(
					ResourceHelper.getResourcePathInputStream("\\src\\main\\resources\\configfile\\config.properties"));

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public WebDriver intializeBrowser() {

		try {
			log.info("Browser intialized");
			String browserName = config.getProperty("browser");
			if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("edge")) {

				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} else {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}
}
