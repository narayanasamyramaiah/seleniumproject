package com.selenium;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class ActionsMethod {
	static WebDriver driver = null;

	@Before
	public void driverinstance() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	}

	@After
	public void closedriver() {
		// driver.close();
		driver.quit();
	}

	@Test @Ignore
	public void pause() {
		try {
			driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");

			long start = System.currentTimeMillis();

			WebElement clickable = driver.findElement(By.id("clickable"));
			new Actions(driver).moveToElement(clickable).pause(Duration.ofSeconds(1)).clickAndHold()
					.pause(Duration.ofSeconds(1)).sendKeys("abc").perform();

			long duration = System.currentTimeMillis() - start;
			Assert.assertTrue(duration > 2000);
			Assert.assertTrue(duration < 3000);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test @Ignore
	public void mouseMovement() {
		try {
			String baseURL = "https://www.browserstack.com/";

			driver.get(baseURL); // -> launch the url
			((JavascriptExecutor) driver).executeScript("scroll(0,300)");
			Actions ac = new Actions(driver);

			WebElement live = driver.findElement(By.cssSelector("#products-dd-toggle"));
			ac.moveToElement(live).build().perform();
			//Thread.sleep(3000);
			WebElement live1 = driver
					.findElement(By.xpath("//button[@title='App Testing' and contains(@id,'products')]"));
			ac.moveToElement(live1).build().perform();

		//	Thread.sleep(3000);
			WebElement automate = driver.findElement(By.xpath("(//a[@title='App Automate'])[1]"));
			automate.click();
		//	Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.urlContains("https://www.browserstack.com/app-automate"));
			System.out.println(driver.getCurrentUrl());
			Assert.assertTrue(driver.getCurrentUrl().contains("https://www.browserstack.com/app-automate"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test @Ignore
	public void doubleClick() {
		
		try
		{
			driver.get("https://www.browserstack.com/");
			Actions a = new Actions(driver);

			// Double click on element

			WebElement trialaction = driver.findElement(By.xpath("//a[@title='Free Trial']"));
			a.doubleClick(trialaction).perform();
		//	Thread.sleep(2000);
			Assert.assertEquals("https://www.browserstack.com/users/sign_up", driver.getCurrentUrl());
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		
	}
	 @Test
	    public void releasesAll() {
	        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");

	        WebElement clickable = driver.findElement(By.id("clickable"));
	        Actions actions = new Actions(driver);
	        actions.clickAndHold(clickable)
	                .keyDown(Keys.SHIFT)
	                .sendKeys("a")
	                .perform();

	        ((RemoteWebDriver) driver).resetInputState();

	        actions.sendKeys("a").perform();
	        Assert.assertEquals("A", String.valueOf(clickable.getAttribute("value").charAt(0)));
	        Assert.assertEquals("a", String.valueOf(clickable.getAttribute("value").charAt(1)));
	    }

}
