package srinivasansekarAppAuto;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.serverevents.ServerEvents;

public class AppiumServerLogs
{
	public static void main(String[] args) throws Exception
	{
		//Start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//Get address of appium Server
		URL u=new URL("http://127.0.0.1:4723/wd/hub");
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ce081718334a5b0b05");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.0.0");
		dc.setCapability("appPackage","com.sec.android.app.popupcalculator");
		dc.setCapability("appActivity","com.sec.android.app.popupcalculator.Calculator");
		//Create driver object
		AndroidDriver driver;
		while(2>1)
		{
			try
			{
				driver=new AndroidDriver(u,dc);
				break;
			}
			catch(Exception ex)
			{
			}
		}
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='7']")));
			driver.findElement(By.xpath("//*[@text='7']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='4']")));
			driver.findElement(By.xpath("//*[@text='4']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='5']")));
			driver.findElement(By.xpath("//*[@text='5']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='8']")));
			driver.findElement(By.xpath("//*[@text='8']")).click();
			ServerEvents events=driver.getEvents();
			File f=new File("E:\\Automation\\events.json");
			Path p=f.toPath();
			events.save(p);
			//install appium event parser via npm to analyze the response
			//"appium-event-parser -t -i E:\\Automation\\events.json" in command prompt 
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		driver.closeApp();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
