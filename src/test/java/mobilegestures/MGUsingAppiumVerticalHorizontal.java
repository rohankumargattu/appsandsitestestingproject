package mobilegestures;

import java.net.URL;
import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class MGUsingAppiumVerticalHorizontal 
{
	public static void main(String[] args) throws Exception
	{
		//Enter contact name to call
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter contact name to call as it is you saved in ur contacts list");
		String contactname=sc.nextLine();
		sc.close();
		//Start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//Get address of appium Server
		URL u=new URL("http://127.0.0.1:4723/wd/hub");
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ce081718334a5b0b05");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.0.0");
		dc.setCapability("appPackage","com.samsung.android.contacts");
		dc.setCapability("appActivity","com.android.contacts.activities.PeopleActivity");
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
		//App Automation
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='Create contact']")));
			TouchAction ta=new TouchAction(driver);
			WaitOptions wo=new WaitOptions();
			wo.withDuration(Duration.ofMillis(3000));
			while(2>1)
			{
				try
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='"+contactname+"']")));
					break;
				}
				catch(Exception exe)
				{
					//Get device dimensions
					int width=driver.manage().window().getSize().getWidth();
					int height=driver.manage().window().getSize().getHeight();
					//Vertical Swipe Logic
					ta.press(ElementOption.point(width/2,(int) (height*0.8))).waitAction(wo).moveTo(ElementOption.point(width/2,(int) (height*0.2))).release().perform();
				}
			}
			
			//Get element dimensions
			MobileElement ele=(MobileElement) driver.findElement(By.xpath("//*[@text='"+contactname+"']/parent::*/parent::*"));
			Point source=ele.getCenter();
			int w=ele.getSize().getWidth();
			int h=ele.getSize().getHeight();
			int x=source.x;
			int y=source.y;
			//Horizontal Swipe Logic(to call)
			ta.press(ElementOption.point((int) (x-w/2*0.8),y)).waitAction(wo).moveTo(ElementOption.point((int) (x+w/2*0.8),y)).release().perform();
			
			Thread.sleep(15000);
			driver.findElementByAndroidUIAutomator("new UiSelector().description(\"End call\")").click();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		Thread.sleep(5000);
		//Close app
		driver.closeApp();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
