package iosappsiteautomation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Test80 
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
		dc.setCapability("automationName","XCUITest");
		dc.setCapability("platformName","iOS");
		dc.setCapability("platformVersion","13.5");
		dc.setCapability("deviceName","iPhone 8");
		dc.setCapability("browserName","Safari");
		//Declare driver object to launch browser via appium server
		IOSDriver driver=new IOSDriver(as.getUrl(),dc);
		try
		{
			Thread.sleep(5000);
			//Create a text file to store browser logs
			File f=new File("safarigmaillogs.txt");
			FileWriter fw=new FileWriter(f);
			BufferedWriter bw=new BufferedWriter(fw);
			//Launch Gmail site and handle browser logs
		    WebDriverWait wait=new WebDriverWait(driver,10);
	        driver.get("https://www.gmail.com");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identifier")));
			LogEntries ls=driver.manage().logs().get("safariConsole"); 
			//"browser" for other browsers
			for(LogEntry l:ls)
			{
				//message in log entries is a json content
				//that content converted as hash map
				Json js=new Json();
				HashMap hp=js.toType(l.getMessage(),Json.MAP_TYPE);
				bw.write(l.getTimestamp()+"------"+l.getLevel()+"------"+hp.get("text"));
			}
	        Thread.sleep(5000);
	        bw.close();
	        fw.close();
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