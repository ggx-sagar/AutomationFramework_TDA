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
		login = new LoginPage_Android(driver);


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

	}
	@Test(priority = 4)
	public void LocationAllowradiobutton() throws Exception

	{
		login.Location_Allow_button();
	}
	@Test(priority = 5)
	public void ClickonAlwaysallowlocation() throws Exception

	{
		login.Allow_always_button();
	}
	@Test(priority = 6)
	public void Clickonback() throws Exception

	{
		login.Back_button();
		details=new OrderDetails_Android(driver);

	}
	@Test(priority = 7)
	public void ClickonOrderDetails() throws Exception

	{

		details.Taskselector();
	}
	@Test(priority = 8)
	public void ClickonPickupButton() throws Exception

	{

		details.PickedDetails();

	}
	@Test(priority = 9)
	public void ClickonDelivery() throws Exception

	{

		details.Delivertask();
	}
	@Test(priority = 10)
	public void ClickonDeliveredbutton() throws Exception

	{

		details.DeliveryDetails();
	}
	
	
	@AfterTest
	public void quitDriver() {
//		driver.quit();
	
	}

}
