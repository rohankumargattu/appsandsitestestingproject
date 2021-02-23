package iosappsiteautomation;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Test85 
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
	    String PHOTOS_BUNDLE_ID="com.apple.mobileslideshow"; //Photos built-in app's bundleId
	    String BUNDLE_ID="io.cloudgrey.the-app"; //AUT's bundleId
		//Provide capabilities related to Simulator and AUT App
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability("automationName","XCUITest");
		dc.setCapability("platformName","iOS");
		dc.setCapability("platformVersion","13.5");
		dc.setCapability("deviceName","iPhone 8"); 
		dc.setCapability("app",APP);
		//Declare driver object to launch app via appium server
		IOSDriver driver=new IOSDriver(as.getUrl(),dc);
		Thread.sleep(10000);
		//Automation
		try
		{
			//Now launch Photos app
			HashMap<String,Object> hm=new HashMap<String,Object>();
            hm.put("bundleId",PHOTOS_BUNDLE_ID);
            driver.executeScript("mobile:launchApp",hm);
            Thread.sleep(1000);
            //Now back to our AUT
            hm.put("bundleId",BUNDLE_ID);
            driver.executeScript("mobile:activateApp",hm);
            Thread.sleep(1000);
            //Now reactivate the Photos app and close that app
            hm.put("bundleId",PHOTOS_BUNDLE_ID);
            driver.executeScript("mobile:activateApp",hm);
            Thread.sleep(1000);
            driver.executeScript("mobile:terminateApp",hm);
            Thread.sleep(5000);
            //Now reactivate our AUT
            hm.put("bundleId",BUNDLE_ID);
            driver.executeScript("mobile:activateApp",hm);
            Thread.sleep(1000);
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
