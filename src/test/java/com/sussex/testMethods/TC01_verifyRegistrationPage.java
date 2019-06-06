package com.sussex.testMethods;
import static org.junit.Assert.assertTrue;
import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.sussex.pageObjects.HomePage;
import com.sussex.pageObjects.LoginPage;
import com.sussex.pageObjects.RegistrationPage;
import com.sussex.testBase.TestBase;

/**
 * Test Script for Home Page Controls Verification
 * @author Pankaj Kumar
 *
 */
public class TC01_verifyRegistrationPage extends TestBase {
	
	RegistrationPage registrationPage;
	HomePage homePage;
	LoginPage loginPage;
//	SerpPage serpPage;
	public TC01_verifyRegistrationPage() {
		
		super();
	}
	
	
	@DataProvider(name = "BoardOptions")
	 public Object[][] BoardOptionss() {
	  Object[][] data = null;
	  try {
	   data = getExcelData(testData, "Filteroptions", "startboardoptions", "endboardoptions");
	  } catch (Exception e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	  return data;
	 }
	
	@BeforeSuite
	public void setUp() throws InterruptedException {
		try {
			// Creating an instance for Browser, Navigating to Url & Maximize the browser
			init();
			// Start Reporting - Extent Report
			report = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReports.html");
			test = report.startTest("Home Page Controls verification");
			registrationPage = new RegistrationPage(driver);
			homePage = new HomePage(driver);
			loginPage = new LoginPage(driver);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	@Test(enabled = true, dataProvider = "Register")
	public void TS01_verifyStudentAbleToRegisterToWithSussexUniversity(String familyName,String givenName
,String dateOFBirth
,String gender
,String emailId
,String nationality
,String password
, String conPassword, String runMode) {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		 try {
			 
			  registrationPage.EnterFamilyName(familyName);
			  registrationPage.EnterGivenName(givenName);
			  Thread.sleep(2000);
			  registrationPage.EnterDateOfBirth(dateOFBirth);
			  Thread.sleep(4000);
			  registrationPage.enterGender(gender);
			  Thread.sleep(2000);
			 registrationPage.enterEmailId(emailId);
			 Thread.sleep(2000);
			 registrationPage.nationality(nationality);
			 Thread.sleep(2000);
			 registrationPage.password(password);
			 Thread.sleep(2000);
			 registrationPage.conPassword(conPassword);
			 Thread.sleep(2000);
			 registrationPage.previouslyAppliedcheckBox();
			 Thread.sleep(2000);
			 registrationPage.termsAndConditionCheckBox();
			 Thread.sleep(2000);
			 registrationPage.clickOnRegisterButton();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	}
	
	
	@AfterSuite
	public void closeBrowser() {
		try {
			Thread.sleep(1000);
			// driver.close();
			report.endTest(test);
			report.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
