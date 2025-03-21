package testcases_Android;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import applicationPages.LoginPage_Android;
import basepage.BasePage;
import extentlisteners.TestListeners;
import io.appium.java_client.AppiumDriver;

import propertyutility.PropertyUtility;
import utilities.appiumServerManager;


public class Verify_UserLogin_Android extends BasePage {
	
// Creating Object
	LoginPage_Android login;
//	@BeforeSuite
//	public void AppiumStart() {
//		appiumServerManager.startAppiumServer();
//	}




	@Test(priority = 1)
	public void setupApp() {
		setup();
//		TestListeners.testReport.get().log(Status.INFO, "Application Setup, Launch SuccessFully");
		login = new LoginPage_Android(driver);


	}

	@Test(priority = 2)
	public void GetAppLaunch() throws Exception

	{
		Thread.sleep(10000);
		login.Android_credential();
//		TestListeners.testReport.get().log(Status.INFO, "Compare OS, Enter Credentials");

	}
	
	@Test(priority = 3)
	public void Clickoncontinue() throws Exception

	{
		login.Continue_button();
//		TestListeners.testReport.get().log(Status.INFO, "Click on Continue after login.");

	}
	@Test(priority = 4)
	public void LocationAllowradiobutton() throws Exception

	{
		login.Location_Allow_button();
//		TestListeners.testReport.get().log(Status.INFO, "Location Allow Radio button clicked.");

	}
	@Test(priority = 5)
	public void ClickonAlwaysallowlocation() throws Exception

	{
		login.Allow_always_button();
//		TestListeners.testReport.get().log(Status.INFO, "Allow always Location.");

	}
	@Test(priority = 6)
	public void Clickonback() throws Exception

	{
		login.Back_button();
//		TestListeners.testReport.get().log(Status.INFO, "Click on back.");

	}
	
	@AfterTest
	public void quitDriver() {
//		quit();
//		TestListeners.testReport.get().log(Status.INFO, "Driver Quit Successfully");
	
	}

}
