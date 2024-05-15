package com.selenium;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class WindowHandle {
	WebDriver driver = null;

	@BeforeClass
	public void driverInstantiation() {
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

	@Test(description = "windows", priority = 1, groups = "windowhandling")
	public void windowHandling() {

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

	@Test
	@Ignore
	public void wndowHandler2() throws InterruptedException {

	}

}
