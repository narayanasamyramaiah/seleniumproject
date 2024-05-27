package com.framework;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting {
	
	// Extent Reports
	
	ExtentSparkReporter spark;

    ExtentReports extent;
    //helps to generate the logs in the test report.
    ExtentTest test;

    @BeforeTest
    public void startReport() {
        // initialize the spark
       
    	 extent = new ExtentReports();
    	spark = new ExtentSparkReporter("./extentReports/Spark.html");
    	extent.attachReporter(spark);
    	
    	//spark = new ExtentSparkReporter(System.getProperty("user.dir") +"/extentReports/testReport.html");

        //initialize ExtentReports and attach the spark
       
       // extent.attachReporter(spark);
        


        //configuration items to change the look and feel
        //add content, manage tests etc
		
		/*
		 * spark.config().setOfflineMode(true);
		 * spark.config().setDocumentTitle("Simple Automation Report");
		 * spark.config().setReportName("Test Report");
		 * spark.config().setTheme(Theme.STANDARD);
		 * spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		 */
		 
    }

    @Test
    public void test_1() {
        test = extent.createTest("Test Case 1", "The test case 1 has passed");
        Assert.assertTrue(true);
    }


    @Test
    public void test_2() {
        test = extent.createTest("Test Case 2", "The test case 2 has failed");
        Assert.assertTrue(false);
    }

    @Test
    public void test_3() {
        test = extent.createTest("Test Case 3", "The test case 3 has been skipped");
        throw new SkipException("The test has been skipped");
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL,result.getThrowable());
            
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, result.getTestName());
        }
        else {
            test.log(Status.SKIP, result.getTestName());
        }
    }

    @AfterTest
    public void tearDown() {
        //to write or update test information to reporter
        extent.flush();
    }

}
