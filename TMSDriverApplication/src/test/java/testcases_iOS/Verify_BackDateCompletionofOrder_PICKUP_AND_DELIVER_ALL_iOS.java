package testcases_iOS;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import applicationPages.BackDateOrderComplete_iOS;
import applicationPages.CompleteAll_iOS;
import applicationPages.Login_Page_iOS;
import applicationPages.SignOut_iOS;
import basepage.BasePage;

public class Verify_BackDateCompletionofOrder_PICKUP_AND_DELIVER_ALL_iOS extends BasePage {
	Login_Page_iOS login;
	BackDateOrderComplete_iOS BDOC;
	CompleteAll_iOS all;
	SignOut_iOS Out;

	@Test(priority = 1)
	public void setupApp() {
		setup();
//		AssertJUnit.assertEquals(false, null);
//		ExtentListeners.testReport.get().log(Status.INFO, "Application Setup, Launch SuccessFully");
		login = new Login_Page_iOS(driver);

	}

	@Test(priority = 2)
	public void GetAppLaunch() throws Exception

	{
		login.iOS_Username();
//		AssertJUnit.assertEquals(false, null);
//		ExtentListeners.testReport.get().log(Status.INFO, "Click on Username, Username Added");

	}
	@Test(priority = 3)
	public void Enter_Password() throws Exception

	{
		login.iOS_Password();
//		AssertJUnit.assertEquals(false, null);
//		ExtentListeners.testReport.get().log(Status.INFO, "Click on Password, Password Added");

	}
	@Test(priority = 4)
	public void Click_Submit() throws Exception

	{
		login.iOS_Submit();
//		AssertJUnit.assertEquals(false, null);
//		ExtentListeners.testReport.get().log(Status.INFO, "Click on Submit, Login Successful");

	}
//	@Test(priority = 5)
//	public void Click_SavePassword() throws Exception
//
//	{
//		login.Save_Password();
//		Assert.assertEquals(false, null);
//		ExtentListeners.testReport.get().log(Status.INFO, "Click on Save Password if available, Move to next");
//
//	}
	@Test(priority = 6)
	public void Click_Continue() throws Exception

	{
		login.Continue_iOS();
//		AssertJUnit.assertEquals(false, null);
//		ExtentListeners.testReport.get().log(Status.INFO, "Click on Continue, Welcome Inside TDA");
		BDOC=new BackDateOrderComplete_iOS(driver);

		}
		
		@Test(priority = 7)
		public void Calendar() throws Exception

		{
			BDOC.Today();
//			AssertJUnit.assertEquals(false, null);
//			ExtentListeners.testReport.get().log(Status.INFO, "Click on Today.");

		}
		@Test(priority = 8)
		public void Dateselector() throws Exception

		{
			BDOC.Clickondate();
//			AssertJUnit.assertEquals(false, null);
//			ExtentListeners.testReport.get().log(Status.INFO, "Click on Date.");
			all=new CompleteAll_iOS(driver);
		}
		@Test(priority = 9)
		public void CompletePickUp() throws Exception

		{
			all.Pick_Up_button_iOS();
//			AssertJUnit.assertEquals(false, null);
//			ExtentListeners.testReport.get().log(Status.INFO, "Pick done Successfully");
			

		}
		@Test(priority = 10)
		public void CompleteDelivery() throws Exception

		{
			all.Deliver_button_iOS();
//			AssertJUnit.assertEquals(false, null);
//			ExtentListeners.testReport.get().log(Status.INFO, "Delivery done Successfully");
			

		}
		@AfterClass
		public void SignOut() {
			Out = new SignOut_iOS(driver);
			Out.Sign_Out_iOS();
//			ExtentListeners.testReport.get().log(Status.INFO, "Signout Successfully");
		}
		
	
//	@AfterTest
//	public void quitDriver() {
//		quit();
//		ExtentListeners.testReport.get().log(Status.INFO, "Driver Quit Successfully");
//	
//	}

	
	
}
