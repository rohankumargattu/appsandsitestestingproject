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

public class MobileColonListSms
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
			Map<String, Object> resmap=(Map<String, Object>) driver.executeScript("mobile:listSms");
			List<Map<String, Object>> msgs=(List<Map<String, Object>>) resmap.get("items");
			//Get and display latest SMS details
			/*System.out.println(msgs.get(0).get("address"));
			System.out.println(msgs.get(0).get("date"));
			System.out.println(msgs.get(0).get("subject"));
			System.out.println(msgs.get(0).get("body"));*/
			//Display all SMS details
			for(int i=0;i<msgs.size();i++)
			{
				System.out.println("Message "+(i+1)+":");
				System.out.println(msgs.get(i).get("address"));
				System.out.println(msgs.get(i).get("date"));
				System.out.println(msgs.get(i).get("subject"));
				System.out.println(msgs.get(i).get("body"));
				System.out.println();
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
