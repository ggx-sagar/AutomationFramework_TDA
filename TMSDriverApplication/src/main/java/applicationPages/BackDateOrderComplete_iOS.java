package applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import basepage.BasePage;
import io.appium.java_client.AppiumDriver;

import propertyutility.PropertyUtility;


public class BackDateOrderComplete_iOS extends BasePage {
	
    public BackDateOrderComplete_iOS(AppiumDriver driver)
    {
            this.driver = driver;
//            PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        
    }
	String wait_L = PropertyUtility.getreaddata("TIME_L");
	int time_L = Integer.parseInt(wait_L);
	String wait_S = PropertyUtility.getreaddata("TIME_S");
	int time_S = Integer.parseInt(wait_S);

	public void Today() throws InterruptedException {
		WebElement Clickontoday = driver
				.findElement(By.xpath("//XCUIElementTypeImage[contains(@name,'Today')]"));

		if (Clickontoday.isDisplayed()) {
			Clickontoday.click();
			Thread.sleep(time_S);
		}
	}
	public void Clickondate() throws InterruptedException {
		WebElement Clickondate = driver
				.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Monday, July 15, 2024']"));
	if (Clickondate.isDisplayed()) {
		Clickondate.click();
			Thread.sleep(time_S);
		} 
	}

}
