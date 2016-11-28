package amazonPage;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.DriverManager;

public class ItemPage {

	final static Logger LOGGER = Logger.getLogger(ItemPage.class);

	WebDriver driver;
	WebDriverWait wdWait;

	By itemTitle = new By.ByXPath("//h1[@id='title']");

	public ItemPage() {
		this.driver = DriverManager.getDriver();
		wdWait = new WebDriverWait(driver, 5);
	}

	public void verifyPage() {

		LOGGER.debug("Verifying page loaded");

		try {
			wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemTitle));
		} catch (Exception e) {
			String message = "The results page did not load";
			LOGGER.error(message, e);
		}
	}

	public void PriceValidator() {
		LOGGER.info("Verifying the price is less than 50");

		By priceXpath = new By.ByXPath("//span[@id='priceblock_ourprice']");
		WebElement wePrice = driver.findElement(priceXpath);
		double priceValue = Double.parseDouble(wePrice.getText().substring(1));

		Assert.assertTrue("The item price is $" + priceValue + " > $ 50 ", priceValue <= 50.00);
		LOGGER.info("The item price is $" + wePrice.getText().substring(1) + " hense it is less than $50");
	}

	public void FreeShippingValidator() {
		LOGGER.info("Verifying the Free shipping is available");
		By shippingXpath = new By.ByXPath("(//span[@id='price-shipping-message'])[1]/b");

		WebElement weShipping = driver.findElement(shippingXpath);
		String freeShipping = "FREE Shipping";
		Assert.assertTrue("The item does not have a Free shipping available",
				freeShipping.equals(weShipping.getText()));
		LOGGER.info("Item does have the FREE Shipping option");

	}

	public ShoppingCartPage AddToCart(String searchPhrase) {
		LOGGER.info("Addind to Cart " + searchPhrase);
		By addToCartButtonXpath = new By.ByXPath("//input[@id='add-to-cart-button']");
		By oneTimeRadioButtontXpath = new By.ByXPath("(//i[@class='a-icon a-icon-radio'])[2]");
		By actionPanel = new By.ByXPath("//div[@id='ap-options']");

		Boolean subscriptionIsPresent = driver.findElements(actionPanel).size() > 0;

		WebElement weAddToCart = driver.findElement(addToCartButtonXpath);

		if (subscriptionIsPresent) {
			WebElement weOneTimeRadioButton = driver.findElement(oneTimeRadioButtontXpath);
			weOneTimeRadioButton.click();
			wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addToCartButtonXpath));
			weAddToCart.click();
		} else {
			weAddToCart.click();
		}

		ShoppingCartPage cartPage = new ShoppingCartPage();
		cartPage.verifyPage();

		return cartPage;

	}
}
