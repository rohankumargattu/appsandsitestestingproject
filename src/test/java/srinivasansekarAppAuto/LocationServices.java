package srinivasansekarAppAuto;

import java.net.URL;

import org.openqa.selenium.html5.Location;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class LocationServices
{
	public static void main(String[] args) throws Exception
	{
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ce081718334a5b0b05");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.0.0");
		dc.setCapability("locationServicesEnabled",true);
		dc.setCapability("locationServicesAuthorized",true);
		dc.setCapability("noReset","true");
		dc.setCapability("fullReset","false");
		dc.setCapability("appPackage","com.google.android.apps.maps");
		dc.setCapability("appActivity","com.google.android.maps.MapsActivity");
		//Start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//Get address of appium Server
		URL u=new URL("http://127.0.0.1:4723/wd/hub");
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
			Thread.sleep(20000);
			//Get current location
			Location current_location=driver.location();
			double lattitude=current_location.getLatitude();
			double longitude=current_location.getLongitude();
			double altitude=current_location.getAltitude();
			System.out.println("Lattitue of current location is "+lattitude);
			System.out.println("Longitude of current location is "+longitude);
			System.out.println("Altitude of current location is "+altitude);
			//Set location using latitude, longitude and Altitude
			//Set Location to Goa
			Location l1=new Location(15.299326,74.123993,2000);
			driver.setLocation(l1);
			Thread.sleep(5000);
			//Set location to kerela
			Location l2=new Location(10.850516,76.271080,2000);
			driver.setLocation(l2);
			Thread.sleep(5000);
			//Set location to minneapolis
			Location l3=new Location(44.977753,-93.265015,2000);
			driver.setLocation(l3);
			Thread.sleep(5000);
			//Set location to Namburu
			Location l4=new Location(16.357460,80.532341,2000);
			driver.setLocation(l4);
			Thread.sleep(5000);
			//Go back to current location
			Location l5=new Location(lattitude,longitude,altitude);
			driver.setLocation(l5);
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
