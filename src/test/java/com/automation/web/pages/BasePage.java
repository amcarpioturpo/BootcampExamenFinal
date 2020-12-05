package com.automation.web.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Parent of the other classes of pages.
 * @author juan.montes
 *
 * Modified and increased funcionality for ESPN FinalExam
 * @author alejandro.carpio
 */
public class BasePage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	public Logger log = Logger.getLogger(BasePage.class);
	
	/**
	 * Constructor.
	 * @param pDriver WebDriver
	 */
	public BasePage (WebDriver pDriver) {
		PageFactory.initElements(pDriver, this);
		wait = new WebDriverWait(pDriver, 20);
		driver = pDriver;
	}

	/**
	 * Get the web driver wait.
	 * @return {@link WebDriverWait}
	 */
	protected WebDriverWait getWait() {
		return wait;
	}

	/**
	 * Get the  web driver.
	 * @return {@link WebDriver}
	 */
	protected WebDriver getDriver() {
		return driver;
	}
	
	/**
	 * Close the web driver.
	 */
	public void dispose() {
		if (driver != null) {
			driver.quit();
		}
	}

	/**
	 * Waits for element to be visible.
	 * @param element WebElement
	 */
	public void waitElementVisibility(WebElement element) {
		getWait().until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Waits for elements to be visible.
	 * @param elements list WebElement
	 */
	public void waitElementsVisibility(List<WebElement> elements) {
		getWait().until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	/**
	 * Waits for element to be non-visible for specific situations
	 * @param element WebElement to detect if it disappears
	 */
	public void waitElementNonVisibility(WebElement element){
		getWait().until(ExpectedConditions.invisibilityOf(element));
	}

	/**
	 * Waits for element to be ready to click
	 * @param element WebElement to detect if it disappears
	 */
	public void waitElementIsClickable(WebElement element){
		getWait().until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Hover over an element
	 * @param element WebElement to hover over
	 */
	public void mouseHover(WebElement element){
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	/**
	 * Clicks on an element
	 * @param element WebElement to click
	 */
	public void clickOnElement(WebElement element){
		element.click();
	}

	/**
	 * Sends an String to a WebElement box
	 * @param element WebElement box to send the text to
	 * @param content String text
	 */
	public void sendKeysToElement(WebElement element, String content){
		element.clear();
		element.click();
		element.sendKeys(content);
	}

}
