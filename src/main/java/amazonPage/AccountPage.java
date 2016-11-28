package amazonPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import page.AbstractPage;
import util.TestUtil;

public class AccountPage extends AbstractPage {

	public AccountPage() {
		super();
		url = props.getProperty("amazon.account.page");
	}

	@Override
	protected void verifyPage() {
		LOGGER.debug("Verifying Account page loaded");
		By dashBoard = new By.ByCssSelector("div#hud-dashboard");
		try {
			wdwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dashBoard));
		} catch (Exception e) {
			String message = "The Amazon Account page did not load";
			LOGGER.error(message, e);
		}

	}

	public void GoToYourAccount() {
		By yourAccountLink = new By.ByCssSelector("a#nav-link-yourAccount>span.nav-line-2>span.nav-icon.nav-arrow");
		By yourAccountLink1 = new By.ByXPath("//div[@id='nav-flyout-yourAccount']/div[@class='nav-template nav-flyout-content nav-tpl-itemList']/a[1]");
        By accountFacilities = new By.ByCssSelector("div.a-fixed-right-grid-col.a-col-left");
		
        LOGGER.info("Navigating to Your Account");
		WebElement weYourAccountLink = driver.findElement(yourAccountLink);
		Actions actionBuilder = new Actions(driver);
		actionBuilder.moveToElement(weYourAccountLink).perform();
		TestUtil.sleep(500);
		
		WebElement weYourAccountLink1 = driver.findElement(yourAccountLink1);
		weYourAccountLink1.click();
		
		LOGGER.debug("Verifying Account facilities loaded");
		try{
		wdwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(accountFacilities));
		}catch (Exception e) {
			String message = "The Account facilities did not load";
			LOGGER.error(message, e);
	}}
	
	public ChangeAccountSettingsPage LoginAndSecuritySettings() {
		By loginAndSecuritySettings = new By.ByXPath("//a[text()='Login & Security Settings']");
		
		LOGGER.info("Click at Login & Security Settings");
		WebElement weLoginAndSecuritySettings = driver.findElement(loginAndSecuritySettings);
		weLoginAndSecuritySettings.click();
		
		ChangeAccountSettingsPage changeAccountSettingsPage = new ChangeAccountSettingsPage();
		changeAccountSettingsPage.verifyPage();
		return changeAccountSettingsPage;
	}

}
