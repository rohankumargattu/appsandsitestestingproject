package iosappsiteautomation;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Test76 
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
		//Provide capabilities related to IOS Simulator and browser
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,"XCUITest");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME,"iOS");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,"13.5");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME,"iPhone 8");
		dc.setCapability("browserName","Safari");
		//Declare driver object to launch browser via appium server
		IOSDriver driver=new IOSDriver(as.getUrl(),dc);
		WebDriverWait wait=new WebDriverWait(driver,10);
		try
		{
			Thread.sleep(5000);
		    driver.get("http://appiumpro.com");
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("toggleMenu"))).click();
		    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Contact"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contactEmail"))).sendKeys("apj@abdulkalam.com");
            driver.findElement(By.id("contactText")).sendKeys("Hello sir!");
            driver.findElement(By.cssSelector("input[type=submit]")).click();
            String res=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class^=response]"))).getText();
            System.out.println(res);
	        Thread.sleep(5000);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			//Close site
			driver.quit();
			//Stop Appium server
			as.stop();
		}
	}
}