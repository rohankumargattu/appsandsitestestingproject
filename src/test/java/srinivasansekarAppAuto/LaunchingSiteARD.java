package srinivasansekarAppAuto;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class LaunchingSiteARD
{
	public static void main(String[] args) throws Exception
	{
		//Start appium server programmatically
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a localhost -p 4723\"");
		//Get address of appium Server
		URL u=new URL("http://localhost:4723/wd/hub");
		//Desired Capabilities of browser and device(ARD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"chrome");
		dc.setCapability("deviceName","ce081718334a5b0b05");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.0.0");
		
		//Object Creation
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
		
		Thread.sleep(5000);
		driver.get("https://www.google.co.in/");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@name='q']")).sendKeys("Virat",Keys.ENTER);
		Thread.sleep(5000);
		//Close site
		driver.close();
		
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
