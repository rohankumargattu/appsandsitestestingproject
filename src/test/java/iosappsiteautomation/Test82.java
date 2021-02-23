package iosappsiteautomation;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.Setting;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Test82 
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
		//Provide capabilities related to Simulator and Hybrid App
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability("automationName","XCUITest");
		dc.setCapability("platformName","iOS");
		dc.setCapability("platformVersion","13.5");
		dc.setCapability("deviceName","iPhone 8"); 
		dc.setCapability("app","/Users/nrstt/Library/Developer/Xcode/DerivedData/UICatalog-ddxqkpltymszsrgjlakalnwmsenh/Build/Products/Debug-iphonesimulator/UICatalog.app");
		//Declare driver object to launch app via appium server
		IOSDriver driver=new IOSDriver(as.getUrl(),dc);
		Thread.sleep(10000);
		//Automation
		try
		{
			//Get current contexts
			Set<String> cs1=driver.getContextHandles();	
			System.out.println(cs1);
			//Click on webview link
			driver.findElement(By.name("Web View")).click();
			Thread.sleep(10000);
			//Get current contexts
			Set<String> cs2=driver.getContextHandles();	
			System.out.println(cs2);
			ArrayList<String> l=new ArrayList<String>(cs2);
			driver.context(l.get(1)); //shift to WebView context
			driver.findElement(By.linkText("Continue")).click();
			Thread.sleep(10000);
			driver.context(l.get(0)); //Back to NATIVE_APP context
			driver.findElement(By.xpath("//XCUIElementTypeButton[@name='UICatalog']")).click();
			Thread.sleep(10000); 
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			//close app
			driver.closeApp();
			//Stop Appium server
			as.stop();
		}
	}
}