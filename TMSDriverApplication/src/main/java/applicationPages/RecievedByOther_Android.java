package applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import basepage.BasePage;
import io.appium.java_client.AppiumDriver;

import propertyutility.PropertyUtility;

public class RecievedByOther_Android extends BasePage {
	
	
    public RecievedByOther_Android(AppiumDriver driver)
    {
            this.driver = driver;
//            PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        
    }
	
	String wait_L = PropertyUtility.getreaddata("TIME_L");
	int time_L = Integer.parseInt(wait_L);
	String wait_S = PropertyUtility.getreaddata("TIME_S");
	int time_S = Integer.parseInt(wait_S);

	public void Taskselector() {
		try {
			WebElement PickupTask = driver.findElement(By.xpath("//android.view.View[@content-desc=\"PICK-UP\"]"));
			if (PickupTask.isDisplayed()) {
				System.out.println("Pickup task found");
				Thread.sleep(time_S);
				WebElement Order_details = driver.findElement(By.xpath(
						"//android.widget.ScrollView/android.view.View[1]/android.view.View/android.view.View[4]"));
				Thread.sleep(time_S);
				Order_details.click();
				Thread.sleep(time_S);
//				PickedDetails();

			}
		} catch (Exception e) {
			System.out.println("Pickup task not found");
			Delivertask();
		}
	}

	public void Delivertask() {
		try {
			WebElement DeliveryTask = driver
					.findElement(By.xpath("//android.view.View[@content-desc=\"DELIVERY\"]"));

			if (DeliveryTask.isDisplayed()) {
				System.out.println("Deliver task found");

				Thread.sleep(time_S);
				WebElement Order_details1 = driver.findElement(By.xpath("//android.view.View[4]"));
				System.out.println("Element processed");
				Thread.sleep(time_L);
				Order_details1.click();
				System.out.println("Element clicked");
				Thread.sleep(time_L);
				DeliveryDetails();
			}
		} catch (Exception E) {
			System.out.println("Deliver task not found");
			Taskselector();
		}
		// TODO: handle exception
	}

	public void PickedDetails() {
		try {
//			ApplicationUtility.scrollTillElement();
			WebElement PickedUp = driver
					.findElement(By.xpath("//android.widget.Button[@content-desc=\"Picked up\"]"));
			if (PickedUp.isDisplayed()) {
				Thread.sleep(time_S);
				PickedUp.click();
				Thread.sleep(time_S);
			}
			Taskselector();
		} catch (Exception e) {
			System.out.println("Pick-up done");
			DeliveryDetails();
		}
	}

	public void DeliveryDetails() {
		try {
//			ApplicationUtility.scrollTillElement();
			WebElement OtherDelivery = driver
					.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Received by others\"]"));
			System.out.println("Element found");
			Thread.sleep(time_L);
			System.out.println("element to be clicked");
			OtherDelivery.click();
			Thread.sleep(time_L);
//			Taskselector();
		} catch (Exception e) {
//			Taskselector();
			System.out.println("Order Delivered");
		}
	}

}
