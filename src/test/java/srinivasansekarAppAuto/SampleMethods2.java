package srinivasansekarAppAuto;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.ElementOption;

public class SampleMethods2 
{
	public static void main(String[] args) throws Exception
	{
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ce081718334a5b0b05");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.0.0");
		dc.setCapability("appPackage","com.vodqareactnative");
		dc.setCapability("appActivity","com.vodqareactnative.MainActivity");
		//Start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//Get address of appium Server
		URL u=new URL("http://127.0.0.1:4723/wd/hub");
		KeyEvent k;
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
			//Come to home, open notifications and click clear all
			Thread.sleep(5000);
			k=new KeyEvent(AndroidKey.HOME);
			driver.pressKey(k);
			Thread.sleep(5000);
			driver.openNotifications();
			Thread.sleep(5000);
			try
			{
				WebElement e=driver.findElement(By.xpath("//*[@text='CLEAR']"));
				//String f=e.getAttribute("enabled");
				if(e.getAttribute("enabled").equalsIgnoreCase("true"))
				{
					e.click();
				}
				else
				{
					k=new KeyEvent(AndroidKey.BACK);
					driver.pressKey(k);
				}
			}
			catch(Exception exe)
			{
				k=new KeyEvent(AndroidKey.BACK);
				driver.pressKey(k);
			}
			Thread.sleep(5000);
			//Get details of appium server
			String path=driver.getRemoteAddress().getPath();
			System.out.println("Path of appium server is "+path);
			String protocol=driver.getRemoteAddress().getProtocol();
			System.out.println("Protocol of appium server is "+protocol);
			int port=driver.getRemoteAddress().getPort();
			System.out.println("Port of appium server is "+port);
			String host=driver.getRemoteAddress().getHost();
			System.out.println("Host of appium server is "+host);
			//Get details of device
			long density=driver.getDisplayDensity();
			System.out.println("Display density of device is "+density);
			String time=driver.getDeviceTime();
			System.out.println("Device time is "+time);
			String platname=driver.getPlatformName();
			System.out.println("platform name is "+platname);
			Thread.sleep(5000);
			//Get device lock status
			//driver.lockDevice();
			if(!driver.isDeviceLocked()) //if not locked then "if" block will execute
			{
				
				System.out.println("Unlocked");
				driver.lockDevice();
				Thread.sleep(10000);
				driver.unlockDevice();	//Device should not have any security patterns
			}
			Thread.sleep(5000);
			driver.launchApp();
			Thread.sleep(5000);			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		//Close app
		driver.closeApp();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
