package applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;


import basepage.BasePage;

import io.appium.java_client.AppiumDriver;

import propertyutility.PropertyUtility;

public class Exception_Android extends BasePage {
    public Exception_Android(AppiumDriver driver)
    {
            this.driver = driver;
//            PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        
    }
	
	
	String wait_L = PropertyUtility.getreaddata("TIME_L");
	int time_L = Integer.parseInt(wait_L);
	String wait_S = PropertyUtility.getreaddata("TIME_S");
	int time_S = Integer.parseInt(wait_S);

	public void Taskselector_exception() {
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
//			Delivertask();
		}
	}
	public void Select_order() {
		try {
			WebElement PickupTask = driver.findElement(By.xpath("//android.view.View[@content-desc=\"PICK-UP\"]"));
			if (PickupTask.isDisplayed()) {
				System.out.println("Pickup task found");
				Thread.sleep(time_S);
				WebElement Order_details_Exception = driver.findElement(By.xpath(
						"//android.view.View[contains(@content-desc,'GGX')]"));
				Thread.sleep(time_S);
				Order_details_Exception.click();
				Thread.sleep(time_S);
				tryagain();
			}}
		catch (Exception e) {
			System.out.println("Pickup task not found");
//			Delivertask();
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
				Thread.sleep(time_S);
				Order_details1.click();
				System.out.println("Element clicked");
				Thread.sleep(time_L);
//				DeliveryDetails();
			}
		} catch (Exception E) {
			System.out.println("Deliver task not found");
			Taskselector_exception();
		}
		// TODO: handle exception
	}

	public void PickedDetails() {
		try {
//			ApplicationUtility.scrollTillElement();
			WebElement PickedUpException = driver
					.findElement(By.xpath("//android.widget.Button[@content-desc=\"Report exception\"]"));
			if (PickedUpException.isDisplayed()) {
				System.out.println("Exception Button found");
				Thread.sleep(time_L);
				PickedUpException.click();
				Thread.sleep(time_L);

			}
//			selectexception();
			System.out.println(" exception block movement");
		} catch (Exception e) {
			System.out.println("Pick-up Exception done");
			DeliveryDetails();
		}
	}

	public void selectexception() {
		try {
			WebElement Select_Exception = driver.findElement(By.xpath("//android.widget.RadioButton[\"0\"]"));
			if (Select_Exception.isDisplayed()) {
				Thread.sleep(time_S);
				Select_Exception.click();
				Thread.sleep(time_S);
				WebElement Select_Ok = driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"OK\"]"));
				Select_Ok.click();
				Thread.sleep(time_S);
//				Exception_block();
			

			}
			
		} catch (Exception e) {
			System.out.println("Select Exception done");
			DeliveryDetails();
		}
	}

	public void Exception_block() {
		try {
			WebElement Exception_block_click = driver.findElement(By.xpath("//android.widget.ImageView[contains(@content-desc,'Exception')]"));
			if (Exception_block_click.isDisplayed()) {
				Thread.sleep(time_S);
				Exception_block_click.click();
				Thread.sleep(time_S);
//				Select_order();
//				Thread.sleep(time_S);
			    

			}
			
		} catch (Exception e) {
			System.out.println("Select Exception done");
			DeliveryDetails();
		}
	}
	public void tryagain() {
		try {
//			ApplicationUtility.scrollTillElement();
			WebElement Tryagain = driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Try again now\"]"));
			if (Tryagain.isDisplayed()) {
				Thread.sleep(time_S);
				Tryagain.click();
//				Thread.sleep(time_L);
				Picked_Details();
				Thread.sleep(time_S);
//				TO_DO();
			

			}
			
		} catch (Exception e) {
			System.out.println("Select Exception done");
			DeliveryDetails();}
		
	}
		
	
	public void DeliveryDetails() {
		try {
//			ApplicationUtility.scrollTillElement();
			WebElement Delivery = driver
					.findElement(By.xpath("//android.widget.Button[@content-desc=\"Delivered\"]"));
			System.out.println("Element found");
			Thread.sleep(time_L);
			System.out.println("element to be clicked");
			Delivery.click();
			Thread.sleep(time_L);
	//		Taskselector_exception();
		} catch (Exception e) {
//			Taskselector();
			System.out.println("Order Delivered");
		}
	}
	public void TO_DO() {
		try {
			WebElement TODO = driver
					.findElement(By.xpath("//android.widget.ImageView[contains(@content-desc,'To Do')]"));
			System.out.println("TO DO Element found");
			Thread.sleep(time_L);
			System.out.println("element to be clicked");
			TODO.click();
			Thread.sleep(time_L);
//			Delivertask();
		} catch (Exception e) {
//			Taskselector();
			System.out.println("Order Delivered");
		}
	}
	public void Picked_Details() {
		try {
//			ApplicationUtility.scrollTillElement();
			WebElement PickedUp = driver
					.findElement(By.xpath("//android.widget.Button[@content-desc=\"Picked up\"]"));
			if (PickedUp.isDisplayed()) {
				Thread.sleep(time_S);
				PickedUp.click();
				Thread.sleep(time_L);
			}
			
		} catch (Exception e) {
			System.out.println("Pick-up done");
			DeliveryDetails();
		}
	}

}
