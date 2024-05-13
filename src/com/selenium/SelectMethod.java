package com.selenium;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectMethod {
	
	public static void ScrollIntoViewAndClick(WebDriver driver,WebElement element)
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}
	
	public static List<WebElement> listElement(WebDriver driver,String xpath)
	{
		
		return driver.findElements(By.xpath(xpath));
	}
	
	public static void main(String[] args) {
		WebDriver driver=null;
		try {
			String baseURL = "https://demo.guru99.com/test/newtours/register.php";				
			driver	 = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
					driver.get(baseURL); //-> launch the url thats is being inputted as soon as the webdriver being instantiated
					driver.navigate().to("https://www.google.com/"); //-> ur own url input to navigate
					driver.navigate().refresh(); // -> refresh the browser
					driver.navigate().back(); // navigating back to the earlier browser page(1 page earlier)

					WebElement element = driver.findElement(By.name("country"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
					
					Select drpCountry = new Select(element);
					
					List<WebElement> ele=drpCountry.getOptions();
					System.out.println("total Country listed:"+ele.size());
					
					for (int i = 0; i < ele.size(); i++) {
						System.out.println(ele.get(i).getText());
					}
					
					drpCountry.selectByVisibleText("ANTARCTICA");
					drpCountry.selectByValue("INDIA");
					drpCountry.selectByIndex(15);
				
					//Thread.sleep(3000);
					//Selecting Items in a Multiple SELECT elements
					driver.get("http://jsbin.com/osebed/2");
					Select fruits = new Select(driver.findElement(By.id("fruits")));
					fruits.selectByVisibleText("Banana");
					fruits.selectByIndex(2);
					
					
					//3rd Example For multi Select and deselect without Select
					
					driver.navigate().to("https://semantic-ui.com/modules/dropdown.html#multiple-search-selection");
					WebElement element1 = driver.findElement(By.xpath("//div[@class='ui fluid dropdown selection multiple']"));
					ScrollIntoViewAndClick(driver,element1);
					element1.click();
					
					List<WebElement> totalSkills=listElement(driver, "//select[@name='skills']/following-sibling::div[@class='menu transition visible']/div");
					
					for (int i = 0; i < totalSkills.size(); i++) {
						System.out.println("Skill "+i+":"+totalSkills.get(i).getText());
					}
					
					ScrollIntoViewAndClick(driver, driver.findElement(By.xpath("//select[@name='skills']/following-sibling::div/div[@data-value='python']")));
					
				
					ScrollIntoViewAndClick(driver, driver.findElement(By.xpath("//select[@name='skills']/following-sibling::div/div[@data-value='node']")));
				
					ScrollIntoViewAndClick(driver, driver.findElement(By.xpath("//select[@name='skills']/following-sibling::div/div[@data-value='ruby']")));
				//	multi.selectByValue("ruby");
				//	multi.deselectAll();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			driver.close();
			driver.quit();
		}
		
	}

}
