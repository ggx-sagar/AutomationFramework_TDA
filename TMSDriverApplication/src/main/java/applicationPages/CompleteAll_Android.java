package applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import basepage.BasePage;

import io.appium.java_client.AppiumDriver;
import propertyutility.PropertyUtility;


public class CompleteAll_Android extends BasePage {
	
    public CompleteAll_Android(AppiumDriver driver)
    {
            this.driver = driver;
//            PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        
    }
	
	String wait_L = PropertyUtility.getreaddata("TIME_L");
	int time_L = Integer.parseInt(wait_L);
	String wait_S = PropertyUtility.getreaddata("TIME_S");
	int time_S = Integer.parseInt(wait_S);

	
//	Android done all------------------------------------------------------------
	
	
	public void AndroidPick_Up_button() throws InterruptedException {
		try {
//			WaitUtility.implicitwait(time_L);
			WebElement PickupAll = driver
					.findElement(By.xpath("//android.widget.Button[@content-desc=\"Pick up all\"]"));
			if (PickupAll.isDisplayed()) {
//				WaitUtility.ExplicitWait(driver, PickupAll, time_L);
				System.out.println("PickUp found");
				PickupAll.click();
				Thread.sleep(time_L);
				WebElement PickupAllConfirmation = driver
						.findElement(By.xpath("//android.widget.Button[@content-desc=\"Pick up all\"]"));
				Thread.sleep(time_L);

//				WaitUtility.ExplicitWait(driver, PickupAll, time_L);
				PickupAllConfirmation.click();
//				WaitUtility.implicitwait(time_S);
//				AndroidDeliver_button();
			}

		} catch (Exception e) {
			System.out.println("PickUp NOT found");
			System.out.println(e);
			WebElement PickupAllConfirmation = driver
					.findElement(By.xpath("//android.widget.Button[@content-desc=\"Pick up all\"]"));
			Thread.sleep(time_L);
			PickupAllConfirmation.click();
			
			// TODO: handle exception
			AndroidDeliver_button();
		}

	}

	public void AndroidDeliver_button() {
		try {
			WebElement DeliverAll = driver
					.findElement(By.xpath("//android.widget.Button[@content-desc=\"Deliver all\"]"));
//			WaitUtility.ExplicitWait(driver, DeliverAll, time_L);
			System.out.println("Delivery found");
			DeliverAll.click();

//			WaitUtility.implicitwait(time_S);
			WebElement DeliverAllConfirmation = driver
					.findElement(By.xpath("//android.widget.Button[@content-desc=\"Deliver all\"]"));
//			WaitUtility.ExplicitWait(driver, DeliverAllConfirmation, time_L);
			DeliverAllConfirmation.click();

		} catch (Exception e) {
			System.out.println("No Order found");
			System.out.println(e);
			// TODO: handle exception
//			AndroidPick_Up_button();
		}
	}
	
	


}
