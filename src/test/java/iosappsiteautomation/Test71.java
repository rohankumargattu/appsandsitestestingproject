package iosappsiteautomation;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.Setting;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Test71 
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
		//Provide capabilities related to Simulator and App
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,"XCUITest");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME,"iOS");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,"13.5");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME,"iPhone 8");
		dc.setCapability(MobileCapabilityType.APP,"com.apple.DocumentsApp");
		HashMap<String, String> cm=new HashMap<>();
		cm.put("ai","test-ai-classifier");
		dc.setCapability("customFindModules",cm);
		dc.setCapability("testaiFindMode","object_detection");
		dc.setCapability("testaiObjectDetectionThreshold","0.9");
		dc.setCapability("shouldUseCompactResponses",false);
	    //Declare driver object to launch app via appium server
		IOSDriver driver=new IOSDriver(as.getUrl(),dc);
		Thread.sleep(10000);
		//Automation
		try
		{
			driver.setSetting(Setting.CHECK_IMAGE_ELEMENT_STALENESS, false);
			//Use regular locator 
			By browse=MobileBy.AccessibilityId("Browse");
			driver.findElement(browse).click();
			Thread.sleep(10000);
			//Use ai locator
			driver.findElementByCustom("clock").click();
		}
		catch(Exception ex)
		{
			//Stop Appium server
			as.stop();
		}
	}
}