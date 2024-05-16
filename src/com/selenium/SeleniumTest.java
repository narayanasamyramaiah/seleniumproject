package com.selenium;

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.*;


public class SeleniumTest {
	

	public static void main(String[] args) {
		WebDriver driver = null;
		String SubSections="//nav[@class='navPages p-0 m-0 ']//li[starts-with(@class,'navPages-item')]/a[contains(@class,'navPages-action')]";
		try {
			System.out.println("Testing Starts here");
			//System.setProperty("webdriver.http.factory", "jdk-http-client");
			System.setProperty("webdriver.chrome.driver", "C:\\seleniumdrivers\\chromedriver.exe");
			
			driver= new ChromeDriver();  // Instantiation of the Selenium Webdriver for Chrome
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get("https://qa.sleepcountry.ca/");
			System.out.println("my browser page title:"+driver.getTitle());
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#guestTkn")).sendKeys("8zkxxuq251");
			driver.findElement(By.xpath("//input[@value='Submit']")).click();
			Assert.assertTrue(driver.findElement(By.xpath("(//a[@href and @class='header-logo__link'])[1]")).isDisplayed());
			System.out.println("print the logo end point:"+driver.findElement(By.xpath("(//a[@href and @class='header-logo__link'])[1]")).getAttribute("host"));
		//getAttribute is to fetch an attribute from inside DOM -> $x("xpath") from console
			
			
			List<WebElement> elements=
					driver.findElements(By.xpath(SubSections));
			System.out.println("Total section size:"+elements.size());
			for(int i=0;i<elements.size();i++)
			{
				System.out.println("Before validated index:"+i);
				if(elements.get(i).isEnabled()&& elements.get(i).isDisplayed())
				{
					System.out.println("validated index:"+i);
					System.out.println(elements.get(i).getText()); // getText() is used to fetch the inside text of ur html
				}
				
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}finally {
			driver.close();
			driver.quit();
			
		}
		
	}
	

}
