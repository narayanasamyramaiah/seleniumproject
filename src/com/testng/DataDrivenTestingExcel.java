package com.testng;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.utils.ExcelUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataDrivenTestingExcel {
	ExcelUtils excelUtils = new ExcelUtils();
	WebDriver driver;

	@BeforeClass
	public void LunchDriver() {
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

	@Test
	public void RegistrationTestWithExcelData() throws IOException {
		driver.get("https://demoqa.com/automation-practice-form");

		// Identify the WebElements for the student registration form
		WebElement firstName = driver.findElement(By.id("firstName"));
		WebElement lastName = driver.findElement(By.id("lastName"));
		WebElement email = driver.findElement(By.id("userEmail"));
		WebElement genderMale = driver.findElement(By.id("gender-radio-1"));
		WebElement mobile = driver.findElement(By.id("userNumber"));
		WebElement address = driver.findElement(By.id("currentAddress"));
		WebElement submitBtn = driver.findElement(By.id("submit"));

		// calling the ExcelUtils class method to initialise the workbook and sheet
		excelUtils.setExcelFile("./testda/Testdata.xlsx", "STUDENT_DATA");

		// iterate over all the row to print the data present in each cell.
		for (int i = 1; i <= excelUtils.getRowCountInSheet(); i++) {
			// Enter the values read from Excel in firstname,lastname,mobile,email,address
			firstName.sendKeys(excelUtils.getCellData(i, 0));
			lastName.sendKeys(excelUtils.getCellData(i, 1));
			email.sendKeys(excelUtils.getCellData(i, 2));
			mobile.sendKeys(excelUtils.getCellData(i, 3));
			address.sendKeys(excelUtils.getCellData(i, 4));

			// Click on the gender radio button using javascript
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true)", genderMale);
			js.executeScript("arguments[0].click();", genderMale);

			// Click on submit button
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].scrollIntoView(true)", submitBtn);
			js1.executeScript("arguments[0].click();", submitBtn);			
			//submitBtn.click();

			// Verify the confirmation message
			WebElement confirmationMessage = driver
					.findElement(By.xpath("//div[text()='Thanks for submitting the form']"));

			// check if confirmation message is displayed
			/*
			 * if (confirmationMessage.isDisplayed()) { // if the message is displayed ,
			 * write PASS in the excel sheet using the method // of ExcelUtils
			 * excelUtils.setCellValue(i, 8, "PASS", "./testda/Testdata.xlsx"); } else { //
			 * if the message is not displayed , write FAIL in the excel sheet using the //
			 * method of ExcelUtils excelUtils.setCellValue(i, 8, "FAIL",
			 * "./testda/Testdata.xlsx"); }
			 */

			// close the confirmation popup
			WebElement closebtn = driver.findElement(By.id("closeLargeModal"));
			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			js2.executeScript("arguments[0].scrollIntoView(true)", closebtn);
			js2.executeScript("arguments[0].click();", closebtn);			
		//	closebtn.click();

		}
	}
}
