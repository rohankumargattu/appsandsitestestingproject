package srinivasansekarAppAuto;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class DeviceDefaultKeys
{
	public static void main(String[] args) throws Exception
	{
		//DesiredCapabilities of app and device(ARD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ce081718334a5b0b05");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.0.0");
		dc.setCapability("appPackage","com.vodqareactnative");
		dc.setCapability("appActivity","com.vodqareactnative.MainActivity");
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
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='LOG IN']")));
			KeyEvent k;
			//Close app
			driver.closeApp();
			for(int i=0;i<=10;i++)
			{
				k=new KeyEvent(AndroidKey.VOLUME_UP);
				driver.pressKey(k);
				Thread.sleep(500);
			}
			for(int i=0;i<=10;i++)
			{
				k=new KeyEvent(AndroidKey.VOLUME_DOWN);
				driver.pressKey(k);
				Thread.sleep(500);
			}
			k=new KeyEvent(AndroidKey.CAMERA);
			driver.pressKey(k);
			Thread.sleep(3000);
			k=new KeyEvent(AndroidKey.BACK);
			driver.pressKey(k);
			Thread.sleep(3000);
			k=new KeyEvent(AndroidKey.POWER);
			driver.pressKey(k);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
