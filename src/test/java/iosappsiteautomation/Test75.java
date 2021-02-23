package iosappsiteautomation;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Test75 
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
			long s1=System.currentTimeMillis();
			WebElement ele1=driver.findElementByAccessibilityId("login");
			long e1=System.currentTimeMillis();
			System.out.println("time related to accessibility id is "+(e1-s1));
			long s2=System.currentTimeMillis();
			WebElement ele2=driver.findElementByXPath("//*[@name='login']");
			long e2=System.currentTimeMillis();
			System.out.println("time related to xpath is "+(e2-s2));
			long s3=System.currentTimeMillis();
			WebElement ele3=driver.findElementByIosNsPredicate("label=='login'");
			long e3=System.currentTimeMillis();
			System.out.println("time related to NsPredicate is "+(e3-s3));
			long s4=System.currentTimeMillis();
			WebElement ele4=driver.findElementByIosClassChain("XCUIElementTypeWindow/XCUIElementTypeOther[$name CONTAINS \"login\"$]");
			long e4=System.currentTimeMillis();
			System.out.println("time related to classchain is "+(e4-s4));
			long s5=System.currentTimeMillis();
			WebElement ele5=driver.findElementByName("login");
			long e5=System.currentTimeMillis();
			System.out.println("time related direct attribute is "+(e5-s5));
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