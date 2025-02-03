package testcases_iOS;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import applicationPages.Login_Page_iOS;
import applicationPages.SignOut_iOS;
import basepage.BasePage;
import extentlisteners.TestListeners;
import utilities.appiumServerManager;

public class VerifyUserLogin_iOS extends BasePage{
	Login_Page_iOS Login;
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
	}
	@Test(priority = 2)
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
