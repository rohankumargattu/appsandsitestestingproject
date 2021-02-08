package srinivasansekarAppAuto;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class MobileColonGetNotifications
{
	public static void main(String[] args) throws Exception
	{
		//Start appium sever
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		//Get appium server address
		URL u=new URL("http://0.0.0.0:4723/wd/hub");
		//Define desired capabilities related to device and app
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ce081718334a5b0b05");
	    dc.setCapability("platformName","android");
	    dc.setCapability("platformVersion","8.0.0");
	    dc.setCapability("autoGrantPermissions","true"); //extra 
	    dc.setCapability("adbExecTimeout","50000"); //in msec //extra
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
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='LOG IN']")));
			Map<String, Object> resmap=(Map<String, Object>)driver.executeScript("mobile:getNotifications");
			List<Map<String, Object>> mapslist=(List<Map<String, Object>>)resmap.get("statusBarNotifications");
			for(Map<String, Object> eachmap:mapslist) 
			{
				Map<String, String> ncontent=(Map<String, String>)eachmap.get("notification");
				//display title
				if(ncontent.get("bigTitle")!=null) 
				{
					System.out.println(ncontent.get("bigTitle"));
				} 
				else 
				{
					System.out.println(ncontent.get("title"));
				}
				//display text
				if(ncontent.get("bigText")!=null) 
				{
					System.out.println(ncontent.get("bigText"));
				} 
				else 
				{
					System.out.println(ncontent.get("text"));
				}
			}
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
