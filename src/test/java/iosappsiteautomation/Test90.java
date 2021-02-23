package iosappsiteautomation;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Test90 
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
		//Common data
		String APP="https://github.com/cloudgrey-io/the-app/releases/download/v1.6.1/TheApp-v1.6.1.app.zip";
	    By listView=MobileBy.AccessibilityId("List Demo");
	    By cloud=MobileBy.AccessibilityId("Cirrostratus");
		//Provide capabilities related to IOS Simulator and App
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability("automationName","XCUITest");
		dc.setCapability("platformName","iOS");
		dc.setCapability("platformVersion","13.5");
		dc.setCapability("deviceName","iPhone 8");
		dc.setCapability("app",APP);
		//Declare driver object to launch app via appium server
	    IOSDriver driver=new IOSDriver(as.getUrl(),dc);
	    WebDriverWait wait=new WebDriverWait(driver,20);
		try
		{
			Thread.sleep(5000);
			wait.until(ExpectedConditions.presenceOfElementLocated(listView)).click();
	        wait.until(ExpectedConditions.presenceOfElementLocated(cloud)).click();
	        wait.until(ExpectedConditions.alertIsPresent());
	        //Collect labels of all buttons in alert
	        HashMap<String, String> hm=new HashMap<>();
	        hm.put("action","getButtons");
	        List<String> buttons=(List<String>)driver.executeScript("mobile:alert",hm);
	        //find the label of the button which isn't 'OK' or 'Cancel'
	        String buttonLabel=null;
	        for(String button:buttons) 
	        {
	            if(button.equals("OK")||button.equals("Cancel")) 
	            {
	                continue; //continue loop
	            }
	            else
	            {
	            	buttonLabel=button;
	            	break; //terminate from loop
	            }
	        }
	        if(buttonLabel==null)
	        {
	        	System.out.println("No extra buttons");
	        }
	        else
	        {
	        	//here we could verify that the new button press worked
	        	hm.put("action","accept");
	        	hm.put("buttonLabel",buttonLabel);
	        	driver.executeScript("mobile:alert",hm);
	        	wait.until(ExpectedConditions.alertIsPresent());
	        	String alertText=driver.switchTo().alert().getText();
	        	System.out.println(alertText);
	        	driver.switchTo().alert().accept();
	        }
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