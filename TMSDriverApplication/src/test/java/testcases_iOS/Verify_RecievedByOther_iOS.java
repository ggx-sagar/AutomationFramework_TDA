package testcases_iOS;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import applicationPages.Login_Page_iOS;
import applicationPages.RecievedByOther_iOS;
import applicationPages.SignOut_iOS;
import basepage.BasePage;




public class Verify_RecievedByOther_iOS extends BasePage {
	
	Login_Page_iOS login;

	RecievedByOther_iOS other;
	SignOut_iOS Out;
	
	
	@Test(priority = 1)
	public void setupApp() {
		setup();
//		ExtentListeners.testReport.get().log(Status.INFO, "Application Setup, Launch SuccessFully");
		login = new Login_Page_iOS(driver);
//		AssertJUnit.assertEquals(false, null);

	}

	@Test(priority = 2)
	public void GetAppLaunch() throws Exception

	{
		login.iOS_Username();
//		ExtentListeners.testReport.get().log(Status.INFO, "Click on Username, Username Added");
//		Assert.assertEquals(false, null);

	}
	@Test(priority = 3)
	public void Enter_Password() throws Exception

	{
		login.iOS_Password();
//		ExtentListeners.testReport.get().log(Status.INFO, "Click on Password, Password Added");
//		Assert.assertEquals(false, null);
	}
	@Test(priority = 4)
	public void Click_Submit() throws Exception

	{
		login.iOS_Submit();
//		ExtentListeners.testReport.get().log(Status.INFO, "Click on Submit, Login Successful");
//		Assert.assertEquals(false, null);

	}
//	@Test(priority = 5)
//	public void Click_SavePassword() throws Exception
//
//	{
//		login.Save_Password();
//    	ExtentListeners.testReport.get().log(Status.INFO, "Click on Save Password if available, Move to next");
//		Assert.assertEquals(false, null);
//
//	}
	@Test(priority = 6)
	public void Click_Continue() throws Exception

	{
		login.Continue_iOS();
//		ExtentListeners.testReport.get().log(Status.INFO, "Click on Continue, Welcome Inside TDA");
//		Assert.assertEquals(false, null);
		other=new RecievedByOther_iOS(driver);
		

	}
	
	@Test(priority = 7)
	public void Recieved_Test() throws Exception

	{
		other.PickUpTask();
//		Assert.assertEquals(false, null);
//		ExtentListeners.testReport.get().log(Status.INFO, "Task Selected, Pickup Start");

	}
	@Test(priority = 8)
	public void ClickonOrdernumber() throws Exception

	{
		other.PickedDetails();
//		Assert.assertEquals(false, null);
//		ExtentListeners.testReport.get().log(Status.INFO, "Select Order Number to go to detail page, order Detail page shown");

	}
	@Test(priority = 9)
	public void Delivertaskselection() throws Exception

	{
		other.Delivertask();
//		Assert.assertEquals(false, null);
//		ExtentListeners.testReport.get().log(Status.INFO, "Delivery Task Selected, Enter Delivery detail page");
	}
	@Test(priority = 10)
	public void Deliverydone() throws Exception

	{
		other.DeliveryDetails();
//		Assert.assertEquals(false, null);
//		ExtentListeners.testReport.get().log(Status.INFO, "Click Revcieved by other, Delivered");

	}
	@AfterClass
	public void SignOut() {
		Out = new SignOut_iOS(driver);
		Out.Sign_Out_iOS();
//		ExtentListeners.testReport.get().log(Status.INFO, "Signout Successfully");
	}
//	@AfterTest
//	public void quitDriver() {
//		quit();
//		ExtentListeners.testReport.get().log(Status.INFO, "Driver Quit Successfully");
//	
//	}
}
