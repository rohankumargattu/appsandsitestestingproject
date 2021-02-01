package srinivasansekarAppAuto;

import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class CollectionsPlayStoreLoops
{
	public static void main(String[] args) throws Exception
	{
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ce081718334a5b0b05");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.0.0");
		dc.setCapability("automationName","uiautomator2");
		dc.setCapability("appPackage","com.android.vending");
		dc.setCapability("appActivity","com.android.vending.AssetBrowserActivity");
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
			List<MobileElement> pl;
			List<MobileElement> cl;
			List<MobileElement> gcl;
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='HOME']")));
			pl=driver.findElements(By.xpath("//*[@resource-id='com.android.vending:id/flat_card_cluster_container']"));
			int x=pl.size();
			System.out.println("No of Parent frames are: "+x);
			Thread.sleep(5000);
			for(int i=0;i<pl.size();i++)
			{
				System.out.println("For Parent "+(i+1));
				cl=pl.get(i).findElements(By.xpath("//*[@resource-id='com.android.vending:id/flat_card_cluster_container']/child::*"));
				int y=cl.size();
				System.out.println("No of child frames are: "+y);
				Thread.sleep(5000);
				for(int j=0;j<cl.size();j++)
				{
					System.out.println("For Child "+(j+1));
					gcl=cl.get(j).findElements(By.xpath("//*[@resource-id='com.android.vending:id/cluster_content']/child::*"));
					int z=gcl.size();
					System.out.println("No of grand children are: "+z);
				}
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
