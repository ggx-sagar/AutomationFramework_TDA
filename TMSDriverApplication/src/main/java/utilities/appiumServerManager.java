package utilities;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class appiumServerManager {
	public static AppiumServiceBuilder appiumServiceBuilder;
	public static AppiumDriverLocalService appiumDriverLocalService;

	public static void startAppiumServer() {
		killAppiumSession();
		// Build the Appium server
//		String[] appiumPlugins = {"element-wait", "gestures"};


		appiumServiceBuilder = new AppiumServiceBuilder();
		appiumServiceBuilder.withIPAddress("127.0.0.1");
		appiumServiceBuilder.usingPort(4723);
//		appiumServiceBuilder.withArgument(GeneralServerFlag.PLUGINS, "element-wait");
//		appiumServiceBuilder.withArgument(GeneralServerFlag.PLUGINS, String.join(",", appiumPlugins));
		appiumServiceBuilder.withArgument(GeneralServerFlag.LOG_LEVEL, "debug");

		// Start the server with Builder

		appiumDriverLocalService = AppiumDriverLocalService.buildService(appiumServiceBuilder);
		appiumDriverLocalService.start();
		System.out.println("Appium server started at: " + appiumDriverLocalService.getUrl());
	}

	public static void stopAppiumServer() {
		if (appiumDriverLocalService.isRunning() && appiumDriverLocalService != null) {
			appiumDriverLocalService.stop();
		}

	}

	public static void killAppiumSession() {
		String[] command = { "/usr/bin/killall", "KILL", "node" };
		try {
			Runtime.getRuntime().exec(command);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
