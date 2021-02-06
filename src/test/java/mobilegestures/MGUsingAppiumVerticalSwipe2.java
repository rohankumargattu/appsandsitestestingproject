package mobilegestures;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class MGUsingAppiumVerticalSwipe2
{
	public static void main(String[] args) throws Exception
	{
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ce081718334a5b0b05");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.0.0");
		dc.setCapability("appPackage","com.sec.android.app.clockpackage");
		dc.setCapability("appActivity","com.sec.android.app.clockpackage.ClockPackage");
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
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='WORLD CLOCK']")));
			driver.findElement(By.xpath("//*[@text='WORLD CLOCK']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='New Delhi']")));
			driver.findElement(By.xpath("//*[@text='New Delhi']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@text,'Search')]")));
			driver.findElement(By.xpath("//*[contains(@text,'Search')]")).click();
			if(driver.isKeyboardShown())
			{
				KeyEvent k=new KeyEvent(AndroidKey.BACK);
				driver.pressKey(k);
				Thread.sleep(2000);
			}
			TouchAction ta=new TouchAction(driver);
			WaitOptions wo=new WaitOptions();
			wo.withDuration(Duration.ofSeconds(5));
			//Swipe for Bottom to top
			int w=driver.manage().window().getSize().getWidth();
			int h=driver.manage().window().getSize().getHeight();
			int x1=(int) w/2;
			int y1=(int) (h*0.8);
			int x2=(int) w/2;
			int y2=(int) (h*0.4);
			while(2>1)
			{
				try
				{
					if(driver.findElement(By.xpath("//*[contains(@text,'Bangalore')]")).isDisplayed())
					{
						System.out.println("Bangalore is visible");
						break;
					}
				}
				catch(Exception exe)
				{
					ta.press(ElementOption.point(x1,y1)).waitAction(wo).moveTo(ElementOption.point(x2,y2)).release().perform();
				}
			}
			
			//Swipe for top to bottom
			while(2>1)
			{
				try
				{
					if(driver.findElement(By.xpath("//*[contains(@text,'Abu Dhabi')]")).isDisplayed())
					{
						System.out.println("Abu Dhabi is visible");
						break;
					}
				}
				catch(Exception exe)
				{
					ta.press(ElementOption.point(x2,y2)).waitAction(wo).moveTo(ElementOption.point(x1,y1)).release().perform();
				}
			}
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
