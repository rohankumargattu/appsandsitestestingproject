package iosappsiteautomation;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Test74 
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
		//Provide capabilities related to IOS Simulator and App
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,"XCUITest");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME,"iOS");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,"13.5");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME,"iPhone 8");
		dc.setCapability(MobileCapabilityType.APP,"/Users/nrstt/batch249/VodQAReactNative.app");
		//Declare driver object to launch app via appium server
		IOSDriver driver=new IOSDriver(as.getUrl(),dc);
		try
		{
			Thread.sleep(5000);
			driver.findElementByAccessibilityId("login").click();
			Thread.sleep(5000);
			driver.findElementByAccessibilityId("slider1").click();
			Thread.sleep(5000);
			WebElement e=driver.findElementByAccessibilityId("slider");
			//Get location of slider
			Rectangle rect=e.getRect();
		    Point start=new Point(rect.x+15,rect.y+20);
		    Point end=new Point(rect.x+rect.width-15,rect.y+20);
		    System.out.println(start.x+" "+start.y);
		    System.out.println(end.x+" "+end.y);
		    //Swipe to right(using IOS specific automation code, special for reactnative app)
		    HashMap<String, Object> hp1=new HashMap<>();
		    hp1.put("duration",1.5);
		    hp1.put("fromX",start.x);
		    hp1.put("fromY",start.y);
		    hp1.put("toX",end.x);
		    hp1.put("toY",end.y);
		    driver.executeScript("mobile:dragFromToForDuration",hp1);
		    Thread.sleep(10000);
			//swipe to left(using IOS specific automation code, special for reactnative app)
		    HashMap<String, Object> hp2=new HashMap<>();
		    hp2.put("duration",1.5);
		    hp2.put("fromX",end.x);
		    hp2.put("fromY",end.y);
		    hp2.put("toX",start.x);
		    hp2.put("toY",start.y);
		    driver.executeScript("mobile:dragFromToForDuration",hp2);
			Thread.sleep(5000);
			long s1=System.currentTimeMillis();
			driver.findElementByXPath("//*[@label='Back']").click(); //no AccessibilityId for it
			long e1=System.currentTimeMillis();
			System.out.println(e1-s1);
			Thread.sleep(5000);
			long s2=System.currentTimeMillis();
			driver.findElementByIosNsPredicate("label=='Back'").click(); 
			long e2=System.currentTimeMillis();
			System.out.println(e2-s2);
			Thread.sleep(5000);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//Close app
		driver.closeApp();
		//Stop Appium server
		as.stop();
	}
}