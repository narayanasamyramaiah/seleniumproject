package com.selenium;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AlertsHandling {
	
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
	public void alertAcceptTest() throws InterruptedException
	{
		
		driver.get("https://www.hyrtutorials.com/p/alertsdemo.html");
		driver.findElement(By.cssSelector("#promptBox")).click();
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		Alert alert= wait.until(ExpectedConditions.alertIsPresent());		
		Thread.sleep(2000);
		String s = Keys.chord(Keys.CONTROL, "a")+Keys.chord(Keys.DELETE);	
		alert.sendKeys(s);
		Thread.sleep(2000);
		driver.switchTo().alert().sendKeys("Selenium Test");
		System.out.println(alert.getText());
		alert.accept();
		
	}
	
	@Test
	public void alertRejectTest() throws InterruptedException
	{
		
		driver.get("https://www.hyrtutorials.com/p/alertsdemo.html");
		driver.findElement(By.cssSelector("#confirmBox")).click();
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		Alert alert= wait.until(ExpectedConditions.alertIsPresent());
		System.out.println(alert.getText());
		Thread.sleep(2000);
		alert.dismiss();
		
	}

}
