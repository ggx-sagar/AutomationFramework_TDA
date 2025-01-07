package utilities;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import propertyutility.PropertyUtility;

public class CommonUtils extends PropertyUtility {
	
	private static AppiumDriver driver;
	private static URL serverurl;
	private static DesiredCapabilities caps = new DesiredCapabilities();
	
	public static void setAndroidCapabilities() { 
		caps.setCapability("platformName", props.getProperty("platform.Name"));
		caps.setCapability("deviceName", props.getProperty("device.Name"));
		caps.setCapability("automationName", props.getProperty("Automation.Name"));
		caps.setCapability("appPackage", props.getProperty("App.Package"));
		caps.setCapability("appActivity", props.getProperty("App.Activity"));
		caps.setCapability("unicodeKeyboard", "true");
		caps.setCapability("resetKeyboard", "true");
//		caps.setCapability("javascriptEnabled", "true");
		
//		caps.setCapability("avd", "Pixel_8");
//		caps.setCapability("avdLaunchTimeout", "500000");

//	    File path = new File("src/test/resources");
//	    File applocation= new File(path,"universal.apk");
//	    caps.setCapability(MobileCapabilityType.APP, applocation.getAbsolutePath());

	}

	public static void setiOSCapabilities() {
		
		// write Capabilites for IOS
		 caps = new DesiredCapabilities();

//			caps.setCapability("platformName", props.getProperty("platform.Name_iOS"));
			caps.setCapability("platformVersion", props.getProperty("platformversion"));
			caps.setCapability("deviceName", props.getProperty("device.Name_iOS"));
			caps.setCapability("automationName", props.getProperty("Automation.Name_iOS"));
			caps.setCapability("udid", props.getProperty("UDID_iOS"));
			caps.setCapability("app", "/Users/apple/Downloads/Runner.app");
//			caps.setCapability(MobileCapabilityType.APP, "hk.gogovan.tmsDriverApp.dev");
			caps.setCapability("bundleId", props.getProperty("BundleID_iOS"));
			caps.setCapability("newCommandTimeout", props.getProperty("TIME_S"));
			caps.setCapability("noReset", "false");
			caps.setCapability("fullReset", "false");
			caps.setCapability("autoAcceptAlerts", "true");
			caps.setCapability("autoGrantPermissions", "false");
			caps.setCapability("locationServicesEnabled", "true");
	
	}
	
	public static AppiumDriver getAndroidDriver() {
		try {
//			serverurl = new URL("http://localhost:" + props.getProperty("appiumserverport") + "/wd/hub");
			serverurl = new URL("http://localhost:4723/");

		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
//		AppiumServer.AppiumStart();

		driver = new AndroidDriver(serverurl, caps);
//		driver = new AndroidDriver(new URL(“http://localhost:4723/wd/hub”), caps);
//		String sessionId=driver.getSessionId().toString();
		return driver;

	}
	public static AppiumDriver getIOSdriver() {
		try {
			
//			serverurl = new URL("http://localhost:" + props.getProperty("appiumserverport") + "/wd/hub");
			serverurl = new URL("http://localhost:4723");

		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
//		AppiumServer.AppiumStart();
		driver = new IOSDriver(serverurl, caps);
		return driver;
	}

}
