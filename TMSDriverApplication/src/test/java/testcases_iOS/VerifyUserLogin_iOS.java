package testcases_iOS;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import applicationPages.Login_Page_iOS;
import basepage.BasePage;

public class VerifyUserLogin_iOS extends BasePage{
	Login_Page_iOS Login;
	
	@Test(priority = 1)
	public void setupApp() throws InterruptedException {
		
		setup();
		Thread.sleep(10000);
	}
	@Test(priority = 2)
	public void GetAppLaunch() throws Exception

	{
		Login=new Login_Page_iOS(driver);
		
		Login.Enterusername();

		Login.iOS_Password();

		Login.iOS_Submit();
		
		Login.Continue_iOS();
	}
	
	@AfterTest
	public void StopAppium() throws Exception{
		utilities.appiumServerManager.stopAppiumServer();
	}
	

}
