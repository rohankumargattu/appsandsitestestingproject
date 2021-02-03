package mobilegestures;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class MGUsingAppiumHorizontalSwipeSlidersParallel1 
{
	public static void main(String[] args) throws Exception
	{
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ce081718334a5b0b05");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.0.0");
		dc.setCapability("appPackage","com.vodqareactnative");
		dc.setCapability("appActivity","com.vodqareactnative.MainActivity");
		//Start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723\"");
		//Get address of appium Server
		URL u=new URL("http://0.0.0.0:4723/wd/hub");
		//Create driver object
		AndroidDriver driver;
		while(2>1)
		{
			try
			{
				driver=new AndroidDriver(u,dc);
				break;
			}
			catch(Exception ex)
			{
			}
		}
		//Automation
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='LOG IN']")));
			driver.findElement(By.xpath("//*[@text='LOG IN']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='Slider']")));
			driver.findElement(By.xpath("//*[@text='Slider']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Back']")));
			WebElement e1=driver.findElement(By.xpath("//*[@content-desc='slider']"));
			WebElement e2=driver.findElement(By.xpath("//*[@content-desc='slider1']"));
			int x=driver.manage().window().getSize().getWidth();
			System.out.println(x);
			WaitOptions wo=new WaitOptions();
			wo.withDuration(Duration.ofSeconds(5));
			TouchAction ta1=new TouchAction(driver);
			ta1.press(ElementOption.element(e1)).waitAction(wo).moveTo(ElementOption.point(800,0)).release();
			TouchAction ta2=new TouchAction(driver);
			ta2.press(ElementOption.element(e2)).waitAction(wo).moveTo(ElementOption.point(800,0)).release();
			Thread.sleep(5000);
			MultiTouchAction ma=new MultiTouchAction(driver);
			ma.add(ta1).add(ta2).perform();
			Thread.sleep(5000);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		//Close app
		driver.closeApp();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
