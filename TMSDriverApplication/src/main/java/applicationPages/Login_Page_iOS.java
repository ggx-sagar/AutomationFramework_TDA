package applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import basepage.BasePage;
import iOS_Locationpermission.ClickOnLocation;
import io.appium.java_client.AppiumDriver;
import propertyutility.PropertyUtility;

public class Login_Page_iOS extends BasePage {
	
	public Login_Page_iOS(AppiumDriver driver) {
//		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	String USERNAME = PropertyUtility.getreaddata("UserName");
	String PASSWORD = PropertyUtility.getreaddata("PassWord");

	@FindBy(xpath = "//XCUIElementTypeTextField[@name=\"Username\"]")
	protected WebElement Username;

	public void Enterusername() throws Exception {

		Username.click();
		Username.sendKeys(USERNAME);

	}

	public void iOS_Username()  {
		try 
		{

			WebElement userName = driver.findElement(By.xpath("//XCUIElementTypeTextField[@name=\"Username\"]"));
			userName.click();
			userName.sendKeys(USERNAME);
		}
		catch (Exception e) {
			System.out.println(e);
			System.out.println("UserName Not Found-Searching for Password");
//			iOS_Password();

		}
	}

	// Enter PassWord

	public void iOS_Password() {

		WebElement passWord = driver.findElement(By.xpath("//*[contains(@name,'Password')]"));
		passWord.click();
		passWord = driver.findElement(By.xpath("//*[contains(@name,'Password')]"));
		passWord.click();
		passWord.sendKeys(PASSWORD);

	}

	// Click on Submit
	public void iOS_Submit() {
		WebElement submit = driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Sign in\"]"));
		submit.click();
	}

	// Check for Save Password Alert

//	public void Save_Password() {
//		try {
//			MobileElement save_password = driver
//					.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Save Password\"]"));
//			if (save_password.isDisplayed()) {
//				click(save_password);
//				WaitUtility.implicitwait(time_S);
//			}
//		} catch (Exception e) {
//			System.out.println("Save Password Not Found-Searching for next Element");
////			Continue_iOS();
//		}
//	}

	// Check for Continue button

	public void Continue_iOS() throws InterruptedException {

		WebElement Continue_ios = driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Continue\"]"));
		if (Continue_ios.isDisplayed()) {
			Continue_ios.click();
		}
	}

	public void Click_on_Location() {
		ClickOnLocation.clickCordinate(driver);
		;
	}

//
//	public void Allowlocation_iOS() {
//		try {
//			MobileElement allowlocation_ios = driver
//					.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Allow Once\"]"));
//			if (allowlocation_ios.isDisplayed()) {
//				click(allowlocation_ios);
//				Thread.sleep(time_S);
////				ActivityTrack_iOS();
//
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
////			ActivityTrack_iOS();
//		}
//	}
//
//	public void ActivityTrack_iOS() {
//		try {
//			MobileElement ActivityTrack_iOS = driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Allow\"]"));
//			if (ActivityTrack_iOS.isDisplayed()) {
//				click(ActivityTrack_iOS);
//				Thread.sleep(time_S);
//
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//	public void Allow_iOS() {
//		try {
//			MobileElement allow_ios = driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Allow\"]"));
//			if (allow_ios.isDisplayed()) {
//				click(allow_ios);
//				Thread.sleep(time_S);
//				Allowlocation_iOS();
//
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			Allowlocation_iOS();
//		}
//	}

}
