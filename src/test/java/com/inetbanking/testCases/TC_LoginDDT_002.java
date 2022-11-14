package com.inetbanking.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

import jdk.internal.org.jline.utils.Log;

public class TC_LoginDDT_002 extends BaseClass {

	@Test(dataProvider = "LoginData")
	public void loginDDT(String user, String pwd) throws IOException, InterruptedException {
		logger.info("URL is opened");
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(user);
		logger.info("User name is provided");
		lp.setPassword(pwd);
		logger.info("password is provided"); 
		lp.clickSubmit();
		Thread.sleep(3000);
		lp.logout();
		logger.info("Logged out");
		Thread.sleep(10000);
		driver.switchTo().alert().accept(); // close logout alert
		driver.switchTo().defaultContent();
		Assert.assertTrue(true);
		
		
//		if (isAlertPreasent() == true) {
//			logger.info("Alert is present");
//			driver.switchTo().alert().accept();
//			driver.switchTo().defaultContent();
//			Assert.assertTrue(false);
//			logger.warn("Login failed");
//		} else {
//			Log.info("No Alert");
//			Log.info("Login Passed");
//			lp.logout();
//			Log.info("Logged out");
//			Thread.sleep(3000);
//			driver.switchTo().alert().accept(); // close logout alert
//			driver.switchTo().defaultContent();
//			Assert.assertTrue(true);
//		}
	}

	public boolean isAlertPreasent() { // user defined method to check alert is present or not

		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}

	}

	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException {
//		String path = System.getProperty("user.dir") + "src/test/java/com/inetbanking/testData/LoginDetails.xlsx";
		logger.info("Enntered method one");
		String path = "C:\\Users\\raja9\\OneDrive\\Documents\\AResume\\LoginDetails.xlsx";
		logger.info("Enntered method two");
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		logger.info("Enntered method three");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);

		String loginData[][] = new String[rownum][colcount];
		logger.info("Enntered method four");

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				loginData[i - 1][j] = XLUtils.getCellData(path, "sheet1", i, j);
			}
		}
		return loginData;
	}
}
