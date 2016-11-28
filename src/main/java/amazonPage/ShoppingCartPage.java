package amazonPage;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.DriverManager;

import java.util.List;

public class ShoppingCartPage {

	final static Logger LOGGER = Logger.getLogger(ItemPage.class);

	WebDriver driver;
	WebDriverWait wdWait;

	By navBar = new By.ByXPath("//div[@id='navbar']");

	public ShoppingCartPage() {
		this.driver = DriverManager.getDriver();
		wdWait = new WebDriverWait(driver, 5);
	}

	public void verifyPage() {

		LOGGER.debug("Verifying page loaded");

		try {
			wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(navBar));
		} catch (Exception e) {
			String message = "The shopping cart page did not load";
			LOGGER.error(message, e);
		}
	}

	public void ValidateCartContains() {
		LOGGER.info("Validating the cart contains 2 results");
		By cartIcon = new By.ByXPath("//a[@id='nav-cart']");
		By cartItems = new By.ByXPath("//div[@class='a-row a-spacing-base a-spacing-top-base']");

		WebElement weCartIcon = driver.findElement(cartIcon);
		weCartIcon.click();
		wdWait.until(ExpectedConditions.visibilityOfElementLocated(cartItems));
		List<WebElement> cartItemsList = driver.findElements(cartItems);
		Assert.assertTrue("The cart does not contain 2 items", cartItemsList.size() == 2);
		LOGGER.info("The cart contain 2 items");
	}

}
