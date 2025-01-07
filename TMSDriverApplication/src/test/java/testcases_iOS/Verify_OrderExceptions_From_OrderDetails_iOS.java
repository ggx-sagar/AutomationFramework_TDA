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

import applicationPages.Exception_iOS;
import applicationPages.Login_Page_iOS;
import applicationPages.SignOut_iOS;
import basepage.BasePage;


public class Verify_OrderExceptions_From_OrderDetails_iOS extends BasePage {
	Login_Page_iOS login;
	Exception_iOS Ex;
	SignOut_iOS Out;

	@Test(priority = 1)
	public void setupApp() {
		setup();
//		Assert.assertEquals(false, null);
//		ExtentListeners.testReport.get().log(Status.INFO, "Application Setup, Launch SuccessFully");
		login = new Login_Page_iOS(driver);

	}

	@Test(priority = 2)
	public void GetAppLaunch() throws Exception

	{
		login.iOS_Username();
//		Assert.assertEquals(false, null);
//		ExtentListeners.testReport.get().log(Status.INFO, "Click on Username, Username Added");

	}
	@Test(priority = 3)
	public void Enter_Password() throws Exception

	{
		login.iOS_Password();
//		Assert.assertEquals(false, null);
//		ExtentListeners.testReport.get().log(Status.INFO, "Click on Password, Password Added");

	}
	@Test(priority = 4)
	public void Click_Submit() throws Exception

	{
		login.iOS_Submit();
//		Assert.assertEquals(false, null);
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
//}
	@Test(priority = 6)
	public void Click_Continue() throws Exception

	{
//		boolean flag= false;
		login.Continue_iOS();
//		Assert.assertEquals(false, flag);
//		ExtentListeners.testReport.get().log(Status.INFO, "Click on Continue, Welcome Inside TDA");
		Ex=new Exception_iOS(driver);

	}
	@Test(priority = 7)
	public void ClickOnPickupTask() throws Exception

	{
		Ex.PickUpTask();
//		Assert.assertEquals(false, null);
//		ExtentListeners.testReport.get().log(Status.INFO, "Select the PickUpTask On Order List");
		

	}
	@Test(priority = 8)
	public void ClickOnPickupButton() throws Exception

	{
		Ex.PickedDetails();
//		Assert.assertEquals(false, null);
//		ExtentListeners.testReport.get().log(Status.INFO, "Enter to Order Details, Click on Pickup Button");
		
	}
	@Test(priority = 9)
	public void ClickOnException() throws Exception

	{
		Ex.selectexception();
//		Assert.assertEquals(false, null);
//		ExtentListeners.testReport.get().log(Status.INFO, "Enter to Order Details, Click on Exception");
		
	}
	@Test(priority = 10)
	public void ClickOnExceptionblock() throws Exception

	{
		Ex.Exception_block();
//		Assert.assertEquals(false, null);
//		ExtentListeners.testReport.get().log(Status.INFO, "Enter to Order Details, Click on Exception Block");
//		
	}
	@Test(priority = 11)
	public void SelectionofOrder() throws Exception

	{
		Ex.Select_order();
//		Assert.assertEquals(false, null);
//		ExtentListeners.testReport.get().log(Status.INFO, "Enter to Order Details, Click on Select  order");
		
	}
	@Test(priority = 12)
	public void ClickonTryagain() throws Exception

	{
		Ex.tryagain();
//		Assert.assertEquals(false, null);
//		ExtentListeners.testReport.get().log(Status.INFO, "Enter to Order Details, Click on TryAgain  order");
		
	}
	@Test(priority = 13)
	public void ClickTODO() throws Exception

	{
		Ex.TO_DO();
//		Assert.assertEquals(false, null);
//		ExtentListeners.testReport.get().log(Status.INFO, "Enter to Order Details, Click on ToDo  order");
		
	}
	@Test(priority = 14)
	public void ClickOnDeliverTask() throws Exception

	{
		Ex.Delivertask();
//		Assert.assertEquals(false, null);
//		ExtentListeners.testReport.get().log(Status.INFO, "Deliver Task Found");
		
	}
	@Test(priority = 15)
	public void ClickOnDeliverButton() throws Exception

	{
		Ex.DeliveryDetails();
//		Assert.assertEquals(false, null);
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
