package mobilegestures;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class MGUsingSeleniumVerticalHorizontal 
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
					PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
					Sequence swipe=new Sequence(finger,1);
					Interaction i1=finger.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),width/2,(int) (height*0.8));
					swipe.addAction(i1);
					Interaction i2=finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
					swipe.addAction(i2);
					Interaction i3=finger.createPointerMove(Duration.ofMillis(200),PointerInput.Origin.viewport(),width/2,(int) (height*0.3));
					swipe.addAction(i3);
					Interaction i4=finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
					swipe.addAction(i4);
					driver.perform(Arrays.asList(swipe));
				}
			}
			
			//Get element dimensions
			MobileElement ele=(MobileElement) driver.findElement(By.xpath("//*[@text='"+contactname+"']/parent::*/parent::*"));
			Point source=ele.getCenter();
			int w=ele.getSize().getWidth();
			int h=ele.getSize().getHeight();
			int x=source.x;
			int y=source.y;
			//Horizontal Swipe Logic
			PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
			Sequence swipe=new Sequence(finger,1);
			Interaction i1=finger.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),(int) (x-w/2*0.8),y);
			swipe.addAction(i1);
			Interaction i2=finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
			swipe.addAction(i2);
			Interaction i3=finger.createPointerMove(Duration.ofMillis(1000),PointerInput.Origin.viewport(),(int) (x+w/2*0.8),y);
			swipe.addAction(i3);
			Interaction i4=finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
			swipe.addAction(i4);
			driver.perform(Arrays.asList(swipe));
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
