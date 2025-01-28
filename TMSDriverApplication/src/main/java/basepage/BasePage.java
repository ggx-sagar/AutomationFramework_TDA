package basepage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import propertyutility.PropertyUtility;

import utilities.CommonUtils;
import utilities.appiumServerManager;


public class BasePage extends CommonUtils{
	public void z(AppiumDriver driver) {
//		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public static AppiumDriver driver;
	public static String key = "config.properties";
	public static String OS=PropertyUtility.getreaddata("Operating_System");
	
	public void setup() {
		appiumServerManager.startAppiumServer();
		PropertyUtility.getreaddata(key);
		if (OS.equalsIgnoreCase("iOS")) {
			CommonUtils.setiOSCapabilities();
			driver = CommonUtils.getIOSdriver();

		} else if (OS.equalsIgnoreCase("Android")) {
			CommonUtils.setAndroidCapabilities();
			driver = CommonUtils.getAndroidDriver();
		}
	}
	
}
