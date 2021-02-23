package iosappsiteautomation;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Test87 
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
		//Provide capabilities related to IOS Simulator and any App
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability("automationName","XCUITest");
		dc.setCapability("platformName","iOS");
		dc.setCapability("platformVersion","13.5");
		dc.setCapability("deviceName","iPhone 8");
		dc.setCapability("app","https://github.com/cloudgrey-io/the-app/releases/download/v1.2.1/TheApp-v1.2.1.app.zip");
		//Declare driver object to launch app via appium server
		IOSDriver driver=new IOSDriver(as.getUrl(),dc);
		String BUNDLE_ID="io.cloudgrey.the-app";
		HashMap<String,Object> hm=new HashMap<>();
        hm.put("bundleId",BUNDLE_ID);
		try
		{
			Thread.sleep(5000);
			//Close app, which is opened by default
            driver.executeScript("mobile:terminateApp",hm);
            //Create top and bottom coordinates on mobile home screen
			Dimension screenSize=driver.manage().window().getSize();
			int yMargin=5;
	        int xMid=screenSize.width/2;
	        PointOption top=PointOption.point(xMid, yMargin);
	        PointOption bottom=PointOption.point(xMid,screenSize.height-yMargin);
	        //Perform top to bottom
	        TouchAction action=new TouchAction(driver);
	        action.press(top);
	        action.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)));
	        action.moveTo(bottom);
	        action.perform();
	        Thread.sleep(10000);
	        //Perform bottom to top
	        action.press(bottom);
	        action.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)));
	        action.moveTo(top);
	        action.perform();
	        Thread.sleep(10000);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//Stop Appium server
		as.stop();
	}
}