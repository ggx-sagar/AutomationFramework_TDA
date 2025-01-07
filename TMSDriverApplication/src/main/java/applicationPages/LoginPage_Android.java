package applicationPages;

import java.security.Permission;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import basepage.BasePage;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.pagefactory.AndroidFindBy;
import propertyutility.PropertyUtility;


public class LoginPage_Android extends BasePage {
	
	
    public LoginPage_Android(AppiumDriver driver)
    {
            this.driver = driver;
//            PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        
    }
	

	String wait_L = PropertyUtility.getreaddata("TIME_L");
	int time_L = Integer.parseInt(wait_L);
	String wait_S = PropertyUtility.getreaddata("TIME_S");
	int time_S = Integer.parseInt(wait_S);
	String USERNAME = PropertyUtility.getreaddata("UserName");
	String PASSWORD = PropertyUtility.getreaddata("PassWord");



	public void Android_credential() throws Exception {

		// Enter UserName
		Thread.sleep(time_L);
//		WaitUtility.implicitwait(time_L);
		WebElement userName = driver.findElement(By.xpath("(//*[@class='android.widget.EditText'])[1]"));
//		WaitUtility.ExplicitWait(driver, userName, time_L);
		userName.click();
//		WaitUtility.ExplicitWait(driver, userName, time_L);
		Thread.sleep(time_S);
		userName.sendKeys(USERNAME);

		// Enter PassWord

		WebElement passWord = driver.findElement(By.xpath("(//*[@class='android.widget.EditText'])[2]"));
//		WaitUtility.ExplicitWait(driver, passWord, time_L);
		passWord.click();
		Thread.sleep(time_S);
		passWord.sendKeys(PASSWORD);

		// Click on Submit

		WebElement submit = driver.findElement(By.xpath("//android.widget.Button[@content-desc='Sign in']"));
//		WaitUtility.ExplicitWait(driver, submit, time_L);
		submit.click();

//		Continue_button();

	}



	public void Continue_button() throws InterruptedException {
		WebElement Continue = driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Continue\"]"));
		if (Continue.isDisplayed()) {
//			WaitUtility.ExplicitWait(driver, Continue, time_L);
			Continue.click();
//			WaitUtility.implicitwait(time_L);
//			Location_Allow_button();
		} else {
			Location_Allow_button();
		}
	}

	public void Location_Allow_button() throws InterruptedException {
		WebElement Location_allow = driver.findElement(By.xpath(
				"//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]"));
		if (Location_allow.isDisplayed()) {
//			WaitUtility.ExplicitWait(driver, Location_allow, time_L);
			Location_allow.click();
//			WaitUtility.implicitwait(time_L);
//			Allow_always_button();
		} else {
			Allow_always_button();

		}
	}

	public void Allow_always_button() throws InterruptedException {
		WebElement Allow_Always = driver
				.findElement(By.id("com.android.permissioncontroller:id/allow_always_radio_button"));
		if (Allow_Always.isDisplayed()) {
//			WaitUtility.ExplicitWait(driver, Allow_Always, time_L);
			Allow_Always.click();
//			WaitUtility.implicitwait(time_L);
//			Back_button();
		} else {
			Back_button();

		}
	}

	public void Back_button() throws InterruptedException {
		WebElement Back = driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Back']"));
		if (Back.isDisplayed()) {
//			WaitUtility.ExplicitWait(driver, Back, time_L);
			Back.click();
//			WaitUtility.implicitwait(time_L);
			Activity_button();

		} else {
			Activity_button();

		}
	}

	public void Activity_button() {
		try {
			WebElement Activity = driver.findElement(By.xpath(
					"//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]"));
			if (Activity.isDisplayed()) {
//				WaitUtility.ExplicitWait(driver, Activity, time_L);
				Activity.click();
//				WaitUtility.implicitwait(time_S);
			}
		} catch (Exception e) {
			System.out.println("No Activity Found- Moving to next test");
		}
	}


	
	
	
	
	
	
	
	
	
	
	
//	public void compare() throws Exception {
//	if (OS.equalsIgnoreCase("iOS")) {
//		iOS_Username();
//	} else if (OS.equalsIgnoreCase("Android")) {
//		Android_credential();
//	}
//}	
	

//	public Login_Page_iOS(AppiumDriver<MobileElement> driver) {
//		this.driver = driver;
//		PageFactory.initElements(driver, this);
//		String wait=PropertyUtility.getreaddata("TIME");
//		int time=Integer.parseInt(wait);
//		}
//
//@AndroidFindBy(xpath = "(//*[@class='android.widget.EditText'])[1]")
//private MobileElement userName;
//
//@AndroidFindBy(xpath = "(//*[@class='android.widget.EditText'])[2]")
//private MobileElement passWord;
////
//
//public void username() {
//
//click(userName);
//sendkeys(userName, "driver_hk_sagar");
//
//}
//public void password() {
//click(passWord);
// sendkeys(passWord, "qwerty");
//		------------------------------------------------------------------------------------------------------------------------------------------------------------

}
