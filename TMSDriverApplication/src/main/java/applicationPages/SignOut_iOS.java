package applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import basepage.BasePage;

import io.appium.java_client.AppiumDriver;

import propertyutility.PropertyUtility;


public class SignOut_iOS extends BasePage {
	
    public SignOut_iOS(AppiumDriver driver)
    {
		PageFactory.initElements(driver, this);    
    }
	String wait_L = PropertyUtility.getreaddata("TIME_L");
	int time_L = Integer.parseInt(wait_L);
	String wait_S = PropertyUtility.getreaddata("TIME_S");
	int time_S = Integer.parseInt(wait_S);


	@FindBy(xpath = "//XCUIElementTypeApplication[@name=\"GoGoX Enterprise\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeButton[1]")
	protected WebElement Option;

	@FindBy(xpath = "//XCUIElementTypeButton[@name=\"Settings\"]")
	protected WebElement ClickSettings;
	
	@FindBy(xpath = "//XCUIElementTypeButton[@name=\"Sign out\"]")
	protected WebElement ClickSignOut;
//	IOS Sign Out------------------------------------------------------------------------
	
	
	public void Sign_Out_iOS() {
		try {
			Thread.sleep(time_L);
//			WaitUtility.ExplicitWait(driver, Option, time_L);
			if (Option.isDisplayed()) {
				System.out.println("Option found");
				Option.click();
				System.out.println("Option Clicked");
				Thread.sleep(time_S);
				ClickSettings.click();
				Thread.sleep(time_L);
				ClickSignOut.click();
//     			WaitUtility.ExplicitWait(driver, ClickSignOut, time_L);
     			

			}

		} catch (Exception e) {
			System.out.println("SignOut Failed");
//			System.out.println(e);
			Sign_Out_iOS();
			// TODO: handle exception
		}

	}


	


}
