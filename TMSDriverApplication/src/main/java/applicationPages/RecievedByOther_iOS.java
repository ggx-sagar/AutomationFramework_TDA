package applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import basepage.BasePage;
import io.appium.java_client.AppiumDriver;

import propertyutility.PropertyUtility;


public class RecievedByOther_iOS extends BasePage{
    public RecievedByOther_iOS(AppiumDriver driver)
    {
            this.driver = driver;
//            PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        
    }

	String wait_L = PropertyUtility.getreaddata("TIME_L");
	int time_L = Integer.parseInt(wait_L);
	String wait_S = PropertyUtility.getreaddata("TIME_S");
	int time_S = Integer.parseInt(wait_S);

	public void PickUpTask() {
//		try {
			WebElement PickupTask = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"PICK-UP\"]"));
			if (PickupTask.isDisplayed()) {
				System.out.println("Pickup task found");
//				WaitUtility.implicitwait(time_S);
				WebElement Order_details = driver.findElement(By.xpath(
						"(//XCUIElementTypeStaticText[contains(@name,'GGX')][1]"));
//				WaitUtility.ExplicitWait(driver, Order_details, time_L);
				Order_details.click();
				//				WaitUtility.implicitwait(time_S);
//				PickedDetails();

		}
//	} catch (Exception e) {
//		System.out.println(e);
//		System.out.println("Pickup task not found");
////		Delivertask();
//	}
}

public void Delivertask() {
//	try {
		WebElement DeliveryTask = driver
				.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"DELIVERY\"]"));

		if (DeliveryTask.isDisplayed()) {
			System.out.println("Deliver task found");

//			WaitUtility.implicitwait(time_S);
			WebElement Order_details1 = driver.findElement(By.xpath("(//XCUIElementTypeStaticText[contains(@name, 'GGX')])"));
			System.out.println("Element processed");
//			WaitUtility.ExplicitWait(driver, Order_details1, time_L);
			Order_details1.click();
			System.out.println("Element clicked");
//			WaitUtility.implicitwait(time_S);
//			DeliveryDetails();
		}
//	} catch (Exception E) {
//		System.out.println(E);
//		System.out.println("Deliver task not found");
////		Taskselector();
//	}
	// TODO: handle exception
}

public void PickedDetails() {
//	try {
		WebElement PickedUp = driver
				.findElement(By.xpath("//XCUIElementTypeButton[contains(@name,'Picked up')]"));
		if (PickedUp.isDisplayed()) {
//			WaitUtility.ExplicitWait(driver, PickedUp, time_L);
			PickedUp.click();
			//			WaitUtility.implicitwait(time_S);
		}
//		Taskselector();
//	} catch (Exception e) {
//		System.out.println(e);
//		System.out.println("Pick-up done");
////		DeliveryDetails();
//	
//	}
}

public void DeliveryDetails() {
//	try {
		WebElement OtherDelivery = driver
				.findElement(By.xpath("//XCUIElementTypeButton[contains(@name,'Received by others')]"));
		System.out.println("Element found");
//		WaitUtility.implicitwait(time_S);
		System.out.println("element to be clicked");
//		WaitUtility.ExplicitWait(driver, OtherDelivery, time_L);
		OtherDelivery.click();
//		WaitUtility.implicitwait(time_S);
//		Taskselector();
//	} catch (Exception e) {
//		System.out.println(e);
////		Taskselector();
//		System.out.println("Order Delivered");
//	}
}


}
