package testcases_Android;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import applicationPages.BackDateOrderComplete_Android;
import applicationPages.CompleteAll_Android;
import applicationPages.LoginPage_Android;
import basepage.BasePage;
import extentlisteners.TestListeners;


public class Verify_BackDateCompletionofOrder_PICKUP_AND_DELIVER_ALL_Android extends BasePage{
	LoginPage_Android login;
	CompleteAll_Android all;
	BackDateOrderComplete_Android BDOC;

	@Test(priority = 1)
	public void setupApp() {
		setup();
		TestListeners.testReport.get().log(Status.INFO, "Application Setup, Launch SuccessFully");
		login = new LoginPage_Android(driver);


	}
	@Test(priority = 2)
	public void GetAppLaunch() throws Exception

	{
		login.Android_credential();
		TestListeners.testReport.get().log(Status.INFO, "Compare OS, Enter Credentials");

	}
	
	@Test(priority = 3)
	public void Clickoncontinue() throws Exception

	{
		login.Continue_button();
		TestListeners.testReport.get().log(Status.INFO, "Click on Continue after login.");

	}
	@Test(priority = 4)
	public void LocationAllowradiobutton() throws Exception

	{
		login.Location_Allow_button();
		TestListeners.testReport.get().log(Status.INFO, "Location Allow Radio button clicked.");

	}
	@Test(priority = 5)
	public void ClickonAlwaysallowlocation() throws Exception

	{
		login.Allow_always_button();
		TestListeners.testReport.get().log(Status.INFO, "Allow always Location.");

	}
	@Test(priority = 6)
	public void Clickonback() throws Exception

	{
		login.Back_button();
		TestListeners.testReport.get().log(Status.INFO, "Click on back.");
		BDOC=new BackDateOrderComplete_Android();

	}
	
	@Test(priority = 7)
	public void Calendar() throws Exception

	{
		BDOC.Today();
		TestListeners.testReport.get().log(Status.INFO, "Click on Today.");

	}
	@Test(priority = 8)
	public void Dateselector() throws Exception

	{
		BDOC.Clickondate();
		TestListeners.testReport.get().log(Status.INFO, "Click on Date.");
		all=new CompleteAll_Android(driver);
	}
	

		

		@Test(priority = 9)
		public void CompletePickUp() throws Exception {

			all.AndroidPick_Up_button();
			TestListeners.testReport.get().log(Status.INFO, "Order Pickup successfully");
		}
		@Test(priority = 10)
		public void CompleteDelivery() throws Exception {
			all.AndroidDeliver_button();;
			TestListeners.testReport.get().log(Status.INFO, "Order Delivered successfully");
		}
		
	
	
	@AfterTest
	public void quitDriver() {
		driver.quit();
		TestListeners.testReport.get().log(Status.INFO, "Driver Quit Successfully");
	
	}
	

}
