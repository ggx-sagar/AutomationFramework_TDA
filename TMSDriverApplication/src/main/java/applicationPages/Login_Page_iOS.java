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
		PageFactory.initElements(driver, this);
	}

	String USERNAME = PropertyUtility.getreaddata("UserName");
	String PASSWORD = PropertyUtility.getreaddata("PassWord");

	@FindBy(xpath = "//XCUIElementTypeTextField[@name=\"Username\"]")
	protected WebElement Username;
	
	public void iOS_Username() throws InterruptedException  {
		Thread.sleep(10000);
			Username.click();
			Username.sendKeys(USERNAME);
	}

	@FindBy(xpath = "//*[contains(@name,'Password')]")
	protected WebElement passWord;
	
	public void iOS_Password() throws InterruptedException {
		Thread.sleep(10000);
		passWord.click();
		passWord = driver.findElement(By.xpath("//*[contains(@name,'Password')]"));
		passWord.click();
		passWord.sendKeys(PASSWORD);
	}

	
	@FindBy(xpath = "//XCUIElementTypeButton[@name=\"Sign in\"]")
	protected WebElement submit;
	
	public void iOS_Submit() {
		submit.click();
	}







	public void Continue_iOS() throws InterruptedException {
		Thread.sleep(10000);

		WebElement Continue_ios = driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Continue\"]"));
		if (Continue_ios.isDisplayed()) {
			Continue_ios.click();
			Thread.sleep(5000);
		}
	}

	@FindBy(xpath = "//XCUIElementTypeButton[@name='Cancel']")
	protected WebElement dontallowlocation;
	
	public void location() throws InterruptedException  {
		Thread.sleep(10000);
			dontallowlocation.click();
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
