package com.selenium;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class FileUpload {
	WebDriver driver;

	@BeforeMethod
	public void driverinstance() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	}

	@AfterMethod
	public void closedriver() {
		// driver.close();
		driver.quit();
	}

	@Test @Ignore
	public void execution() throws InterruptedException, AWTException {

		driver.get("https://www.facebook.com/");

		// Enter username
		driver.findElement(By.name("email")).sendKeys("Selenium123@gmail.com");

		// create object of Robot class
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_SHIFT);

		// Enter password
		driver.findElement(By.name("pass")).sendKeys("Selenium123");

		// Hit Enter of keyboard using Robot Class
		r.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
		// Release Enter
		r.keyRelease(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_SHIFT);
		r = null;

	}

	@Test @Ignore
	public void FileUploadRobot() throws AWTException {

		try {
			driver.get("https://www.sejda.com/word-to-pdf");

			// Locating upload filebutton
			WebElement upload = driver.findElement(By.xpath("//div[@class='btn-group upload-btn-group main']"));
			upload.click();
			// upload.sendKeys("C:\\Users\\DELL\\Documents\\Daily Work Tracker.docx");
			// driver.close();

			StringSelection strSelection = new StringSelection("C:\\Users\\DELL\\Documents\\Daily Work Tracker.docx");
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(strSelection, null);
			Robot robot = new Robot();
			robot.delay(300);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(200);
			robot.keyRelease(KeyEvent.VK_ENTER);

			Assert.assertTrue(driver.findElement(By.cssSelector("#download-dropdown a[download]")).isEnabled());
			Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Your document is ready']")).isDisplayed());
		} catch (Exception e) {
			// TODO: handle exception
			if (driver.findElement(By.cssSelector("#tasks-per-hour-reason")).isDisplayed()) {
				System.out.println(driver.findElement(By.cssSelector("#tasks-per-hour-reason")).getText());
			}
			{

			}
			System.out.println("sjeda can be used max 3 times for convertion , so wait for 1 hr");
		}

	}

	@Test
	public void FileUploadWithAutoIT() throws AWTException {

		try {
			driver.get("https://www.sejda.com/word-to-pdf");

			// Locating upload filebutton
			WebElement upload = driver.findElement(By.xpath("//div[@class='btn-group upload-btn-group main']"));
			upload.click();
			Thread.sleep(5000);
			Runtime.getRuntime().exec("./test.exe");
			Thread.sleep(5000);
			// Assert.assertTrue(driver.findElement(By.cssSelector("#download-dropdown
			// a[download]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Your document is ready']")).isDisplayed());
		} catch (Exception e) {
			// TODO: handle exception
			if (driver.findElement(By.cssSelector("#tasks-per-hour-reason")).isDisplayed()) {
				System.out.println(driver.findElement(By.cssSelector("#tasks-per-hour-reason")).getText());
			}
			{

			}
			System.out.println("sjeda can be used max 3 times for convertion , so wait for 1 hr");
		}

	}
}
