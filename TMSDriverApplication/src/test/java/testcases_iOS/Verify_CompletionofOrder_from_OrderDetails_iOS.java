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

import applicationPages.Login_Page_iOS;
import applicationPages.OrderDetail_iOS;
import applicationPages.SignOut_iOS;
import basepage.BasePage;
import utilities.appiumServerManager;


public class Verify_CompletionofOrder_from_OrderDetails_iOS extends BasePage {
	Login_Page_iOS Login;
	OrderDetail_iOS Detail;
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
		
		Detail=new OrderDetail_iOS(driver);
	}
	@Test(priority = 2)
	public void ClickOnPickupTask() throws Exception

	{
		Thread.sleep(10000);
		Detail.PickUpTask();

//		ExtentListeners.testReport.get().log(Status.INFO, "Select the PickUpTask On Order List");
		

	}
	@Test(priority = 3)
	public void ClickOnPickupButton() throws Exception

	{
		Thread.sleep(5000);
		Detail.PickedDetails();

//		ExtentListeners.testReport.get().log(Status.INFO, "Enter to Order Details, Click on Pickup Button");
		
	}
	@Test(priority = 4)
	public void ClickOnDeliverTask() throws Exception

	{
		Thread.sleep(10000);
		Detail.Delivertask();

//		ExtentListeners.testReport.get().log(Status.INFO, "Deliver Task Found");
		
	}
	@Test(priority = 5)
	public void ClickOnDeliverButton() throws Exception

	{
		Thread.sleep(5000);
		Detail.DeliveryDetails();

//		ExtentListeners.testReport.get().log(Status.INFO, "Enter to Order Details, Click on Deliver Button");
		
	}
	@Test(priority = 6)
	public void Signout() throws Exception{
		Thread.sleep(10000);
		Out=new SignOut_iOS(driver);
		Out.Sign_Out_iOS();
	}
	
	@AfterTest
	public void StopAppium() throws Exception{

		utilities.appiumServerManager.stopAppiumServer();
		
	}
	
	


	
	
}
