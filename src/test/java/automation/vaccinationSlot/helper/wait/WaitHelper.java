package automation.vaccinationSlot.helper.wait;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import automation.vaccinationSlot.helper.logger.LoggerHelper;

public class WaitHelper {

	 WebDriver driver;

	private static Logger log = LoggerHelper.getLogger(WaitHelper.class);

	public WaitHelper(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * This method will give FluentWait object
	 * 
	 * @param timeoutInMillisecond
	 * @param poolingInterval
	 * @return FluentWait
	 */
	public FluentWait<WebDriver> getFluentWait(int timeoutInMillisecond, int poolingInterval) {
		log.info("waitng for timeout:" + timeoutInMillisecond + "for every:" + poolingInterval);
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.withTimeout(Duration.ofMillis(timeoutInMillisecond));
		wait.pollingEvery(Duration.ofMillis(poolingInterval));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		return wait;
	}

	/**
	 * This method will wait for element to be visible.
	 * 
	 * @param element
	 * @param timeoutInMillisecond
	 * @param poolingInterval
	 * @return
	 */
	public WebElement waitForElementTobeVisible(WebElement element, int timeoutInMillisecond, int poolingInterval) {
        log.info("waiting for element to be visible:"+element.toString()+"in seconds:"+timeoutInMillisecond);
		FluentWait<WebDriver> fwait = getFluentWait(timeoutInMillisecond, poolingInterval);
		fwait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}
	
	public WebElement waitForElementTobeClickable(WebElement element, int timeoutInMillisecond, int poolingInterval) {
        log.info("waiting for element to be visible:"+element.toString()+"in seconds:"+timeoutInMillisecond);
		FluentWait<WebDriver> fwait = getFluentWait(timeoutInMillisecond, poolingInterval);
		fwait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}
}
