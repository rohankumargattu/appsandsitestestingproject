package srinivasansekarAppAuto;

import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class AdapterViewWay2
{
	public static void main(String[] args) throws Exception
	{
		//Start appium sever
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		//Get appium server address
		URL u=new URL("http://0.0.0.0:4723/wd/hub");
		//Define desired capabilities related to device and app
		//https://github.com/appium/appium/blob/master/sample-code/apps/ApiDemos-debug.apk
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,AutomationName.ESPRESSO);
		dc.setCapability("forceEspressRebuild","true");
		dc.setCapability("showGradleLog","true");
	    dc.setCapability("deviceName","ce081718334a5b0b05");
	    dc.setCapability("platformName","android");
	    dc.setCapability("platformVersion","8.0.0");
		dc.setCapability("appPackage","io.appium.android.apis");
		dc.setCapability("appActivity","io.appium.android.apis.ApiDemos");
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
			WebDriverWait w=new WebDriverWait(driver,10);
			w.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='Views']"))).click();
			JSONArray ja=new JSONArray();
			ja.put("title");
			ja.put("TextClock");
			//List<String> il=Arrays.asList("title","TextClock");
			JSONObject jo=new JSONObject();
			jo.put("name","hasEntry");
			jo.put("args",ja);
			driver.findElementByAndroidDataMatcher(jo.toString()).click();
			//driver.findElementByAndroidDataMatcher("{\"name\":\"hasEntry\",\"args\":[\"title\",\"TextClock\"]}").click();
			Thread.sleep(10000);
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
