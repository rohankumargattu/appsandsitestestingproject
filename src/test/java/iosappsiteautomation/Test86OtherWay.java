package iosappsiteautomation;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Test86OtherWay 
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
		//Common objects
		String APP="https://github.com/cloudgrey-io/the-app/releases/download/v1.2.1/TheApp-v1.2.1.app.zip";
		//Provide capabilities related to Simulator and Hybrid App
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability("automationName","XCUITest");
		dc.setCapability("platformName","iOS");
		dc.setCapability("platformVersion","13.5");
		dc.setCapability("deviceName","iPhone 8"); 
		dc.setCapability("app",APP);
		//Declare driver object to launch app via appium server
		IOSDriver driver=new IOSDriver(as.getUrl(),dc);
		Thread.sleep(5000);
		//Automation
		try
		{
			driver.executeScript("mobile:terminateApp",ImmutableMap.of("bundleId","io.cloudgrey.the-app"));
			Thread.sleep(5000);
			//Launch Safari to open the deep link
			driver.executeScript("mobile:launchApp",ImmutableMap.of("bundleId","com.apple.mobilesafari"));
			Thread.sleep(5000);
			//Click on URL box to get Keyboard
			driver.findElement(MobileBy.AccessibilityId("URL")).click();
			Thread.sleep(5000);
			//Fill URL
			driver.findElement(MobileBy.AccessibilityId("URL")).sendKeys("theapp://login/alice/mypassword\uE007");
			Thread.sleep(5000);
			//Click Open in pop-up
			driver.findElement(MobileBy.AccessibilityId("Open")).click();
			Thread.sleep(5000);	
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			//Close app
			driver.closeApp();
			//Stop Appium server
			as.stop();
		}
	}
}
