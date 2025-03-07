package testcases_iOS;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
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

import applicationPages.CompleteAll_iOS;
import applicationPages.Login_Page_iOS;
import applicationPages.SignOut_iOS;
import basepage.BasePage;
import utilities.appiumServerManager;


public class Verify_CompletionOfOrder_By_PICKUP_OR_DELIVER_ALL_iOS extends BasePage {
	Login_Page_iOS Login;
	CompleteAll_iOS all;
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
		
		all=new CompleteAll_iOS(driver);
	}
	
	@Test(priority = 2)
	public void CompletePickUp() throws Exception

	{
		all.Pick_Up_button_iOS();

//		ExtentListeners.testReport.get().log(Status.INFO, "Pick done Successfully");
		

	}
	@Test(priority = 3)
	public void CompleteDelivery() throws Exception

	{
		all.Deliver_button_iOS();

//		ExtentListeners.testReport.get().log(Status.INFO, "Delivery done Successfully");
		

	}
	@Test(priority = 4)
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
