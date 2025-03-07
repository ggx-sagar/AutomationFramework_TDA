package testcases_iOS;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import applicationPages.Login_Page_iOS;
import applicationPages.OrderDetail_iOS;
import applicationPages.SignOut_iOS;
import basepage.BasePage;
import utilities.appiumServerManager;

public class Verify_UserLogin_iOS extends BasePage{
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
