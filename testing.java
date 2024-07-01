package com.fcai.SoftwareTesting;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;

@SpringBootApplication
public class SoftwareTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftwareTestingApplication.class, args);
	}


	

	@Test
	public void testSearchForAMovie() throws InterruptedException {
		System.setProperty("webdriver.firefox.driver", "C:\\Users\\hp\\Downloads\\geckodriver-v0.34.0-win-aarch64\\geckodriver");
		WebDriver webDriver=new FirefoxDriver();
		webDriver.navigate().to("https://www.imdb.com");

		WebElement searchBox = webDriver.findElement(By.id("suggestion-search"));
		searchBox.sendKeys("The Shawshank Redemption");
		Thread.sleep(3000);

		WebElement searchButton = webDriver.findElement(By.id("suggestion-search-button"));
		searchButton.click();
		Thread.sleep(3000);

		Assert.assertEquals("The Shawshank Redemption", webDriver.findElement(By.xpath("//*[@id=\"__next\"]/main/div[2]/div[3]/section/div/div[1]/section[2]/div[2]/ul/li[1]/div[2]/div/a")).getText());
		Assert.assertEquals("1994", webDriver.findElement(By.xpath("//*[@id=\"__next\"]/main/div[2]/div[3]/section/div/div[1]/section[2]/div[2]/ul/li[1]/div[2]/div/ul[1]/li/span")).getText());
		Assert.assertEquals("Tim Robbins, Morgan Freeman", webDriver.findElement(By.xpath("//*[@id=\"__next\"]/main/div[2]/div[3]/section/div/div[1]/section[2]/div[2]/ul/li[1]/div[2]/div/ul[2]/li/span")).getText());
		webDriver.close();
	}


	@Test
	public void testTopRatedMovies() throws InterruptedException {
		System.setProperty("webdriver.firefox.driver", "C:\\Users\\hp\\Downloads\\geckodriver-v0.34.0-win-aarch64\\geckodriver");

		WebDriver webDriver=new FirefoxDriver();
		webDriver.navigate().to("https://www.imdb.com");

		WebElement menuButton = webDriver.findElement(By.id("imdbHeader-navDrawerOpen"));
		menuButton.click();

		WebElement topMoviesChoice=webDriver.findElement(By.xpath("//*[@id=\"imdbHeader\"]/div[2]/aside[1]/div/div[2]/div/div[1]/span/div/div/ul/a[2]"));
		topMoviesChoice.click();
		Thread.sleep(2000);
		Assert.assertEquals("IMDb Top 250 Movies", webDriver.findElement(By.xpath("//*[@id=\"__next\"]/main/div/div[3]/section/div/div[1]/div/div[2]/hgroup/h1")).getText());
		Assert.assertEquals("1. The Shawshank Redemption", webDriver.findElement(By.xpath("//*[@id=\"__next\"]/main/div/div[3]/section/div/div[2]/div/ul/li[1]/div[2]/div/div/div[1]")).getText());

		Thread.sleep(2000);
		webDriver.close();
	}



	@Test
	public void testSearchForMoviesRealeasedInASpecificYear() throws InterruptedException {
		System.setProperty("webdriver.firefox.driver", "C:\\Users\\hp\\Downloads\\geckodriver-v0.34.0-win-aarch64\\geckodriver");

		WebDriver webDriver = new FirefoxDriver();
		webDriver.navigate().to("https://www.imdb.com");
		Thread.sleep(2000);
		WebElement allDropDownMenu = webDriver.findElement(By.xpath("//*[@id=\"nav-search-form\"]/div[1]"));
		allDropDownMenu.click();
		WebElement advancedSearchChoice = webDriver.findElement(By.xpath("//*[@id=\"navbar-search-category-select-contents\"]/ul/a/span[1]"));
		advancedSearchChoice.click();
		WebElement titleType = webDriver.findElement(By.xpath("//*[@id=\"titleTypeAccordion\"]/div[1]"));
		titleType.click();
		WebElement movieChoice = webDriver.findElement(By.xpath("//*[@id=\"accordion-item-titleTypeAccordion\"]/div/section/button[1]"));
		movieChoice.click();
		Thread.sleep(4000);

		WebElement genre = webDriver.findElement(By.xpath("//*[@id=\"genreAccordion\"]"));
		((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", genre);
		((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,250)", "");//to scroll down
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(genre));
		genre.click();
		WebElement genreChoice=webDriver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[3]/section/section/div/section/section/div[2]/div/section/div[2]/div[1]/section/div/div[6]/div[2]/div/section/button[1]"));
		genreChoice.click();
		Thread.sleep(4000);
		WebElement releaseDate=webDriver.findElement(By.xpath("//*[@id=\"releaseDateAccordion\"]"));
		releaseDate.click();
		Thread.sleep(2000);

		((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,300)", "");
		Thread.sleep(3000);

		WebElement fromYearInput = webDriver.findElement(By.name("release-yearmonth-start-input"));
		Thread.sleep(3000);
		fromYearInput.sendKeys("2010");

		WebElement toYearInput = webDriver.findElement(By.name("release-yearmonth-end-input"));
		Thread.sleep(3000);
		toYearInput.sendKeys("2020");

		WebElement results=webDriver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[3]/section/section/div/section/section/div[2]/div/section/div[1]/button"));
		results.click();
		Thread.sleep(3000);

		Assert.assertEquals("Popularity", webDriver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[3]/section/section/div/section/section/div[2]/div/section/div[2]/div[2]/div[1]/div[2]/div/span/span/label")).getText());
		webDriver.close();
	}
}