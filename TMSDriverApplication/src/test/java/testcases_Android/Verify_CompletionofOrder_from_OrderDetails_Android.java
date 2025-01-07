package testcases_Android;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import applicationPages.LoginPage_Android;
import applicationPages.OrderDetails_Android;
import basepage.BasePage;
import extentlisteners.TestListeners;


public class Verify_CompletionofOrder_from_OrderDetails_Android extends BasePage {
	LoginPage_Android login;
	OrderDetails_Android details;


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
		details=new OrderDetails_Android(driver);

	}
	@Test(priority = 7)
	public void ClickonOrderDetails() throws Exception

	{

		details.Taskselector();
		TestListeners.testReport.get().log(Status.INFO, "Task selected and Pickup Done");

	}
	@Test(priority = 8)
	public void ClickonPickupButton() throws Exception

	{

		details.PickedDetails();
		TestListeners.testReport.get().log(Status.INFO, "Task selected and Pickup Done");

	}
	@Test(priority = 9)
	public void ClickonDelivery() throws Exception

	{

		details.Delivertask();
		TestListeners.testReport.get().log(Status.INFO, "Deliver task found");
	}
	@Test(priority = 10)
	public void ClickonDeliveredbutton() throws Exception

	{

		details.DeliveryDetails();
		TestListeners.testReport.get().log(Status.INFO, "Deliver Done");
	}
	
	
	@AfterTest
	public void quitDriver() {
		driver.quit();
		TestListeners.testReport.get().log(Status.INFO, "Driver Quit Successfully");
	
	}

}
