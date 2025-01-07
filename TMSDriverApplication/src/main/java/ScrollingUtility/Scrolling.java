package ScrollingUtility;

import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;

import basepage.BasePage;
import io.appium.java_client.AppiumDriver;

public class Scrolling extends BasePage{


	public static void sc() {
	
    JavascriptExecutor js = (JavascriptExecutor) driver;
    HashMap<String,Object> scrollObject = new HashMap<String, Object>();
    scrollObject.put("predicateString", "value == ");
   scrollObject.put("direction", "down");
         js.executeScript("mobile: scroll", scrollObject);
}
}
