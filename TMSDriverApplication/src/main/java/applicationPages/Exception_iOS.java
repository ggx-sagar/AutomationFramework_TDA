package applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;


import basepage.BasePage;

import io.appium.java_client.AppiumDriver;

import propertyutility.PropertyUtility;


public class Exception_iOS extends BasePage {
	
    public Exception_iOS(AppiumDriver driver)
    {
            this.driver = driver;
//            PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        
    }
	String wait_L = PropertyUtility.getreaddata("TIME_L");
int time_L = Integer.parseInt(wait_L);
String wait_S = PropertyUtility.getreaddata("TIME_S");
int time_S = Integer.parseInt(wait_S);

public void PickUpTask() {
//	try {
		WebElement PickupTask = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"PICK-UP\"]"));
		if (PickupTask.isDisplayed()) {
			System.out.println("Pickup task found");
//			WaitUtility.implicitwait(time_S);
			WebElement Order_details = driver.findElement(By.xpath(
					"(//XCUIElementTypeStaticText[contains(@name,'GGX')])[1]"));
//			WaitUtility.implicitwait(time_S);
			Order_details.click();
//			WaitUtility.implicitwait(time_S);

		}
//	} catch (Exception e) {
//		System.out.println("Pickup task not found");
//
//	}
}
public void PickedDetails() {
//	try {
		WebElement PickedUpException = driver
				.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Report exception\"]"));
//		ApplicationUtility.swipeUpTillElement(PickedUpException);
//		WaitUtility.ExplicitWait(driver, PickedUpException, time_L);
		if (PickedUpException.isDisplayed()) {
			System.out.println("Exception Button found");
//			WaitUtility.implicitwait(time_L);
			PickedUpException.click();
			System.out.println(" Report Exception Clicked ");
//			WaitUtility.implicitwait(time_S);

		}
//		selectexception();
		
//	} catch (Exception e) {
//		System.out.println(e);
//		System.out.println("Pick-up Exception Failed");
//		DeliveryDetails();
//	}
}
public void selectexception() {
//	try {
		WebElement Select_Exception = driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Shipment damaged\"]"));
//		WaitUtility.ExplicitWait(driver, Select_Exception, time_L);
		if (Select_Exception.isDisplayed()) {
//			WaitUtility.implicitwait(time_S);
			Select_Exception.click();
//			Thread.sleep(time_S);
//			MobileElement Select_Ok = driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"OK\"]"));
//			WaitUtility.ExplicitWait(driver, Select_Ok, time_L);
//			click(Select_Ok);
//			ExtentListeners.testReport.get().log(Status.INFO, "Done with Exception");
//			WaitUtility.implicitwait(time_S);
//			Exception_block();
		

		}
		
//	} catch (Exception e) {
//		System.out.println(e);
//		System.out.println("Select Exception Failed");
//	}
}
public void Select_order() {
//	try {
//		MobileElement PickupTask = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\\\"PICK-UP\\\"]"));
//		if (PickupTask.isDisplayed()) {
//			System.out.println("Pickup task found");
//			Thread.sleep(time_S);
			WebElement Order_details_Exception = driver.findElement(By.xpath(
					"//XCUIElementTypeStaticText[contains(@name,'GGX')][1]"));
//			WaitUtility.ExplicitWait(driver, Order_details_Exception, time_L);
			Order_details_Exception.click();
//			WaitUtility.implicitwait(time_L);
//			tryagain();
//		}
	}
//	catch (Exception e) {
//		System.out.println(e);
//		System.out.println("Pickup task not found");
////		Delivertask();
//	}
//}
public void Exception_block() {
//	try {
		WebElement Exception_block_click = driver.findElement(By.xpath("///XCUIElementTypeImage[contains(@name,'Exception')]"));
		if (Exception_block_click.isDisplayed()) {
//			WaitUtility.ExplicitWait(driver, Exception_block_click, time_L);
			Exception_block_click.click();
//			WaitUtility.implicitwait(time_L);
//			Select_order();
//			Thread.sleep(time_S);
		    

		}
		
//	} catch (Exception e) {
//		System.out.println(e);
//		System.out.println("Select ExceptionBlock Failed");
////		DeliveryDetails();
//	}
}

public void Delivertask() {
//	try {
		WebElement DeliveryTask = driver
				.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name,'DELIVERY')]"));

		if (DeliveryTask.isDisplayed()) {
			System.out.println("Deliver task found");

//			WaitUtility.implicitwait(time_S);
			WebElement Order_details1 = driver.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name,'GGX')]"));
			System.out.println("Element processed");
//			WaitUtility.ExplicitWait(driver, Order_details1, time_L);
			Order_details1.click();
			System.out.println("Element clicked");
//			WaitUtility.implicitwait(time_L);
//			DeliveryDetails();
		}
//	} catch (Exception E) {
//		System.out.println("Deliver task not found");
////		Taskselector_exception();
//	}
	// TODO: handle exception
}






public void tryagain() {
//	try {
		WebElement Tryagain = driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Try again now\"]"));
//		WaitUtility.ExplicitWait(driver, Tryagain, time_L);
		if (Tryagain.isDisplayed()) {
//			WaitUtility.implicitwait(time_L);
			Tryagain.click();
//			WaitUtility.implicitwait(time_L);
			Picked_Details();
//			WaitUtility.implicitwait(time_L);
//			TO_DO();
		

		}
		
//	} catch (Exception e) {
//		System.out.println(e);
//		System.out.println("Try again failed");
////		DeliveryDetails();}
//	}
}
	

public void DeliveryDetails() {
//	try {
		WebElement Delivery = driver
				.findElement(By.xpath("//XCUIElementTypeButton[contains(@name,'Delivered')]"));
		System.out.println("Element found");
//		WaitUtility.ExplicitWait(driver, Delivery, time_L);
		System.out.println("element to be clicked");
		Delivery.click();
//		WaitUtility.implicitwait(time_L);
//		Taskselector_exception();
//	} catch (Exception e) {
////		Taskselector();
//		System.out.println("Order Delivered Failed");
//	}
}
public void TO_DO() {
//	try {
		WebElement TODO = driver
				.findElement(By.xpath("//XCUIElementTypeImage[contains(@name,'To Do')]"));
		System.out.println("TO DO Element found");
//		WaitUtility.ExplicitWait(driver, TODO, time_L);
		System.out.println("element to be clicked");
		TODO.click();
//		WaitUtility.implicitwait(time_L);
//		Delivertask();
//	} catch (Exception e) {
////		Taskselector();
//		System.out.println("ToDO failed");
//	}
}
public void Picked_Details() {
//	try {
		WebElement PickedUp = driver
				.findElement(By.xpath("//XCUIElementTypeButton[contains(@name,'Picked up')]"));
//		WaitUtility.ExplicitWait(driver, PickedUp, time_L);
		if (PickedUp.isDisplayed()) {
//			WaitUtility.implicitwait(time_L);
			PickedUp.click();
//			WaitUtility.implicitwait(time_S);
		}
		
	} 
//	catch (Exception e) {
//		System.out.println(e);
//		System.out.println("Pick-up Failed");
////		DeliveryDetails();
//	}
		
	
}



