package com.inetbanking.testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_AddCustomer_003 extends BaseClass {

	@Test
	public void AddnewCustomer() throws InterruptedException {
		logger.info("URL is opened");
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(username);
		logger.info("Entered username");
		lp.setPassword(password);
		logger.info("Entered password");
		lp.clickSubmit();
		Thread.sleep(3000);
		logger.info("Add new customer");
		driver.findElement(By.linkText("New Customer")).click();
		WebElement element = driver.findElement(By.xpath("//iframe[contains(@id,'google_ads_iframe')]"));
		driver.switchTo().frame(element);
		driver.findElement(By.xpath("//*[@id=\"dismiss-button\"]/div")).click();
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		Thread.sleep(4000);
		AddCustomerPage cpage = new AddCustomerPage(driver);
		Thread.sleep(2000);
		logger.info("starting form");
		Thread.sleep(5000);
		cpage.addCust();
		Thread.sleep(1000);
		cpage.custName("Maddy");
		logger.info("Added name");
		cpage.custgender();
		cpage.custdob("12", "02", "1993");
		Thread.sleep(1000);
		cpage.custaddress("Pragathi nagar, kphb");
		cpage.custcity("Hyderbad");
		cpage.custstate("Telangana");
		cpage.custpinno("998877");
		logger.info("detaisl");
		cpage.custtelephoneno("9988998877");
		String email = randomString() + "@gmail.com";
		cpage.custemailid(email);
		cpage.custpassword("password");
		cpage.submit();
		Thread.sleep(1000);

		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
		Assert.assertTrue(true);

	}

	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
}
