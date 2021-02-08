package srinivasansekarAppAuto;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;

public class MobileColonShellADBCommands3
{
	public static void main(String[] args) throws Exception
	{
		//To get list of all folders from device
		//Start appium sever with --relaxed-security flag
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium --relaxed-security\"");
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
	    dc.setCapability("appPackage", "com.google.android.apps.photos");
        dc.setCapability("appActivity","com.google.android.apps.photos.home.HomeActivity");
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
			//adb shell ls /mnt/sdcard
			Thread.sleep(5000);
            List<String> l=Arrays.asList("/mnt/sdcard");
            Map<String, Object> lsCmd=ImmutableMap.of("command","ls", "args",l);
            String lsoutput=(String) driver.executeScript("mobile:shell",lsCmd);
            System.out.println(lsoutput);
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
