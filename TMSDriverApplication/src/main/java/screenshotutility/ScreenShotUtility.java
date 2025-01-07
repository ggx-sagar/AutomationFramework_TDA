package screenshotutility;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import basepage.BasePage;
import io.appium.java_client.AppiumDriver;

public class ScreenShotUtility extends BasePage{
    public ScreenShotUtility(AppiumDriver driver)
    {
            this.driver = driver;
//            PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        
    }


//    public static String screenshotPath;
    public static String screenshotPath;
    public static String screenshotName;

    // Capture a screenshot and save it
    public static void captureScreenshot() {
        // Ensure that driver is initialized and cast to TakesScreenshot
        if (BasePage.driver instanceof TakesScreenshot) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) BasePage.driver;

            // Take screenshot and save it to a file
            File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            
            // Create a timestamp for the screenshot name
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd_HH:mm:ss"));
            screenshotName = "Screenshot_" + timestamp + ".jpg";
            screenshotPath = System.getProperty("user.dir") + "/screenshot/" + screenshotName;

            try {
                FileUtils.copyFile(srcFile, new File(screenshotPath));
            } catch (IOException e) {
                e.printStackTrace(); // Log the exception for troubleshooting
            }
        } else {
            throw new IllegalStateException("Driver instance is not a TakesScreenshot");
        }
}}
