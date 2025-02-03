package iOS_Locationpermission;

import java.util.HashMap;

import io.appium.java_client.AppiumDriver;

public class ClickOnLocation {
	public static void clickCordinate(AppiumDriver driver) {
	HashMap args = new HashMap();
    args.put("action", "accept");
    args.put("buttonLabel","Allow While Using App");

    // prepare command
    String cmd = "mobile: alert";

    // execute
    try {
        driver.executeScript(cmd, args);
    } catch (Exception e) {
    	System.out.println(e);
    }}
}
