package com.testng;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParallelTest {

	WebDriver driver;
	
	@BeforeClass
	public void cleanupProcess()
	{
		// for saferside killing the process with java
				try {
					KillChromeDriver();
					KillFirefoxDriver();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	public void KillChromeDriver() {

		// Specify the process name to kill
		String processName = "chromedriver.exe";

		try {
			// Execute taskkill command to kill the process
			Process process = Runtime.getRuntime().exec("taskkill /F /IM " + processName);

			// Wait for the process to finish
			process.waitFor();

			// Check if the process exited successfully
			if (process.exitValue() == 0) {
				System.out.println("Successfully killed " + processName);
			} else {
				System.out.println("Failed to kill " + processName);
			}
		} catch (IOException | InterruptedException e) {
			// Handle exceptions
			e.printStackTrace();
		}
	}
	
	public void KillFirefoxDriver() {

		// Specify the process name to kill
		String processName = "gechodriver.exe";

		try {
			// Execute taskkill command to kill the process
			Process process = Runtime.getRuntime().exec("taskkill /F /IM " + processName);

			// Wait for the process to finish
			process.waitFor();

			// Check if the process exited successfully
			if (process.exitValue() == 0) {
				System.out.println("Successfully killed " + processName);
			} else {
				System.out.println("Failed to kill " + processName);
			}
		} catch (IOException | InterruptedException e) {
			// Handle exceptions
			e.printStackTrace();
		}
	}

	@Test(priority = 1)
	public void testChrome() throws InterruptedException {
		System.out.println("The thread ID for Chrome is " + Thread.currentThread().getId());
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.bstackdemo.com/");		
		Assert.assertEquals(driver.getTitle(), "StackDemo");
	}

	@Test(priority = 2)
	public void testFirefox() throws InterruptedException {
		System.out.println("The thread ID for Firefox is " + Thread.currentThread().getId());
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.bstackdemo.com/");		
		Assert.assertEquals(driver.getTitle(), "StackDemo");
	}

	@AfterMethod
	public void close() {
		
		driver.quit();
		

	}
}