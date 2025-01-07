package testcases_iOS;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import basepage.BasePage;

public class Verify_app_setup extends BasePage{
	
//	@BeforeTest
//	
//	public void StartAppium() {
//		AppiumServer Server=new AppiumServer();
//		Server.AppiumStart();
//		
//		
//	}
	@Test(priority = 1)
	public void setupApp() {
		
		setup();
	}

}
