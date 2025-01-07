package basepage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import propertyutility.PropertyUtility;
import utilities.CommonUtils;


public class BasePage extends CommonUtils{
	public static AppiumDriver driver;
	public static String key = "config.properties";
	public static String OS=PropertyUtility.getreaddata("Operating_System");
	
	public void setup() {
		// AppiumServer.start();
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
