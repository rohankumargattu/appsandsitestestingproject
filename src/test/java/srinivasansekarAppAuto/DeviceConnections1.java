package srinivasansekarAppAuto;

import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;

public class DeviceConnections1
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
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723\"");
		//Get address of appium Server
		URL u=new URL("http://0.0.0.0:4723/wd/hub");
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
		
		//Automation
		try
		{
			//Get connection details of WIFI,DATA,AIRPLANE related to device
			ConnectionState cs=driver.getConnection();
			//About WIFI
			if(cs.isWiFiEnabled())
			{
				System.out.println("Wifi is ON");
			}
			else
			{
				System.out.println("Wifi is OFF");
			}
			//About DATA
			if(cs.isDataEnabled())
			{
				System.out.println("Data is ON");
			}
			else
			{
				System.out.println("Data is OFF");
			}
			//About AIRPLANE
			if(cs.isAirplaneModeEnabled())
			{
				System.out.println("AirplaneMode is ON");
			}
			else
			{
				System.out.println("AirplaneMode is OFF");
			}	
			
			String autoname=driver.getAutomationName();
			System.out.println("Currently using driver is "+autoname);
		}
		catch(Exception exe)
		{
			System.out.println(exe.getMessage());
		}
		
		//Close app
		driver.closeApp();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
