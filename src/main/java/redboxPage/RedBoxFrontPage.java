package redboxPage;

import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import amazonPage.ChangeAccountSettingsPage;
import page.AbstractPage;
import util.PropertiesLoader;
import util.TestUtil;

public class RedBoxFrontPage extends AbstractPage{

	By rowsXpath = By.xpath("//div[@class='row extra-padding ng-scope']");

	public RedBoxFrontPage(){
		super();
		url = props.getProperty("redbox.front.page");
	}
	
	@Override
	protected void verifyPage() {
		By redBoxLogo = new By.ByCssSelector("a#headlogolink");
		wdwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(redBoxLogo));
	}

	public void SearchQuery(String searchQuery) {
		By searchIcon = new By.ByXPath("//a[@id='searchdigital0_SearchIcon']");
		By searchBar = new By.ByXPath("//input[@id='searchdigital0_SearchBox']");
		By videoResultRows = new By.ByXPath("//div[@class='row extra-padding ng-scope']");

		System.out.println("Clicking Search button");
		WebElement weSearchIcon = driver.findElement(searchIcon);
		weSearchIcon.click();
		wdwait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));

		System.out.println("Locating search bar");
		WebElement weSearchBar = driver.findElement(searchBar);
		weSearchBar.sendKeys("Batman" + Keys.ENTER);
		wdwait.until(ExpectedConditions.visibilityOfElementLocated(rowsXpath));
	}

	public void RowsValidator(int rowNumber) {
		List<WebElement> allResultRows = driver.findElements(rowsXpath);
		PropertiesLoader props = new PropertiesLoader();
		rowNumber = props.getPropertyAsInt("row.number");

		if (rowNumber<=0 ||rowNumber > allResultRows.size()) {
			
			String message = "The row number " + rowNumber + " does not exist";
			message = String.format("Row number %d does not exist - %d rows found", rowNumber, allResultRows.size());
			System.out.println(message);
			Assert.fail(message);
		}
		WebElement weRow = allResultRows.get(rowNumber - 1);

		By articlesXpath = By.xpath("./div/div/a");
		List<WebElement> articleList = weRow.findElements(articlesXpath);
		System.out.println("The size of the row is " + articleList.size());
	}
	
	public void HoverDvdAndBlueRayReleases(){
		By dvdAndBlueRay = new By.ByCssSelector("li#moviesbutton > a");
		By dvdAndBlueRayMenu = new By.ByCssSelector("div[class$='nav-flyout-movies']");
		
		driver.findElement(dvdAndBlueRay);
		WebElement weDvdAndBlueRay = driver.findElement(dvdAndBlueRay);
		
		Actions actionBuilder = new Actions(driver);
		actionBuilder.moveToElement(weDvdAndBlueRay).perform(); //hovering the element
		TestUtil.sleep(500);
		}
	
	public MostPopularPage ClickMostPopular(){
		By mostPopular = new By.ByCssSelector("div.flyout-section-wrap > div > ul > li:nth-child(3) > a");
		driver.findElement(mostPopular).click();

		MostPopularPage mostPopularPage = new MostPopularPage();
		mostPopularPage.verifyPage();
		return mostPopularPage;
	}

}
