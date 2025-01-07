package testcases_iOS;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import applicationPages.Login_Page_iOS;
import basepage.BasePage;

public class VerifyUserLogin_iOS extends BasePage{
	Login_Page_iOS Login=new Login_Page_iOS(driver);
	
	@Test(priority = 1)
	public void setupApp() throws InterruptedException {
		
		setup();
		Thread.sleep(10000);
	}
	@Test(priority = 2)
	public void GetAppLaunch() throws Exception

	{
		Login.iOS_Username();

		Login.iOS_Password();

		Login.iOS_Submit();
		
		Login.Continue_iOS();
	}
	
	

}
