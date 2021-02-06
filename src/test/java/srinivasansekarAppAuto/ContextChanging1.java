package srinivasansekarAppAuto;

import java.net.URL;
import java.util.Set;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class ContextChanging1
{
	public static void main(String[] args) throws Exception
	{
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"chrome");
		dc.setCapability("deviceName","ce081718334a5b0b05");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.0.0");
		//dc.setCapability("automationName","uiautomator2");
		//dc.setCapability("appPackage","com.cricbuzz.android");
		//dc.setCapability("appActivity","com.cricbuzz.android.lithium.app.view.activity.MatchCenterActivity");
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
			//Context for hybrid apps
			Thread.sleep(5000);
			Set s=driver.getContextHandles();
			System.out.println(s); //2
			//Get Current Context
			String x=driver.getContext();
			System.out.println(x); //CHROMIUM
			//driver.context("NATIVE_APP");
			//String y=driver.getContext();
			//System.out.println(y); //NATIVE_APP
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		//Close app/browser
		if(driver.isBrowser())
		{
			driver.close();
			System.out.println("close is Executed");
		}
		else
		{
			driver.closeApp();
			System.out.println("closeApp is Executed");
		}
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
