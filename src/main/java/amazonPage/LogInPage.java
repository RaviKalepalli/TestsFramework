package amazonPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import page.AbstractPage;

public class LogInPage extends AbstractPage {

	public LogInPage() {
		super();
		url = props.getProperty("amazon.signin.page");
	}

	@Override
	protected void verifyPage() {
		LOGGER.debug("Verifying LogIn page loaded");
		By signInBox = new By.ByCssSelector("div[class$='a-padding-extra-large']");
		try {
			wdwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(signInBox));
		} catch (Exception e) {
			String message = "The Amazon SignIn page did not load";
			LOGGER.error(message, e);
		}
	}
	
	public AccountPage SignInAsAUser(){
		By email = new By.ByCssSelector("input#ap_email");
		By password = new By.ByCssSelector("input#ap_password");
		By SignInButton = new By.ByCssSelector("input#signInSubmit");
		String userName = props.getProperty("user.name");
		String pass = props.getProperty("pass");
		
		LOGGER.info("Input user credentials");
		WebElement weEmail = driver.findElement(email);
		weEmail.sendKeys(userName);
		WebElement wePassword = driver.findElement(password);
		wePassword.sendKeys(pass);
		WebElement weSignInButton = driver.findElement(SignInButton);
		weSignInButton.click();
		
		AccountPage accountPage = new AccountPage();
		accountPage.verifyPage();
		return accountPage;
		
		
	}

}
