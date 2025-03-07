package applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ScrollingUtility.Scrolling;
import basepage.BasePage;
import io.appium.java_client.AppiumDriver;

import propertyutility.PropertyUtility;



public class OrderDetail_iOS extends BasePage {
	
    public OrderDetail_iOS(AppiumDriver driver)
    {
            this.driver = driver;
//            PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        
    }

	String wait_L = PropertyUtility.getreaddata("TIME_L");
	int time_L = Integer.parseInt(wait_L);
	String wait_S = PropertyUtility.getreaddata("TIME_S");
	int time_S = Integer.parseInt(wait_S);

	public void PickUpTask() throws InterruptedException {
		
//			try {
		WebElement PickupTask = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='PICK-UP']"));
		if (PickupTask.isDisplayed()) {
			System.out.println("Pickup task found");
//			WaitUtility.implicitwait(time_S);
			WebElement Order_details = driver
					.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name,'GGX')][1]"));
//			WaitUtility.ExplicitWait(driver, Order_details, time_L);
			Order_details.click();
			
			}
		}

	public void Delivertask() {
//			try {
		WebElement DeliveryTask = driver
				.findElement(By.xpath("//XCUIElementTypeStaticText[@name='DELIVERY']"));

		if (DeliveryTask.isDisplayed()) {
			System.out.println("Deliver task found");
//			WaitUtility.ExplicitWait(driver, DeliveryTask, time_L);
			WebElement Order_details1 = driver
					.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name,'GGX')][1]"));
			System.out.println("Element processed");
//			WaitUtility.ExplicitWait(driver, Order_details1, time_L);
			Order_details1.click();
			System.out.println("Element clicked");
			// DeliveryDetails();
		}
//			} catch (Exception E) {
//				System.out.println("Deliver task not found");
//				System.out.println(E);
//		//		Taskselector();
//			}
		// TODO: handle exception
	}

	public void PickedDetails() throws InterruptedException {
//			try {
		WebElement PickedUp = driver.findElement(By.xpath("//XCUIElementTypeButton[contains(@name,'Picked up')]"));
//		Scrolling.sc();
		if (PickedUp.isDisplayed()) {
//			WaitUtility.ExplicitWait(driver, PickedUp, time_L);
			PickedUp.click();
			Thread.sleep(time_S);
			WebElement Upload_SOP = driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Upload and submit']"));
			Upload_SOP.click();

		}
//				Taskselector();
//			} catch (Exception e) {
//				System.out.println(e);
//				System.out.println("Pick-up done");
////				DeliveryDetails();
//			}
	}

	public void DeliveryDetails() throws InterruptedException {
//			try {
		WebElement Delivery = driver.findElement(By.xpath("//XCUIElementTypeButton[contains(@name,'Delivered')]"));
//				ApplicationUtility.swipeUpTillElement(Delivery);
//		Scrolling.sc();
		System.out.println("Element found");
//		WaitUtility.ExplicitWait(driver, Delivery, time_L);
		System.out.println("element to be clicked");
		Delivery.click();
		Thread.sleep(time_S);
		WebElement Upload_SOP = driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Upload and submit']"));
		Upload_SOP.click();
//		WaitUtility.implicitwait(time_S);
//			Taskselector();
//			} catch (Exception e) {
////				Taskselector();
//				System.out.println("Order Delivered");
//			}
//		}

	}
}
