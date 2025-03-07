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
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import applicationPages.Exception_iOS;
import applicationPages.Login_Page_iOS;
import applicationPages.OrderDetail_iOS;
import applicationPages.SignOut_iOS;
import basepage.BasePage;
import utilities.appiumServerManager;


public class Verify_OrderExceptions_From_OrderDetails_iOS extends BasePage {
	Login_Page_iOS Login;
	Exception_iOS Ex;
	SignOut_iOS Out;

	@BeforeSuite
	public void AppiumStart() {
		appiumServerManager.startAppiumServer();
	}

	
	
	@BeforeTest
	public void setupApp() throws InterruptedException {
		setup();
		Thread.sleep(10000);
	}
	
	
	@Test(priority = 1)
	public void GetAppLaunch() throws Exception

	{

		Login=new Login_Page_iOS(driver);
		
		Login.iOS_Username();

		Login.iOS_Password();

		Login.iOS_Submit();
		
		Login.Continue_iOS();
		
		Login.location();
		
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
