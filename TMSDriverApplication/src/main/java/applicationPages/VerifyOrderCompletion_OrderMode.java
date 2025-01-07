package applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import basepage.BasePage;
import io.appium.java_client.AppiumDriver;
import propertyutility.PropertyUtility;


public class VerifyOrderCompletion_OrderMode extends BasePage {
	
    public VerifyOrderCompletion_OrderMode(AppiumDriver driver)
    {
            this.driver = driver;
//            PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        
    }

	String wait_L = PropertyUtility.getreaddata("TIME_L");
	int time_L = Integer.parseInt(wait_L);
	String wait_S = PropertyUtility.getreaddata("TIME_S");
	int time_S = Integer.parseInt(wait_S);
	String USERNAME = PropertyUtility.getreaddata("UserName");
	String PASSWORD = PropertyUtility.getreaddata("PassWord");
	
	public void Ordermode() {
		WebElement Ordermode= driver.findElement(By.xpath("//XCUIElementTypeOther/XCUIElementTypeButton[2]"));
		Ordermode.click();
//		WaitUtility.implicitwait(time_S);
		
		WebElement Order_Mode= driver.findElement(By.xpath("//XCUIElementTypeOther[contains(@name,'Order mode')]"));
		Order_Mode.click();
//		WaitUtility.implicitwait(time_S);
		
		WebElement Apply= driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Apply']"));
		Apply.click();
//		WaitUtility.implicitwait(time_S);
		

	}
	

}
