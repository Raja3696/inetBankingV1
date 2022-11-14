package com.inetbanking.testCases;


import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

public class BaseClass {

	ReadConfig readConfig = new ReadConfig();
	public String baseURL = readConfig.getApplicationURL();
	public String username =readConfig.getUsername();
	public String password =readConfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {
		
		logger = Logger.getLogger("BaseClass");
		PropertyConfigurator.configure("log4j.properties");
		
		if(br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",readConfig.getChromepath());
			driver = new ChromeDriver();	
		}
		if(br.equals("ie")) {
			System.setProperty("webdriver.ie.driver",readConfig.getIepath());
			driver = new InternetExplorerDriver();	
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(baseURL);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	public void takeScreenshot(WebDriver driver, String tname) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		FileUtils.copyFile(srcFile, destFile);
		System.out.println("Screenshot Taken!");
	}
	
}
