package testcases_Android;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import applicationPages.Exception_Android;
import applicationPages.LoginPage_Android;
import basepage.BasePage;
import extentlisteners.TestListeners;


public class Verify_OrderExceptions_From_OrderDetails_Android extends BasePage {

	LoginPage_Android login;

	Exception_Android EX;


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
		TestListeners.testReport.get().log(Status.INFO, "Login Done Successfully");
		

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
		EX=new Exception_Android(driver);
	}
	

	@Test(priority = 7)
	public void EXCEPTION_TEST_1() throws Exception

	{
		
		EX.Taskselector_exception();
		TestListeners.testReport.get().log(Status.INFO, "Pickup Task selected");

	}
	@Test(priority = 8)
	public void Clickonordernumber() throws Exception

	{
		
		EX.PickedDetails();
		TestListeners.testReport.get().log(Status.INFO, "Order details clicked");

	}
	@Test(priority = 9)
	public void reportexception() throws Exception

	{
		
		EX.selectexception();
		TestListeners.testReport.get().log(Status.INFO, "Order Exception is completed");

	}
	@Test(priority = 10)
	public void Clickonexceptiontab() throws Exception

	{
		
		EX.Exception_block();
		TestListeners.testReport.get().log(Status.INFO, "Click on Exception tab to resolve exception");

	}
	@Test(priority = 11)
	public void Orderselectioninexception() throws Exception

	{
		
		EX.Select_order();
		TestListeners.testReport.get().log(Status.INFO, "Order Done in Exception");

	}
	@Test(priority = 12)
	public void ClickonTODOtab() throws Exception

	{
		
		EX.TO_DO();
		TestListeners.testReport.get().log(Status.INFO, "Click on Todo to complete delivery");

	}
	@Test(priority = 13)
	public void Selectdeliverytask() throws Exception

	{
		
		EX.Delivertask();
		TestListeners.testReport.get().log(Status.INFO, "Delivery task selected");

	}
	@Test(priority = 14)
	public void Completedelivery() throws Exception

	{
		
		EX.DeliveryDetails();
		TestListeners.testReport.get().log(Status.INFO, "DeliveryDone");

	}
	@AfterTest
	public void quitDriver() {
		driver.quit();
		TestListeners.testReport.get().log(Status.INFO, "Driver Quit Successfully");
	
	}
	
}
