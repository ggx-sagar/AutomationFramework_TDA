package testcases_Android;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import applicationPages.LoginPage_Android;
import applicationPages.RecievedByOther_Android;
import basepage.BasePage;


public class Verify_RecievedByOther_Android extends BasePage {
	
	LoginPage_Android login;

	RecievedByOther_Android other;
	
	
	@Test(priority = 1)
	public void setupApp() throws Exception {
		setup();
//		ExtentListeners.testReport.get().log(Status.INFO, "Application Setup, Launch SuccessFully");
		login = new LoginPage_Android(driver);
		Thread.sleep(4000);

	}

	@Test(priority = 2)
	public void GetAppLaunch() throws Exception

	{
		login.Android_credential();
		
	}
	@Test(priority = 3)
	public void Clickoncontinue() throws Exception

	{
		login.Continue_button();
//		ExtentListeners.testReport.get().log(Status.INFO, "Click on Continue after login.");

	}
	@Test(priority = 4)
	public void LocationAllowradiobutton() throws Exception

	{
		login.Location_Allow_button();
//		ExtentListeners.testReport.get().log(Status.INFO, "Location Allow Radio button clicked.");

	}
	@Test(priority = 5)
	public void ClickonAlwaysallowlocation() throws Exception

	{
		login.Allow_always_button();
//		ExtentListeners.testReport.get().log(Status.INFO, "Allow always Location.");

	}
	@Test(priority = 6)
	public void Clickonback() throws Exception

	{
		login.Back_button();
//		ExtentListeners.testReport.get().log(Status.INFO, "Click on back.");
		other=new RecievedByOther_Android(driver);
	}

	
	@Test(priority = 7)
	public void Recieved_Test() throws Exception

	{


		other.Taskselector();
//		ExtentListeners.testReport.get().log(Status.INFO, "Select Task.");

	}
	@Test(priority = 8)
	public void ClickonOrdernumber() throws Exception

	{
		other.PickedDetails();
//		ExtentListeners.testReport.get().log(Status.INFO, "Order Picked.");
	}
	@Test(priority = 9)
	public void Delivertaskselection() throws Exception

	{
		other.Delivertask();
//		ExtentListeners.testReport.get().log(Status.INFO, "Select Delivery Task.");

	}
	@Test(priority = 10)
	public void Deliverydone() throws Exception

	{
		other.DeliveryDetails();
//		ExtentListeners.testReport.get().log(Status.INFO, "Recieved by other");

	}
	@AfterTest
	public void quitDriver() {
		driver.quit();
//		ExtentListeners.testReport.get().log(Status.INFO, "Driver Quit Successfully");
	
	}
}
