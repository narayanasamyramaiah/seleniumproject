package com.selenium;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class WindowHandle {
	 WebDriver driver;
//https://github.com/testng-team/testng/issues/2169 --> while using groups , use alwaysrun=true for even Before annotations and after in testng 
	 //To fix webdriver null exception
	@BeforeSuite(alwaysRun = true)
	public void driverInstantiation() {
	    try {
	        // Initialize WebDriver
	        driver = new FirefoxDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	        System.out.println("WebDriver initialized successfully.");
	    } catch (Exception e) {
	        System.out.println("Failed to initialize WebDriver: " + e.getMessage());
	    }
	}

	@AfterSuite(alwaysRun = true)
	public void closedriver() {
	    if (driver != null) {
	        driver.quit();
	        System.out.println("WebDriver quit successfully.");
	    }
	}


	@Test(description = "windows", priority = 1, groups = {"windowhandling"})
	public void windowHandling() {

		 if (driver == null) {
		        System.out.println("WebDriver is null. Skipping test.");
		        return;
		    }

		
		// Launching the site.
		driver.get("https://demo.guru99.com/popup.php");
		driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).click();

		String MainWindow = driver.getWindowHandle(); // this will store the original browser window thats started in
														// the begining
		System.out.println("WindowId:" + MainWindow);
		// To handle all new opened window.
		Set<String> s1 = driver.getWindowHandles(); // this looks for all browser tabs(child) opened including the
													// parent
		Iterator<String> i1 = s1.iterator();

		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			System.out.println("WindowId:" + ChildWindow);
			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {

				// Switching to Child window
				driver.switchTo().window(ChildWindow);
				driver.findElement(By.name("emailid")).sendKeys("abc@gmail.com");
				driver.findElement(By.name("btnLogin")).click();
				// Closing the Child Window.
				driver.close();
			}
		}
		// Switching to Parent window i.e Main Window.
		driver.switchTo().window(MainWindow);

	}

	@Test(description = "windows", priority = 2, groups = {"windowhandling"})

	public void wndowHandler2() throws InterruptedException {

		 if (driver == null) {
		        System.out.println("WebDriver is null. Skipping test.");
		        return;
		    }

		driver.get("https://www.hyrtutorials.com/p/window-handles-practice.html");
		driver.findElement(By.cssSelector("#newWindowBtn")).click();
		Thread.sleep(3000);
		String MainWindow = driver.getWindowHandle();
		Set<String> allwindows = driver.getWindowHandles();
		Iterator<String> it = allwindows.iterator();
		while (it.hasNext()) {
			String childWindow = it.next();
			if (!MainWindow.equalsIgnoreCase(childWindow)) {
				driver.switchTo().window(childWindow);
				System.out.println(driver.getTitle());
				Assert.assertTrue(driver.getTitle().contains("Basic Controls"),
						"ChildWindow Not Navigated to the right one");
				driver.close();
			}

		}
		// Switching to Parent window i.e Main Window.
		driver.switchTo().window(MainWindow);
	}

}
