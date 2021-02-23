package iosappsiteautomation;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.serverevents.CustomEvent;
import io.appium.java_client.serverevents.ServerEvents;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Test69 
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
		String APP="https://github.com/cloudgrey-io/the-app/releases/download/v1.10.0/TheApp-v1.10.0.app.zip";
	    DesiredCapabilities dc=new DesiredCapabilities();
	    dc.setCapability("platformName", "iOS");
	    dc.setCapability("platformVersion", "13.5");
	    dc.setCapability("deviceName", "iPhone 8");
	    dc.setCapability("app", APP);
	    //Launch app and do login
	    IOSDriver driver=new IOSDriver(as.getUrl(),dc);
	    CustomEvent evt=new CustomEvent();
	    evt.setVendor("theApp"); //app name
	    try
	    {
	    	WebDriverWait wait=new WebDriverWait(driver,20);
	    	wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Login Screen"))).click();
	    	evt.setEventName("go to Login screen");
	    	driver.logEvent(evt);
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@label='username']"))).sendKeys("alice");
	    	evt.setEventName("userid entered");
	    	driver.logEvent(evt);
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@label='password']"))).sendKeys("mypassword");
	    	evt.setEventName("password entered");
	    	driver.logEvent(evt);
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@label='loginBtn'])[2]"))).click();
	        evt.setEventName("clicked on login button");
	    	driver.logEvent(evt);
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@label='You are logged in as alice']")));
	    	evt.setEventName("logined successfully");
	    	driver.logEvent(evt);
	    	evt.setEventName("test End");
	    	driver.logEvent(evt);
	    }
	    catch(Exception ex)
	    {
	    	evt.setEventName(ex.getMessage());
	    	driver.logEvent(evt);
	    }
	    //Close app
        driver.closeApp();
        evt.setEventName("App was closed");
    	driver.logEvent(evt);
        //Save Events
        ServerEvents events=driver.getEvents();
        File f=new File("/Users/nrstt/Desktop/log123.json");
        Path p=f.toPath();
        events.save(p);
        //Stop Appium Server
        as.stop();
	}
}
