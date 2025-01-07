package applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import basepage.BasePage;

import propertyutility.PropertyUtility;

public class BackDateOrderComplete_Android extends BasePage {
	String wait_L = PropertyUtility.getreaddata("TIME_L");
	int time_L = Integer.parseInt(wait_L);
	String wait_S = PropertyUtility.getreaddata("TIME_S");
	int time_S = Integer.parseInt(wait_S);

	public void Today() throws InterruptedException {
		WebElement Clickontoday = driver
				.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Today\"]"));
		if (Clickontoday.isDisplayed()) {
			Clickontoday.click();
			Thread.sleep(time_S);

		} else {
			Clickondate();
		}
	}
	public void Clickondate() throws InterruptedException {
		WebElement Clickondate = driver
				.findElement(By.xpath("//android.view.View[@content-desc=\"Wednesday, July 24, 2024\"]"));
		if (Clickondate.isDisplayed()) {
   		Clickondate.click();
			Thread.sleep(time_S);
		} else {

		}
//		//android.view.View[@content-desc="Wednesday, July 24, 2024"]
	}

}
