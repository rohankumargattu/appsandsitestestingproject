package iosappsiteautomation;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.Setting;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Test72 
{
	public static void main(String[] args) throws Exception
	{
		//Start Appium server programmatically
		AppiumServiceBuilder sb=new AppiumServiceBuilder();
		sb.usingAnyFreePort();
        sb.usingDriverExecutable(new File("/usr/local/bin/node"));
		sb.withAppiumJS(new File("/usr/local/bin/appium"));
		//For IOS only
		HashMap<String,String> ev=new HashMap();
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
		try
		{
			Thread.sleep(10000);
			//locate using xpath and Click on Login 
			driver.findElement(By.xpath("//*[@name='login']")).click();
			Thread.sleep(10000);
			//locate using image and Click on back
			driver.setSetting(Setting.CHECK_IMAGE_ELEMENT_STALENESS,false);
			driver.setSetting(Setting.IMAGE_MATCH_THRESHOLD,0.4);  //assumption
			File f=new File("/Users/nrstt/batch249/myback.png");
			Path p=f.toPath();
			byte[] b=Files.readAllBytes(p);
			String picdata=Base64.getEncoder().encodeToString(b);
			WebElement e=driver.findElementByImage(picdata);
			e.click();
			Thread.sleep(10000);
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