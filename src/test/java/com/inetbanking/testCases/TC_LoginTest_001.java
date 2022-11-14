package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

import jdk.internal.org.jline.utils.Log;


public class TC_LoginTest_001 extends BaseClass{

	@Test
	public void loginTest() throws IOException, InterruptedException {
		logger.info("URL is opened");
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(username);
		logger.info("Entered username");
		lp.setPassword(password);
		logger.info("Entered password");
		lp.clickSubmit();
//		Assert.assertEquals("Guru99 Bank Manager HomePage", driver.getTitle());
		
		
		if(driver.getTitle().equalsIgnoreCase("Guru99 Bank Manager HomePage")) {
			logger.info(driver.getTitle());
			Assert.assertTrue(true);
			logger.info("Testcase passed");
		}else {
			logger.info("Testcase failed");
			takeScreenshot(driver, "logintest");
			Assert.assertTrue(false);
		}
		
	}
	
}
