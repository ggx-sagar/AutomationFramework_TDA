package applicationUtility;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.google.common.collect.ImmutableMap;

import basepage.BasePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class ApplicationUtility extends BasePage {

	public static void hidekeyboard() {
		
	}
		public static void scrollTillElement() {
		WebElement element =driver.findElement(MobileBy.AndroidUIAutomator(

                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollToEnd(10)"));
	}
}
