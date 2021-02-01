package srinivasansekarAppAuto;

import java.net.URL;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;


public class Collections 
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
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='LOG IN']")));
			//Utilization of "Set"
			Set x=driver.getContextHandles();
			System.out.println("Contexts are: "+x);
			//Utilization of "Map/HashMap"
			//Get app details
			System.out.println("\nApp String map:");
			Map<String,String> m1=driver.getAppStringMap();
			for(Map.Entry e1:m1.entrySet())
			{
				System.out.println(e1.getKey()+" = "+e1.getValue());
			}
			//Get device details
			System.out.println("\nSettings:");
			Map<String,Object> m3=driver.getSettings();
			for(Map.Entry e3:m3.entrySet())
			{
				System.out.println(e3.getKey()+" = "+e3.getValue().toString());
			}
			//Get session details(server details)
			System.out.println("\nSession Details:");
			Map<String,Object> m2=driver.getSessionDetails();
			for(Map.Entry e2:m2.entrySet())
			{
				System.out.println(e2.getKey()+" = "+e2.getValue().toString());
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
