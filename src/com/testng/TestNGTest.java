package com.testng;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGTest {
	
	
	//this java class file talks about understanding the Annotations
	
	@BeforeSuite
	public void beforeSuite()
	{
		System.out.println("Before suite");
	}
	
	@BeforeTest (alwaysRun = true)
	public void beforeTest()
	{
		System.out.println("Before test");
	}
	
	@BeforeClass
	public void beforeClass()
	{
		System.out.println("Before Class");
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod()
	{
		System.out.println("Before Method");
	}

	@Test(description = "depends",dependsOnMethods = "test3", alwaysRun = true ,groups={"sanity"})
	public void depedsMethod()
	{
		System.out.println("inside depends");
	}
	
	@Test (priority = 1)
	public void test1()
	{
		System.out.println("inside test1");
	}
	
	@Test(priority = 3,groups={"sanity"})
	public void test3()
	{
		System.out.println("inside test3");
		Assert.assertEquals(1, 2);
	}
	
	@Test(priority = 2)
	public void test2()
	{
		System.out.println("inside test2");
	}
	
	@AfterClass
	public void afterClass()
	{
		System.out.println("afterclass");
	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod()
	{
		System.out.println("afterMethod");
	}
	
	@AfterSuite
	public void aftersuite()
	{
		System.out.println("afterSuite");
	}
	
	@AfterTest(alwaysRun = true)
	public void afterTest()
	{
		System.out.println("aftertest");
	}
}
