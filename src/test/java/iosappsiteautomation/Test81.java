package iosappsiteautomation;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Test81 
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
		try
		{
			Thread.sleep(5000);
			//Launch site and do login via form filling
			WebDriverWait wait =new WebDriverWait(driver,10);
	        driver.get("http://expenseus.com/user/login");
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("user[email]"))).sendKeys("magnitiait@gmail.com");
	        driver.findElement(By.name("user[password]")).sendKeys("abdulkalam");
	        driver.findElement(By.xpath("//input[@value='Log in']")).click();
	        Thread.sleep(5000);
	        try
	        {
	        	if(driver.findElement(By.linkText("Logout")).isDisplayed())
	        	{
	        		System.out.println("Successful login");
	        	}
	        }
	        catch(Exception exc)
	        {
	        	System.out.println("Unsuccessful login");
	        }
	        //Get and display all cookies
	        Set<Cookie> cl=driver.manage().getCookies();
	        ArrayList<Cookie> ca=new ArrayList<Cookie>(cl);
	        for(Cookie c:ca)
	        {
	        	System.out.println(c.getName());
	        }
	        //Remember session cookie via Cookie object and delete for browser
	        Cookie loginCookie=driver.manage().getCookieNamed("PHPSESSID");
	        driver.manage().deleteCookieNamed("PHPSESSID");
	        Thread.sleep(20000);
	        //Launch site and do login by adding session cookie to browser
	        System.out.println("Using cookie");
	        driver.manage().addCookie(loginCookie);
	        driver.get("http://expenseus.com");
	        try
	        {
	        	if(driver.findElement(By.linkText("Logout")).isDisplayed())
	        	{
	        		System.out.println("Successful login");
	        	}
	        }
	        catch(Exception exc)
	        {
	        	System.out.println("Unsuccessful login");
	        }
	        Thread.sleep(20000);
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