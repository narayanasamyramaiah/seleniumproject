package com.accesibility;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.deque.axe.AXE;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AccessibleTest {
	WebDriver driver;
	public static final URL scriptUrl = AccessibleTest.class.getResource("/axe.min.js");

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-browser-side-navigation");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		//driver.get("https://www.selenium.dev/");
		driver.get("https://www.apple.com/in/accessibility/");
		driver.manage().window().maximize();
	}

	@Test
	public void validateAccessibility(Method method) throws InterruptedException {
		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();
		JSONArray violations = responseJSON.getJSONArray("violations");
		if (violations.length() == 0) {
			Assert.assertTrue(true, "No violations found");
		} else {
			AXE.writeResults("violations/"+method.getName(), responseJSON);
			Reporter.log("# AXE.report(violations)", true);
			Assert.fail(AXE.report(violations));
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void amazonAllyTest() throws NumberFormatException, InterruptedException {
		String URL = "https://www.amazon.com/";
		driver.get(URL);
			Thread.sleep(1000);
			JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();
			JSONArray violations = responseJSON.getJSONArray("violations");
			if (violations.length() == 0) {
				System.out.println("No violations found");
			}else {
				AXE.writeResults("violations/AmazonAllyTest", responseJSON);
				Assert.assertTrue(false, AXE.report(violations));
			}
		
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
