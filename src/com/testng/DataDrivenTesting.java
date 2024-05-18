package com.testng;



import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.commons.compress.archivers.ar.ArArchiveEntry;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.utils.ExcelUtils;

import io.github.bonigarcia.wdm.WebDriverManager;


public class DataDrivenTesting {
	
	ExcelUtils excel=new ExcelUtils();
	WebDriver driver;	
	@BeforeClass
	public void LunchDriver()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
	}
	@AfterClass
	public void closedriver() {
		// driver.close();
		driver.quit();
	}
	@Test(dataProvider = "LoginData",dataProviderClass = DataDrivenTesting.class) @Ignore
	public void dataDrivenTest(String username,String password)
	{
		
			try
			{
				driver.get("https://practicetestautomation.com/practice-test-login/");
				driver.findElement(By.cssSelector("#username")).sendKeys(username);
				driver.findElement(By.cssSelector("#password")).sendKeys(password);
				driver.findElement(By.cssSelector("#submit")).click();
				Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Logged In Successfully']")).isDisplayed());
				System.out.println("PASS, Successful Login happened for user:"+username);
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("FAIL, credentials is not correct for user:"+username);
			}
				
	}

	@DataProvider(name="LoginData")
	public Object[][] TestDataFeed(){
		Object [] [] userdata= new Object [3][2];
		userdata[0][0]="abc";
		userdata[0][1]="password";
		userdata[1][0]="xyz";
		userdata[1][1]="password";
		userdata[2][0]="student";
		userdata[2][1]="Password123";
		return userdata;
	
	}
	@Test 
	public void DataDrivenTestWithExcelRead() throws IOException
	{
		excel.setExcelFile("./testda/Testdata.xlsx","sheet1");
		int rowcount=excel.getRowCountInSheet();
		int column_count=2;
		
		String[][] credentials=new String[rowcount][column_count];
		
		for (int Row = 1; Row <= rowcount; Row++) {
			for (int col = 0; col < column_count; col++) {
				//System.out.println(excel.getCellData(i, j));
				credentials[Row-1][col]=excel.getCellData(Row, col); //Loading the data into a String array
			}
			
		}
		excel.killInstance();
		for (int i = 0; i < rowcount; i++) {
            for (int j = 0; j < column_count; j++) {
                System.out.print(credentials[i][j] + " ");
            }
            System.out.println(); // Move to the next line after printing each row
        }	
		
		String username=credentials[0][0];
		String password=credentials[0][1];
		try
		{
			driver.get("https://practicetestautomation.com/practice-test-login/");
			driver.findElement(By.cssSelector("#username")).sendKeys(username);
			driver.findElement(By.cssSelector("#password")).sendKeys(password);
			driver.findElement(By.cssSelector("#submit")).click();
			Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Logged In Successfully']")).isDisplayed());
			System.out.println("PASS, Successful Login happened for user:"+username);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("FAIL, credentials is not correct for user:"+username);
		}
		 username=credentials[3][0];
		 password=credentials[3][1];
		try
		{
			driver.get("https://practicetestautomation.com/practice-test-login/");
			driver.findElement(By.cssSelector("#username")).sendKeys(username);
			driver.findElement(By.cssSelector("#password")).sendKeys(password);
			driver.findElement(By.cssSelector("#submit")).click();
			Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Logged In Successfully']")).isDisplayed());
			System.out.println("PASS, Successful Login happened for user:"+username);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("FAIL, credentials is not correct for user:"+username);
		}
	}
}
