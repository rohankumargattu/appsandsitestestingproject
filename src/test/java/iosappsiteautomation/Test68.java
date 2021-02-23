package iosappsiteautomation;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.serverevents.ServerEvents;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Test68 
{
	public static void main(String[] args) throws Exception
	{
		//Start Appium server programmatically
		AppiumServiceBuilder sb=new AppiumServiceBuilder();
		sb.usingAnyFreePort();
        sb.usingDriverExecutable(new File("/usr/local/bin/node"));
		sb.withAppiumJS(new File("/usr/local/bin/appium"));
		//For IOS only
		HashMap<String,String> ev=new HashMap<String,String>();
		ev.put("PATH","/usr/local/bin:"+System.getenv("PATH"));
		sb.withEnvironment(ev);
		//Start server
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
		Thread.sleep(10000);
		//Close app
		driver.closeApp();
		//Save appium server
		ServerEvents events=driver.getEvents();
		File f=new File("/users/nrstt/desktop/log123.json");
		Path p=f.toPath();
		events.save(p);
		//Stop Appium server
		as.stop();
	}
}