package com.selenium;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TakeScreenshot {
	 WebDriver driver = null;

		@BeforeClass
		public void driverinstance() {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		}

		@AfterClass
		public void closedriver() {
			// driver.close();
			driver.quit();
		}

		@Test
		public void testBStackTakeScreenShot() throws Exception {
			// goto url
			driver.get("https://www.browserstack.com");
			// Call take screenshot function
			TakeScreenshot.takeSnapShot(driver, "./screenshots/test.png");
		}

		/**
		 * This function will take screenshot
		 * 
		 * @param webdriver
		 * @param fileWithPath
		 * @throws Exception
		 */
		public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
			// Convert web driver object to TakeScreenshot
			TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
			// Call getScreenshotAs method to create image file
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			// Move image file to new destination
			File DestFile = new File(fileWithPath);
			// Copy file at destination
			FileUtils.copyFile(SrcFile, DestFile);
		}
	}

	


