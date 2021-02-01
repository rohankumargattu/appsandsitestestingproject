package srinivasansekarAppAuto;

import java.net.URL;
import java.util.Scanner;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverSelection
{
	public static void main(String[] args) throws Exception
	{
		//Get driver name
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter driver like espresso/uiautomator2");
		String dn=sc.nextLine();
		sc.close();
		//Start appium sever
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u=new URL("http://0.0.0.0:4723/wd/hub");
		//Define desired capabilities related to device and app
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		if(dn.equalsIgnoreCase("espresso"))
		{
			dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,AutomationName.ESPRESSO);
			dc.setCapability("forceEspressRebuild","true");
		}
		else if(dn.equalsIgnoreCase("uiautomator2"))
		{
			dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,AutomationName.ANDROID_UIAUTOMATOR2);
		}
		else
		{
			System.out.println("Check the spelling of driver name");
			System.exit(0);
		}
		dc.setCapability("deviceName","emulator-5554");
	    dc.setCapability("platformName","android");
	    dc.setCapability("platformVersion","8.1.0");
		dc.setCapability("appPackage","com.vodqareactnative");
	    dc.setCapability("appActivity","com.vodqareactnative.MainActivity");
		//Launch app in device through appium server
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
		//Common test automation code for both drivers
		try
		{
			Thread.sleep(10000);
			driver.findElementByXPath("//*[@text='LOG IN']").click();
			Thread.sleep(5000);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//Close App
		driver.quit();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe"); 
	}
}