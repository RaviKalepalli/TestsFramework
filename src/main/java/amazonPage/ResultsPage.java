package amazonPage;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.DriverManager;

public class ResultsPage {

	final static Logger LOGGER = Logger.getLogger(ResultsPage.class);

	WebDriver driver;
	WebDriverWait wdWait;

	By resultsXpath = By.xpath(
			"//ul[@id='s-results-list-atf']//div[@class='s-item-container']//a[@class='a-link-normal s-access-detail-page  a-text-normal']");

	public ResultsPage() {
		this.driver = DriverManager.getDriver();
		wdWait = new WebDriverWait(driver, 5);
	}

	public void verifyPage() {
		try {
			wdWait.until(ExpectedConditions.visibilityOfElementLocated(resultsXpath));
		} catch (Exception e) {
			String message = "Error waiting for row";
			LOGGER.error(message, e);
			Assert.fail(message);
		}
	}

	public void ValidateItemPosition(String searchPhrase, int itemPosition) {
		int position = 1;
		List<WebElement> allResults = driver.findElements(resultsXpath);
		try {
			for (WebElement resultItem : allResults) {
				position++;
				String itemTitle = resultItem.getText();
				if (itemTitle.startsWith(searchPhrase)) {
					position = position - 1;
				}
				break;

			}
		} catch (Exception e) {
			throw new RuntimeException("The " + searchPhrase + " item is not present on the results list!");
		}
		Assert.assertTrue("The " + searchPhrase + " item is NOT first in the list !!! The position is " + position,
				position == itemPosition);
		LOGGER.info("The " + searchPhrase + " item is first in the list");
	}

	public void PrimeValidatorFirstResult() {
		By firstResultPrimeXpath = new By.ByXPath(
				"(//div[@class='s-item-container'])[1]//i[@class='a-icon a-icon-prime a-icon-small s-align-text-bottom']");

		LOGGER.info("Validating the first result has Prime shipping");
		try {
			WebElement wePrime = driver.findElement(firstResultPrimeXpath);
		} catch (Exception e) {
			LOGGER.error("The first result does not have a Prime shipping");
		}
		LOGGER.info("The first result has a Prime shipping");
	}

	public void BestSellerValidatorFirstResult() {
		By firstResultBestSellerXpath = new By.ByXPath(
				"(//span[@class='aok-float-left sx-badge-rectangle sx-bestseller-color'])[1]");

		LOGGER.info("Validating the first result is Best Seller");
		try {
			WebElement weFirstBestSeller = driver.findElement(firstResultBestSellerXpath);
		} catch (Exception e) {
			LOGGER.error("The first result is not a Best Seller");
		}
		LOGGER.info("The first result is a Best Seller");
	}

	public ItemPage ClickTheItem(String searchPhrase) {

		By firstResultXpath = new By.ByXPath(
				"(//div[@class='s-item-container'])[1]//a[@class='a-link-normal s-access-detail-page  a-text-normal']");

		WebElement weFirstResultXpath = driver.findElement(firstResultXpath);
		LOGGER.info("Clicking at " + searchPhrase);
		weFirstResultXpath.click();

		ItemPage articlePage = new ItemPage();
		articlePage.verifyPage();

		return articlePage;
	}
}
