package com.selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class FrameHandling {
	WebDriver driver;

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
	@Ignore
	public void frameTest() {
		try {
			driver.get("https://www.globalsqa.com/demo-site/");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Frames']")));
			driver.findElement(By.xpath("//a[text()='Frames']")).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#iFrame")));
			driver.findElement(By.cssSelector("#iFrame")).click();
			driver.switchTo().frame("globalSqa");
			Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='GlobalSQA']")).isDisplayed());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.switchTo().defaultContent();
		}

	}

	@Test
	@Ignore
	public void siwtchToFrame_ID() {
		driver.get("https://demo.guru99.com/test/guru99home/");
		// navigates to the page consisting an iframe

		driver.manage().window().maximize();
		driver.switchTo().frame("a077aa5e"); // switching the frame by ID

		System.out.println("********We are switch to the iframe*******");
		driver.findElement(By.xpath("html/body/a/img")).click();
		// Clicks the iframe

		System.out.println("*********We are done***************");
	}

	@Test @Ignore
	public void indexFrame() {
		driver.get("https://demo.guru99.com/test/guru99home/");
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("total size of frames:" + size);
		for (int i = 0; i <= size; i++) {
			driver.switchTo().frame(i);
			int total = driver.findElements(By.xpath("html/body/a/img")).size();
			System.out.println(total);
			driver.switchTo().defaultContent();
		}
	}

	@Test
	public void nestedFrame() {
		driver.get("https://demoqa.com/nestedframes");
		WebElement pageHeadingElement = driver.findElement(By.xpath("//h1[@class='text-center']"));
		String pageHeading = pageHeadingElement.getText();
		System.out.println("Page Heading is :" + pageHeading);

		// Switch to Parent frame
		WebElement frame1 = driver.findElement(By.id("frame1"));
		driver.switchTo().frame(frame1);
		WebElement frame1Element = driver.findElement(By.tagName("body"));
		String frame1Text = frame1Element.getText();
		System.out.println("Frame1 is :" + frame1Text);

		// Switch to child frame
		driver.switchTo().frame(0);
		WebElement frame2Element = driver.findElement(By.tagName("p"));
		String frame2Text = frame2Element.getText();
		System.out.println("Frame2 is :" + frame2Text);

		// Switch to default content
		driver.switchTo().defaultContent();

		// Try to print the heading of the main page without swithcing
		WebElement mainPageText = driver.findElement(By.xpath("//*[@id='framesWrapper']/div[1]"));
		System.out.println(mainPageText.getText());
	}
	
	@Test
	public void withoutSwitchToDefaultContent()
	{
		driver.get("https://demoqa.com/nestedframes");
		WebElement pageHeadingElement = driver.findElement(By.xpath("//h1[@class='text-center']"));
		String pageHeading = pageHeadingElement.getText();
		System.out.println("Page Heading is :" + pageHeading);

		// Switch to Parent frame
		WebElement frame1 = driver.findElement(By.id("frame1"));
		driver.switchTo().frame(frame1);
		WebElement frame1Element = driver.findElement(By.tagName("body"));
		String frame1Text = frame1Element.getText();
		System.out.println("Frame1 is :" + frame1Text);

		// Switch to child frame
		driver.switchTo().frame(0);
		WebElement frame2Element = driver.findElement(By.tagName("p"));
		String frame2Text = frame2Element.getText();
		System.out.println("Frame2 is :" + frame2Text);

		try
		{
			// Try to print the heading of the main page without swithcing
			WebElement mainPageText = driver.findElement(By.xpath("//*[@id='framesWrapper']/div[1]"));
			System.out.println(mainPageText.getText());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
