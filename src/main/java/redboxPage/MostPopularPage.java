package redboxPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import page.AbstractPage;
import util.TestUtil;

public class MostPopularPage extends AbstractPage {
	
	 public MostPopularPage() {
		 super();
		 url = props.getProperty("redbox.most.popular.page");
	}
	
	@Override
	protected void verifyPage() {
		LOGGER.debug("Verifying Most Popular Page loaded");
		By header = new By.ByCssSelector("//h1[text()='Most Popular']");
		try {
			wdwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(header));
		} catch (Exception e) {
			String message = "The Most Popular Page did not load";
			LOGGER.error(message, e);
		}		
	}
	
	public void PrintAllTitlesAndLengths(){
		
		By allArticlesLocator = new By.ByCssSelector("div.title-box-odopod > a");
		By movieTitleLocator = new By.ByXPath(".//div[@class='title ng-binding']");
		By movieLengthLocator = new By.ByXPath(".//div[@class='movie-length ng-binding ng-scope']");
		
		List<WebElement> articleList = driver.findElements(allArticlesLocator);
		
		LOGGER.info("Printing all popular movies");
		final String movieHorizontalLogSpacer = "=====================================";
		LOGGER.info(movieHorizontalLogSpacer);
		
		for (int i = 0; i<articleList.size(); i++){
			LOGGER.info("Movie#"+(i+1));
			
			WebElement currentMovie = articleList.get(i);
			Actions actionBuilder = new Actions(driver);
			actionBuilder.moveToElement(currentMovie).perform();
			TestUtil.sleep(500);
			
			WebElement currMovieTitle = currentMovie.findElement(movieTitleLocator);
			String MovieName = currMovieTitle.getText();
			LOGGER.info("Movie Name = "+ MovieName);
			
			WebElement currMovieLenght = currentMovie.findElement(movieLengthLocator);
			String MovieLength = currMovieLenght.getText();
			LOGGER.info("Movie Length = "+ MovieLength);
			LOGGER.info(movieHorizontalLogSpacer);
		}
	}

}
