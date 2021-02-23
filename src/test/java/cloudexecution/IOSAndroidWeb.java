package cloudexecution;

import java.net.URL;
import java.util.Scanner;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class IOSAndroidWeb
{
	public static void main(String[] args) throws Exception
	{
		//Enter data from keyboard
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter device name iphone/simulator/ARD/AVD");
		String dn=sc.nextLine();
		sc.close();
		
		//Declare Global objects
		DesiredCapabilities dc;
		AppiumDriver driver=null;
		
		//Define Credentials and create URL
		String username="rohankumargattu";
		String accesskey="b7e91619ca4340b98305a6ed4a2437b5";
		URL u=new URL("http://"+username+":"+accesskey+"@ondemand.saucelabs.com:80/wd/hub");
		
		//Define desired capabilities
		if(dn.equals("simulator"))
		{
			dc = DesiredCapabilities.iphone();
			dc.setCapability("appiumVersion", "1.20.1");
			dc.setCapability("deviceName","iPhone 12 Pro Max Simulator");
			dc.setCapability("deviceOrientation", "portrait");
			dc.setCapability("platformVersion","14.3");
			dc.setCapability("platformName", "iOS");
			dc.setCapability("browserName", "Safari");
			//Create driver object
			//driver=new IOSDriver(u,dc);
			while(2>1)
			{
				try
				{
					driver=new IOSDriver(u,dc);
					break;
				}
				catch(Exception ex)
				{
				}
			}
		}
		else if(dn.equals("iphone"))
		{
			//Real devices are available in TestObject cloud
		}
		else if(dn.equals("AVD"))
		{
			dc = DesiredCapabilities.android();
			dc.setCapability("appiumVersion", "1.19.2");
			dc.setCapability("deviceName","Samsung Galaxy S9 FHD GoogleAPI Emulator");
			dc.setCapability("deviceOrientation", "portrait");
			dc.setCapability("browserName", "Chrome");
			dc.setCapability("platformVersion", "9.0");
			dc.setCapability("platformName","Android");
			//Create driver object
			//driver=new AndroidDriver(u,dc);
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
		}
		else if(dn.equals("ARD"))
		{
			//Real devices are available in TestObject cloud
		}
		else
		{
			System.out.println("Spelling chusko mundhu ah tarvata execute cheyyi");
			System.exit(0);
		}
		//Launch site
		driver.get("https://www.google.co.in");
		Thread.sleep(5000);
		//Validation
		if(driver.getTitle().equals("Google"))
		{
			System.out.println("Test passed");
		}
		else
		{
			System.out.println("Test failed");
		}
		//Close site
		driver.quit();
	}
}
