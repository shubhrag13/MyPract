package practiceTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportChrome {
	
	ExtentReports report;
	ExtentTest test;
	String title;
	WebDriver driver;
	String projDir;
	
	@BeforeMethod
	public void startUp() {
		projDir = System.getProperty("user.dir");
		Reporter.log(projDir, true);
//		report = new ExtentReports("E:\\Eclipse-Workspace\\mavenDemo\\ExtentReportsTest.HTML", false);
		report = new ExtentReports(projDir + "\\ExtentReportsTest.HTML", false);
//		report = new ExtentReports("E:\\gitRepo\\mavenDemo\\ExtentReportsTest.HTML", false);
		test = report.startTest("ExtentReportChrome");
	}
	
	@Test
	public void login() throws Exception {
		
//		System.setProperty("webdriver.chrome.driver", "E:\\Eclipse-Workspace\\Lib\\chromedriver.exe");
//		System.setProperty("webdriver.chrome.driver", "E:\\gitRepo\\mavenDemo\\Lib\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", projDir + "\\Lib\\chromedriver.exe");
		
		driver = new ChromeDriver();
		test.log(LogStatus.INFO, "Chrome is Launched");
		
		driver.get("https://www.google.com");
		Thread.sleep(2000);
		driver.findElement(By.name("q")).sendKeys("Reporter Class TestNG");
		test.log(LogStatus.INFO, "Search String Entered");
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		test.log(LogStatus.INFO, "Search Performed");
		
		Thread.sleep(5000);
		
		title = driver.getTitle();
		test.log(LogStatus.INFO, title);
		if(title.contains("Reporter")) {
			test.log(LogStatus.PASS, "Search Results are displayed");
		}else
			test.log(LogStatus.FAIL, "An issue occurred while performing search");
	}
	
	@AfterMethod
	public void end() {
		report.endTest(test);
		report.flush();
		driver.close();
	}

}
