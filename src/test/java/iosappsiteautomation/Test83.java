package iosappsiteautomation;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


import com.google.common.collect.ImmutableMap;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Test83 
{
	public static void main(String[] args) throws Exception
	{
		//Start Appium server programmatically
		AppiumServiceBuilder sb=new AppiumServiceBuilder();
		sb.usingAnyFreePort();
        sb.usingDriverExecutable(new File("/usr/local/bin/node"));
		sb.withAppiumJS(new File("/usr/local/bin/appium"));
		HashMap<String,String> ev=new HashMap();
		ev.put("PATH","/usr/local/bin:"+System.getenv("PATH"));
		sb.withEnvironment(ev);
		AppiumDriverLocalService as=AppiumDriverLocalService.buildService(sb);
		as.start();
		//Provide capabilities related to IOS Simulator and Home Screen
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability("automationName","XCUITest");
		dc.setCapability("platformName","iOS");
		dc.setCapability("platformVersion","13.5");
		dc.setCapability("deviceName","iPhone 8");
		dc.setCapability("app","com.apple.springboard");
        dc.setCapability("autoLaunch",false);        
		//Declare driver object to launch app via appium server
		IOSDriver driver=new IOSDriver(as.getUrl(),dc);
		Thread.sleep(5000);
		try
		{
			//close if any other app was opened previously
			driver.executeScript("mobile:pressButton",ImmutableMap.of("name","home"));
			Thread.sleep(5000);
			//Goto home
			driver.executeScript("mobile:pressButton",ImmutableMap.of("name","home"));
			Thread.sleep(5000);
			//Collect screens on home screen
			List<WebElement> screens=driver.findElements(By.xpath("//*[@name='Home screen icons']/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeIcon"));
			int count=screens.size();
			System.out.println("No: of pages on home screen is "+count);
			//Goto each page from first page and count no: of apps
			int totalappscount=0;
			for(int i=0;i<count;i++)
			{
				int screenappscount=screens.get(i).findElements(By.xpath("child::XCUIElementTypeIcon")).size();
				totalappscount=totalappscount+screenappscount;
				HashMap<String,Object> hm=new HashMap<String,Object>();
				hm.put("direction","left");
				driver.executeScript("mobile:swipe",hm);
			}
			System.out.println("No: of apps on home screen is "+totalappscount);
			//Goto home and click on Contacts app icon
			driver.executeScript("mobile:pressButton",ImmutableMap.of("name","home"));
			Thread.sleep(5000);
			driver.executeScript("mobile:pressButton",ImmutableMap.of("name","home"));
			Thread.sleep(5000);
			for(int i=0;i<count;i++)
			{
				MobileElement e=(MobileElement) driver.findElement(By.xpath("//XCUIElementTypeIcon[@name='Contacts']"));
				if(e.isDisplayed())
				{
					e.click();
					break;
				}
				else
				{
					HashMap<String,Object> hm=new HashMap<String,Object>();
					hm.put("direction","left");
					driver.executeScript("mobile:swipe",hm);
				}
			}
			Thread.sleep(10000);
			driver.terminateApp("com.apple.MobileAddressBook");
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//No need to use closeApp() method 
		//because springboard app(Home screen) cannot terminate
		//Stop Appium server
		as.stop();
	}
}