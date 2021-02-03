package mobilegestures;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class MGUsingSeleniumHorizontalSwipePlayStore 
{
	public static void main(String[] args) throws Exception
	{
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
		dc.setCapability("appPackage","com.android.vending");
		dc.setCapability("appActivity","com.android.vending.AssetBrowserActivity");
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
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Search for apps & games\")")));
			List<MobileElement> l=driver.findElements(By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='com.android.vending:id/0_resource_name_obfuscated']"));
			System.out.println("No of View elements are: "+l.size());
			for(int i=0;i<l.size();i++)
			{
				while(2>1)
				{
					List<MobileElement> cl=l.get(i).findElements(By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='com.android.vending:id/0_resource_name_obfuscated']/child::*"));
					//Swipe right to left
					if(cl.get(cl.size()-1).getAttribute("className").equals("android.view.View"))
					{
						break;
					}
					else
					{
						MobileElement ele=l.get(i);
						Point source=ele.getCenter();
						int x=source.x;
						int y=source.y;
						int w=ele.getSize().getWidth();
						int h=ele.getSize().getHeight();
						PointerInput finger1=new PointerInput(PointerInput.Kind.TOUCH,"finger1");
						Sequence swipe1=new Sequence(finger1,1);
						Interaction i1=finger1.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),(int) (x+w/2*0.5),y);
						swipe1.addAction(i1);
						Interaction i2=finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
						swipe1.addAction(i2);
						Interaction i3=finger1.createPointerMove(Duration.ofMillis(200),PointerInput.Origin.viewport(),(int) (x-w/2*0.5),y);
						swipe1.addAction(i3);
						Interaction i4=finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
						swipe1.addAction(i4);
						driver.perform(Arrays.asList(swipe1));
					}
				}
				while(2>1)
				{
					List<MobileElement> cl=l.get(i).findElements(By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='com.android.vending:id/0_resource_name_obfuscated']/child::*"));
					//Swipe left to right
					if(cl.get(0).getAttribute("className").equals("android.view.View"))
					{
						break;
					}
					else
					{
						MobileElement ele=l.get(i);
						Point source=ele.getCenter();
						int x=source.x;
						int y=source.y;
						int w=ele.getSize().getWidth();
						int h=ele.getSize().getHeight();
						PointerInput finger1=new PointerInput(PointerInput.Kind.TOUCH,"finger1");
						Sequence swipe1=new Sequence(finger1,1);
						Interaction i1=finger1.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),(int) (x-w/2*0.5),y);
						swipe1.addAction(i1);
						Interaction i2=finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
						swipe1.addAction(i2);
						Interaction i3=finger1.createPointerMove(Duration.ofMillis(200),PointerInput.Origin.viewport(),(int) (x+w/2*0.5),y);
						swipe1.addAction(i3);
						Interaction i4=finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
						swipe1.addAction(i4);
						driver.perform(Arrays.asList(swipe1));
					}
				}
			}
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
