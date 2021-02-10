package locators;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileSelector;
import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;

public class MSMobileBy2ImageAppSite
{
	public static void main(String[] args) throws Exception
	{
		//Convert image to Base64 string
		File f=new File("dial.png");
		Path path=f.toPath();
		String x=Base64.getEncoder().encodeToString(Files.readAllBytes(path));
        System.out.println(x);
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
		dc.setCapability("appPackage","com.samsung.android.contacts");
	    dc.setCapability("appActivity","com.android.dialer.DialtactsActivity");
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
		//Test automation
		try
		{
			Thread.sleep(5000);
			//driver.findElement(MobileSelector.IMAGE.toString(),x).isDisplayed();
			//driver.findElement(MobileBy.image(x)).isDisplayed();
			if(driver.findElementByImage(x).isDisplayed())
			{
				int xco=driver.findElementByImage(x).getLocation().getX();
				int yco=driver.findElementByImage(x).getLocation().getY();
				int w=driver.findElementByImage(x).getSize().getWidth();
				int h=driver.findElementByImage(x).getSize().getHeight();
				System.out.println(xco+" "+yco);
				System.out.println(w+" "+h);
				//Automate operations on matched element using "TouchAction" class methods
			}
			Thread.sleep(5000);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//Close app
		driver.closeApp();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}

/*
driver.setSetting(Setting.IMAGE_MATCH_THRESHOLD, 0.2);
driver.setSetting(Setting.CHECK_IMAGE_ELEMENT_STALENESS,false);
driver.setSetting(Setting.FIX_IMAGE_FIND_SCREENSHOT_DIMENSIONS, false);
driver.setSetting(Setting.FIX_IMAGE_TEMPLATE_SIZE, true); 
driver.setSetting(Setting.IMAGE_ELEMENT_TAP_STRATEGY,"w3cActions"/"touchActions"); 
*/
