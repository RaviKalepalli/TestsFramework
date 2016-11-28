package amazonPage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.AbstractPage;
import util.TestUtil;

public class AmazonFrontPage extends AbstractPage {

	public AmazonFrontPage() {
		super();
		url = props.getProperty("amazon.front.page");
	}
	

	public ResultsPage searchQuery(String searchQuery) {
		By searchBar = new By.ByXPath("//input[@id='twotabsearchtextbox']");

		LOGGER.info("Locating search bar");
		WebElement weSearchBar = driver.findElement(searchBar);
		weSearchBar.sendKeys(searchQuery + Keys.ENTER);

		ResultsPage resultsPage = new ResultsPage();
		resultsPage.verifyPage();

		return resultsPage;
	}

	public void HoverSignIn() {
		By signInLink = new By.ByCssSelector("a[data-nav-ref='nav_ya_signin']");
		
		WebElement weSignInLink = driver.findElement(signInLink);

		Actions actionBuilder = new Actions(driver);
		actionBuilder.moveToElement(weSignInLink).perform();
		TestUtil.sleep(500);
	}
	
	public LogInPage ClickSignInButton(){
		By signInButton = new By.ByCssSelector("div#nav-flyout-ya-signin span");
        driver.findElement(signInButton).click();
        
        LOGGER.info("Click Sign In button");
        LogInPage logInPage = new LogInPage();
        logInPage.verifyPage();
		return null;
	}

	@Override
	protected void verifyPage() {
		LOGGER.debug("Verifying page loaded");
		By navbar = new By.ByCssSelector("#navbar");
		try {
			wdwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(navbar));
		} catch (Exception e) {
			String message = "The Amazon Front page did not load";
			LOGGER.error(message, e);
		}

	}

}
