package applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import ScrollingUtility.Scrolling;
import basepage.BasePage;
import io.appium.java_client.AppiumDriver;

import propertyutility.PropertyUtility;


public class CompleteAll_iOS extends BasePage {
	
    public CompleteAll_iOS(AppiumDriver driver)
    {
            this.driver = driver;
//            PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        
    }
	String wait_L = PropertyUtility.getreaddata("TIME_L");
	int time_L = Integer.parseInt(wait_L);
	String wait_S = PropertyUtility.getreaddata("TIME_S");
	int time_S = Integer.parseInt(wait_S);

//	IOS Done All------------------------------------------------------------------------

	public void Pick_Up_button_iOS() {
//		try {

		WebElement PickupAll = driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Pick up all\"]"));
//		WaitUtility.ExplicitWait(driver, PickupAll, time_L);
		Scrolling.sc();

		if (PickupAll.isDisplayed()) {
		System.out.println("PickUp found");

		PickupAll.click();
			WebElement PickupAllConfirmation = driver
					.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Pick up all\"]"));
//			WaitUtility.ExplicitWait(driver, PickupAllConfirmation, time_L);
			PickupAllConfirmation.click();
//			WaitUtility.implicitwait(time_S);

		}

//		} catch (Exception e) {
//			System.out.println("No PickUp Found");
//			// TODO: handle exception
//		}

	}

	public void Deliver_button_iOS() {
//		try {

		WebElement DeliverAll = driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Deliver all\"]"));
//		WaitUtility.ExplicitWait(driver, DeliverAll, time_L);
		if (DeliverAll.isDisplayed()) {
			System.out.println("Delivery found");
			DeliverAll.click();

			WebElement DeliverAllConfirmation = driver
					.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Deliver all\"]"));
//			WaitUtility.ExplicitWait(driver, DeliverAllConfirmation, time_L);
			DeliverAllConfirmation.click();
		}

//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("No Order Found");
//		}
//	}

	}
}
