package testcases_iOS;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import applicationPages.Login_Page_iOS;
import applicationPages.OrderDetail_iOS;
import applicationPages.SignOut_iOS;
import basepage.BasePage;


public class Verify_CompletionofOrder_from_OrderDetails_iOS extends BasePage {
	Login_Page_iOS login;
	OrderDetail_iOS Detail;
	SignOut_iOS Out;

	@Test(priority = 1)
	public void setupApp() {
		setup();
//		ExtentListeners.testReport.get().log(Status.INFO, "Application Setup, Launch SuccessFully");

		login = new Login_Page_iOS(driver);

	}

	@Test(priority = 2)
	public void GetAppLaunch() throws Exception

	{
		login.iOS_Username();

//		ExtentListeners.testReport.get().log(Status.INFO, "Click on Username, Username Added");

	}
	@Test(priority = 3)
	public void Enter_Password() throws Exception

	{
		login.iOS_Password();

//		ExtentListeners.testReport.get().log(Status.INFO, "Click on Password, Password Added");

	}
	@Test(priority = 4)
	public void Click_Submit() throws Exception

	{
		login.iOS_Submit();

//		ExtentListeners.testReport.get().log(Status.INFO, "Click on Submit, Login Successful");

	}
//	@Test(priority = 5)
//	public void Click_SavePassword() throws Exception
//
//	{
//		login.Save_Password();

//		ExtentListeners.testReport.get().log(Status.INFO, "Click on Save Password if available, Move to next");
//
//	}
	@Test(priority = 6)
	public void Click_Continue() throws Exception

	{
		login.Continue_iOS();

//		ExtentListeners.testReport.get().log(Status.INFO, "Click on Continue, Welcome Inside TDA");
		Detail=new OrderDetail_iOS(driver);

	}
	@Test(priority = 7)
	public void ClickOnPickupTask() throws Exception

	{
		Detail.PickUpTask();

//		ExtentListeners.testReport.get().log(Status.INFO, "Select the PickUpTask On Order List");
		

	}
	@Test(priority = 8)
	public void ClickOnPickupButton() throws Exception

	{
		Detail.PickedDetails();

//		ExtentListeners.testReport.get().log(Status.INFO, "Enter to Order Details, Click on Pickup Button");
		
	}
	@Test(priority = 9)
	public void ClickOnDeliverTask() throws Exception

	{
		Detail.Delivertask();

//		ExtentListeners.testReport.get().log(Status.INFO, "Deliver Task Found");
		
	}
	@Test(priority = 10)
	public void ClickOnDeliverButton() throws Exception

	{
		Detail.DeliveryDetails();

//		ExtentListeners.testReport.get().log(Status.INFO, "Enter to Order Details, Click on Deliver Button");
		
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
