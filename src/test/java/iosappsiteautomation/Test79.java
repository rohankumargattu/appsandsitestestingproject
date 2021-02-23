package iosappsiteautomation;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Test79 
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
		dc.setCapability("browserName","Safari");
		//Declare driver object to launch browser via appium server
		IOSDriver driver=new IOSDriver(as.getUrl(),dc);
		try
		{
			Thread.sleep(10000);
			//Launch site and handle Shadow DOM
		    WebDriverWait wait=new WebDriverWait(driver,10);
	        driver.get("https://material-components.github.io/material-components-web-components/demos/switch.html");
	        //find the web component based shadow element
	        WebElement parent=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//mwc-switch[1]")));
	        //pierce shadow dom to get checked status of inner control, and assert on it
	        boolean checked=(boolean)driver.executeScript("return(arguments[0].shadowRoot.querySelector('input').checked);",parent);
	        System.out.println(checked);
	        Thread.sleep(10000);
	        //change the state from off to on by clicking inner input
	        driver.executeScript("arguments[0].shadowRoot.querySelector('input').click();",parent);
	        //check that state of inner control has changed appropriately
	        checked=(boolean)driver.executeScript("return arguments[0].shadowRoot.querySelector('input').checked;",parent);
	        System.out.println(checked);
		    Thread.sleep(10000);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//Close app
		driver.quit();
		//Stop Appium server
		as.stop();
	}
}