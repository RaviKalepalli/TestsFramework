package amazonPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import page.AbstractPage;

public class ChangeAccountSettingsPage extends AbstractPage {
	
	public ChangeAccountSettingsPage() {
		super();
		url = props.getProperty("amazon.account.settings.page");
	}

	@Override
	protected void verifyPage() {
		LOGGER.debug("Verifying Change Account Page loaded");
		By header = new By.ByCssSelector("h1#ap_cnep_header");
		try {
			wdwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(header));
		} catch (Exception e) {
			String message = "The Change Account Page did not load";
			LOGGER.error(message, e);
		}
		
	}
	public void GettingOwnerName(){
		LOGGER.info("Getting Account Owner Name");
		By name = new By.ByXPath("(//div[@class='a-fixed-right-grid-col a-col-left'])[1]/div[@class='a-row'][2]");
	    WebElement weName = driver.findElement(name);
	    LOGGER.info("The Name is "+weName.getText());
	
	}

}
