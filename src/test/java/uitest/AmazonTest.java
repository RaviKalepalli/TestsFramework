package uitest;

import org.junit.Test;

import amazonPage.AccountPage;
import amazonPage.AmazonFrontPage;
import amazonPage.ChangeAccountSettingsPage;
import amazonPage.ItemPage;
import amazonPage.LogInPage;
import amazonPage.ResultsPage;
import amazonPage.ShoppingCartPage;
import util.PropertiesLoader;
import framework.FrameworkBase;

public class AmazonTest extends FrameworkBase {

	PropertiesLoader props = new PropertiesLoader();

//	@Test
	public void amazonRokuStreamingStickTest() {
		String searchPhrase = "Roku Streaming Stick (3600R) (2016 Model)";
		int itemPosition = props.getPropertyAsInt("item.position");

		AmazonFrontPage frontPage = new AmazonFrontPage();
		frontPage.openPage();
		ResultsPage resultsPage = frontPage.searchQuery(searchPhrase);
		resultsPage.ValidateItemPosition(searchPhrase, itemPosition);
		resultsPage.ClickTheItem(searchPhrase);
		ItemPage itemPage = new ItemPage();
		itemPage.PriceValidator();
		itemPage.FreeShippingValidator();
	}

//	@Test
	public void CartTest() {
		String searchPhrase1 = "Great Northern Popcorn Original Stainless Steel Stove Top";
		String searchPhrase2 = "Gold Medal Flavacol";

		AmazonFrontPage frontPage = new AmazonFrontPage();
		frontPage.openPage();
		ResultsPage resultsPage = frontPage.searchQuery(searchPhrase1);
		resultsPage.PrimeValidatorFirstResult();
		resultsPage.ClickTheItem(searchPhrase1);
		ItemPage itemPage = new ItemPage();
		itemPage.AddToCart(searchPhrase1);
		resultsPage = frontPage.searchQuery(searchPhrase2);
		resultsPage.BestSellerValidatorFirstResult();
		resultsPage.ClickTheItem(searchPhrase2);
		itemPage.AddToCart(searchPhrase2);
		ShoppingCartPage shopCart = new ShoppingCartPage();
		shopCart.verifyPage();
		shopCart.ValidateCartContains();
	}

	@Test
	public void LogInTest() {
		AmazonFrontPage frontPage = new AmazonFrontPage();
		frontPage.openPage();
		frontPage.HoverSignIn();
		frontPage.ClickSignInButton();
		LogInPage logInPage = new LogInPage();
		logInPage.SignInAsAUser();
		AccountPage accountPage = new AccountPage();
		accountPage.GoToYourAccount();
		accountPage.LoginAndSecuritySettings();
		ChangeAccountSettingsPage changeAccountOwnerName = new ChangeAccountSettingsPage();
		changeAccountOwnerName.GettingOwnerName();
		
	}

}
