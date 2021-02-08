package srinivasansekarAppAuto;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class MobileColonType
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
			WebElement e=driver.findElement(By.xpath("//*[@text='admin']"));
			e.clear();  //clear existing data
			e.click();
			Thread.sleep(3000);
			if(driver.isKeyboardShown())
			{
				driver.hideKeyboard();
			}
			//Way 1
			/*JSONObject jo=new JSONObject();
			jo.put("text","admin");
			driver.executeScript("mobile:type",jo.toMap());*/
			//Way 2
			/*Map<String,Object> m1=new HashMap<String,Object>();
			m1.put("text","admin");
			driver.executeScript("mobile:type",m1);*/
			//Way 3
			Map<String,Object> m1=ImmutableMap.of("text","admin");
			driver.executeScript("mobile:type",m1);
			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='LOG IN']"))).click();
			Thread.sleep(3000);
			MobileElement e1=(MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Long Press\")");
			String e1id=e1.getId();
			MobileElement e2=(MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Slider\")");
			String e2id=e2.getId();
			Map<String,Object> m2=ImmutableMap.of("elementId",e1id,"elementToId",e2id);
			driver.executeScript("mobile:scrollBackTo",m2);
			Thread.sleep(3000);
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
