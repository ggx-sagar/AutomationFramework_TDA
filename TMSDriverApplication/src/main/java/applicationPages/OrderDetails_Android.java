package applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import basepage.BasePage;
import io.appium.java_client.AppiumDriver;
import propertyutility.PropertyUtility;


public class OrderDetails_Android extends BasePage {
	
    public OrderDetails_Android(AppiumDriver driver)
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
				WebElement Order_details = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.View[1]/android.view.View/android.view.View[4]"));
//				WaitUtility.ExplicitWait(driver, Order_details, time_L);
				Order_details.click();
//				PickedDetails();

			}
		} catch (Exception e) {
			System.out.println("Pickup task not found");
			Delivertask();
		}
	}

	public void Delivertask() {
		try {
//		x	ApplicationUtility.scrollTillElement();
			Thread.sleep(time_S);
			WebElement DeliveryTask = driver
					.findElement(By.xpath("//android.view.View[@content-desc=\"DELIVERY\"]"));

			if (DeliveryTask.isDisplayed()) {
				System.out.println("Deliver task found");

//				WaitUtility.ExplicitWait(driver, DeliveryTask, time_L);
				WebElement Order_details1 = driver.findElement(By.xpath("//android.view.View[4]"));
				System.out.println("Element processed");
//				WaitUtility.ExplicitWait(driver, Order_details1, time_L);
				Order_details1.click();
				System.out.println("Element clicked");
	//			DeliveryDetails();
			}
		} catch (Exception E) {
			System.out.println("Deliver task not found");
	//		Taskselector();
		}
		// TODO: handle exception
	}

	public void PickedDetails() {
		try {
//			ApplicationUtility.scrollTillElement();
			WebElement PickedUp = driver
					.findElement(By.xpath("//android.widget.Button[@content-desc=\"Picked up\"]"));

			if (PickedUp.isDisplayed()) {
//				WaitUtility.ExplicitWait(driver, PickedUp, time_L);
				PickedUp.click();
			}
//			Taskselector();
		} catch (Exception e) {
			System.out.println("Pick-up Not done");
			System.out.println(e);
//		DeliveryDetails();
		}
	}

	public void DeliveryDetails() {
		try {
			Thread.sleep(time_S);
//			ApplicationUtility.scrollTillElement();
			
			WebElement Delivery = driver
					.findElement(By.xpath("//android.widget.Button[@content-desc=\"Delivered\"]"));
//			TOUCHACTION();
//			Scrolling.sc();
			System.out.println("Element found");
//			WaitUtility.ExplicitWait(driver, Delivery, time_L);
			System.out.println("element to be clicked");
			Delivery.click();
//			WaitUtility.implicitwait(time_S);
//		Taskselector();
		} catch (Exception e) {
//			Taskselector();
			System.out.println("Not Found");
		}
	}

}
