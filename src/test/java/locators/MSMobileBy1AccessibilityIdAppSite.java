package locators;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.org.apache.xerces.internal.impl.xs.identity.Selector;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileSelector;
import io.appium.java_client.android.AndroidDriver;

public class MSMobileBy1AccessibilityIdAppSite
{
	public static void main(String[] args) throws Exception
	{
		//Start appium server programmatically
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723\"");
		//Get address of appium Server
		URL u=new URL("http://0.0.0.0:4723/wd/hub");
		//Desired Capabilities of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","emulator-5554");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","5.1.1");
		dc.setCapability("appPackage","com.android.dialer");
		dc.setCapability("appActivity","com.android.dialer.DialtactsActivity");
		
		//Object Creation
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
			wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("dial pad")));
			driver.findElement(MobileSelector.ACCESSIBILITY.toString(),"dial pad").click();
			//driver.findElement(MobileBy.AccessibilityId("dial pad")).click();
			//driver.findElementByAccessibilityId("dial pad").click();
			Thread.sleep(5000);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//Close app
		driver.closeApp();
		Thread.sleep(5000);
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");	
	}
}
