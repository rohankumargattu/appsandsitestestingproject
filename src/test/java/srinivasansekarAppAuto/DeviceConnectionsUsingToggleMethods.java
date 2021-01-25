package srinivasansekarAppAuto;

import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;

public class DeviceConnectionsUsingToggleMethods
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
			ConnectionState setcon;
			ConnectionState cs=driver.getConnection();
			//Enabling and Disabling WIFI
			if(cs.isWiFiEnabled())
			{
				System.out.println("Wifi is ON");
				Thread.sleep(5000);
				driver.toggleWifi();
				Thread.sleep(5000);
				if(cs.isWiFiEnabled())
				{
					System.out.println("Wifi is still ON");
				}
				else
				{
					System.out.println("Wifi is Turned OFF");
				}
			}
			else
			{
				System.out.println("Wifi is OFF");
				driver.toggleWifi();
				Thread.sleep(5000);
				if(cs.isWiFiEnabled())
				{
					System.out.println("Wifi is now turned ON");
				}
				else
				{
					System.out.println("Wifi is still OFF");
				}
			}
			
			//Enabling and Disabling DATA
			if(cs.isDataEnabled())
			{
				System.out.println("DATA is ON");
				Thread.sleep(5000);
				driver.toggleData();
				Thread.sleep(5000);
				if(cs.isDataEnabled())
				{
					System.out.println("DATA is still ON");
				}
				else
				{
					System.out.println("DATA is Turned OFF");
				}
			}
			else
			{
				System.out.println("DATA is OFF");
				driver.toggleData();
				Thread.sleep(5000);
				if(cs.isDataEnabled())
				{
					System.out.println("DATA is now turned ON");
				}
				else
				{
					System.out.println("DATA is still OFF");
				}
			}
			
			//Enabling and Disabling AIRPLANE MODE
			if(cs.isAirplaneModeEnabled())
			{
				System.out.println("AirplaneMode is ON");
				Thread.sleep(5000);
				driver.toggleAirplaneMode();
				Thread.sleep(5000);
				if(cs.isAirplaneModeEnabled())
				{
					System.out.println("Airplane Mode is still ON");
				}
				else
				{
					System.out.println("Airplane Mode is Turned OFF");
				}
			}
			else
			{
				System.out.println("AirplaneMode is OFF");
				/*driver.toggleAirplaneMode();
				Thread.sleep(5000);
				if(cs.isAirplaneModeEnabled())
				{
					System.out.println("Airplane Mode now turned ON");
				}
				else
				{
					System.out.println("Airplane Mode is still OFF");
				}*/
			}
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
