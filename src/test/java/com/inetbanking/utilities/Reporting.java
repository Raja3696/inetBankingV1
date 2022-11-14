package com.inetbanking.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.inetbanking.pageObjects.LoginPage;

public class Reporting extends TestListenerAdapter {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	public static Logger log = Logger.getLogger("Reporting");
	

	public void onStart(ITestContext testContext) {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp
		String repName = "Test-Report - " + timestamp + ".html";
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/" + repName);
//		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Raja");

		htmlReporter.config().setDocumentTitle("InetBanking Test Project"); // Title of project
		htmlReporter.config().setReportName("Functional Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
	}

	public void onTestSuccess(ITestResult tr) {
		PropertyConfigurator.configure("log4j.properties");
		log.info("Entered success method");
		logger = extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); // Send the passed info
	}

	public void onTestFailure(ITestResult tr) {
		PropertyConfigurator.configure("log4j.properties");
		log.info("Entered failure method");
		logger = extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED)); // Send the passed info

		String screenShotPath = System.getProperty("user.dir") + "/Screenshots/" + tr.getName() + ".png";

		File f = new File(screenShotPath);
		log.info(f);

		if (f.exists()) {
			try {
				log.info("Taking screenshot");
				logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenShotPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void onTestSkipped(ITestResult tr) {
		logger = extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.LIME)); // Send the passed info
	}
	public void onFinish(ITestContext testcontext) {
		extent.flush();
	}
	
}
