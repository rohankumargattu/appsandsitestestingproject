package srinivasansekarAppAuto;

import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.appium.Eyes;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;

public class AppUpgradeVisualRegressionTestAppliTools
{
	public static void main(String[] args) throws Exception
	{
		//Visual Testing via AppliTools Eyes cloud
		//String APP_V1="https://github.com/cloudgrey-io/the-app/releases/download/v1.10.0/TheApp-VR-v1.apk";
	    //String APP_V2="https://github.com/cloudgrey-io/the-app/releases/download/v1.10.0/TheApp-VR-v2.apk";
	    //Start appium sever
	  	Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
	  	URL u=new URL("http://0.0.0.0:4723/wd/hub");
	  	//Define desired capabilities related to device and app
	  	DesiredCapabilities dc=new DesiredCapabilities();
	  	dc.setCapability(CapabilityType.BROWSER_NAME,"");
	  	dc.setCapability("deviceName","ce081718334a5b0b05");
	    dc.setCapability("platformName","android");
	    dc.setCapability("platformVersion","8.0.0");
	  	dc.setCapability("app","E:\\Automation\\TheApp-v1.9.0.apk"); //decomment for 1st time execution
	    //dc.setCapability("app","E:\\Automation\\TheApp-v1.10.0.apk"); //decomment for 2nd time execution
	    dc.setCapability("uninstallOtherPackages","io.cloudgrey.the_app");
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
	    //Set up Eyes SDK(Register to Applitools and attach eyes dependency code in pom.xml)
		try
		{
			//Authenticate to Cloud
			Eyes eyes=new Eyes();
			eyes.setLogHandler(new StdoutLogHandler()); //To get logs in eclipse console
			eyes.setForceFullPageScreenshot(true); //To take screenshot of full screen of app
			eyes.setApiKey("UfC100vwEy0MbOU8nHqL3uzmV1058iemT9wV7VL1OeYjspI110");
			//Continue automation
	       	WebDriverWait wait=new WebDriverWait(driver,20);
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("Echo Box")));
	    	eyes.open(driver,"TheApp","Visual screen test");
	    	Thread.sleep(2000);
	    	eyes.checkWindow();
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("Echo Box"))).click();
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("messageInput"))).sendKeys("kalam");
	    	driver.findElement(MobileBy.AccessibilityId("messageSaveBtn")).click();
	    	Thread.sleep(2000);
	    	eyes.checkWindow();
	    	eyes.close();
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