package uitest;

import redboxPage.MostPopularPage;
import redboxPage.RedBoxFrontPage;

import org.junit.Test;

import util.PropertiesLoader;
import framework.FrameworkBase;

public class RedBoxTest extends FrameworkBase {

	PropertiesLoader props = new PropertiesLoader();

//	@Test
	public void batmanRowCheckingTest() {
		String searchPhrase = "Batman";
		int rowNumber = props.getPropertyAsInt("row.number");
		int resultIndex = props.getPropertyAsInt("result.index");

		RedBoxFrontPage frontPage = new RedBoxFrontPage();
		frontPage.openPage();
		frontPage.SearchQuery(searchPhrase);
		frontPage.RowsValidator(rowNumber);
		}
	
	@Test
	public void clickHoverMenu(){
		RedBoxFrontPage frontPage = new RedBoxFrontPage();
		frontPage.openPage();
		frontPage.HoverDvdAndBlueRayReleases();
		frontPage.ClickMostPopular();
		
		MostPopularPage mostPopularPage = new MostPopularPage();
		mostPopularPage.PrintAllTitlesAndLengths();
		
		
	}

}
