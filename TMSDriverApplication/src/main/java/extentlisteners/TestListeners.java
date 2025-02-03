package extentlisteners;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import basepage.BasePage;
import io.appium.java_client.AppiumDriver;
import screenshotutility.ScreenShotUtility;


public class TestListeners extends BasePage implements ITestListener {
//	public TestListeners(AppiumDriver driver) {
//		PageFactory.initElements(driver, this);
//	}
	
    private static ExtentReports extent;
    private static ExtentTest test;
    public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
    
    


    static {
        String fileName = "Extent_Logs_Report_" + localTime() + ".html";
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + fileName);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        htmlReporter.config().setTheme(Theme.valueOf(props.getProperty("Report_Theme_Name")));
        htmlReporter.config().setDocumentTitle(props.getProperty("Document_Title"));
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(props.getProperty("Automation_Report_Name"));
        extent.setSystemInfo("Automation Tester", props.getProperty("Automation_Tester"));
        extent.setSystemInfo("Organization", props.getProperty("Organisation"));
        extent.setSystemInfo("Build No", props.getProperty("Build"));
        extent.setSystemInfo("Version", props.getProperty("Version"));
        
    }

    private static String localTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName() + " - Test Case: " + result.getMethod().getMethodName());
        testReport.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>TEST CASE: " + methodName + " PASSED</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        testReport.get().log(Status.PASS, m);
//        testReport.get().pass(m);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        String details = "<details><summary><b><font color='red'>Exception Occurred: Click to see</font></b></summary>"
                + exceptionMessage.replaceAll(",", "<br>") + "</details>";
        testReport.get().fail(details);

        String screenshotPath = ScreenShotUtility.screenshotPath;
        captureScreenShot();
		testReport.get().fail("<b><font color='red'>Screenshot of failure</font></b>",
		        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

        String failureLog = "TEST CASE FAILED";
        Markup m = MarkupHelper.createLabel(failureLog, ExtentColor.RED);
        testReport.get().log(Status.FAIL, m);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>Test Case: " + methodName + " Skipped</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        testReport.get().skip(m);
    }


	public static void captureScreenShot() {
		try {
			Thread.sleep(1000);
			ScreenShotUtility.captureScreenshot();
			testReport.get().pass("<b>" + "<font color=" + "green>" + "Screenshot" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ScreenShotUtility.screenshotName).build());
		} catch (Exception e) {
			System.out.println("Exception in Capture ScreenShot" + e);
		}
	}
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Implement as needed
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }
}

//    private static ExtentReports extent;
//    private static ExtentTest test;
//    public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
//
//    static {
//        // Set up the report filename and location
//        String fileName = "Extent_Logs_Report_" + localTime() + ".html";
//        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + fileName);
//
//        // Initialize ExtentReports and attach the reporter
//        extent = new ExtentReports();
//        extent.attachReporter(htmlReporter);
//
//        // Configuration for the HTML report
//        htmlReporter.config().setTheme(Theme.valueOf(props.getProperty("Report_Theme_Name")));
//        htmlReporter.config().setDocumentTitle(props.getProperty("Document_Title"));
//        htmlReporter.config().setEncoding("utf-8");
//        htmlReporter.config().setReportName(props.getProperty("Automation_Report_Name"));
//
//        // System information
//        extent.setSystemInfo("Automation Tester", props.getProperty("Automation_Tester"));
//        extent.setSystemInfo("Organization", props.getProperty("Organisation"));
//        extent.setSystemInfo("Build No", props.getProperty("Build"));
//        extent.setSystemInfo("Version", props.getProperty("Version"));
//    }
//
//    private static String localTime() {
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
//        LocalDateTime now = LocalDateTime.now();
//        return dtf.format(now);
//    }
//
//    @Override
//    public void onTestStart(ITestResult result) {
//        test = extent.createTest(result.getTestClass().getName() + " - Test Case: " + result.getMethod().getMethodName());
//        testReport.set(test);
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        String methodName = result.getMethod().getMethodName();
//        String logText = "<b>TEST CASE: " + methodName + " PASSED</b>";
//        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
//        testReport.get().log(Status.PASS, m);
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
//        String details = "<details><summary><b><font color='red'>Exception Occurred: Click to see</font></b></summary>"
//                + exceptionMessage.replaceAll(",", "<br>") + "</details>";
//        testReport.get().fail(details);
//
//        // Capture the screenshot if the test fails
//        String screenshotPath = ScreenShotUtility.screenshotPath;
//        captureScreenShot();
//        try {
//            testReport.get().fail("<b><font color='red'>Screenshot of failure</font></b>",
//                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//        } catch (Exception e) {
//            System.out.println("Error attaching screenshot: " + e.getMessage());
//        }
//
//        String failureLog = "TEST CASE FAILED";
//        Markup m = MarkupHelper.createLabel(failureLog, ExtentColor.RED);
//        testReport.get().log(Status.FAIL, m);
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        String methodName = result.getMethod().getMethodName();
//        String logText = "<b>Test Case: " + methodName + " Skipped</b>";
//        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
//        testReport.get().skip(m);
//    }
//
//    public static void captureScreenShot() {
//        try {
//            Thread.sleep(1000);
//            ScreenShotUtility.captureScreenshot();
//            testReport.get().pass("<b>" + "<font color=" + "green>" + "Screenshot" + "</font>" + "</b>",
//                    MediaEntityBuilder.createScreenCaptureFromPath(ScreenShotUtility.screenshotName).build());
//        } catch (Exception e) {
//            System.out.println("Exception in Capture ScreenShot" + e);
//        }
//    }
//
//    @Override
//    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//        // Implement as needed for retry or other logic
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        if (extent != null) {
//            extent.flush();  // Ensure that the report is written to disk
//        }
//    }}
