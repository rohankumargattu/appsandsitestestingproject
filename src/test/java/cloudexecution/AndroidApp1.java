package cloudexecution;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class AndroidApp1
{
	public static void main(String[] args) throws Exception
	{
	    DesiredCapabilities caps=DesiredCapabilities.android();
	    caps.setCapability("appiumVersion", "1.17.1");
	    caps.setCapability("deviceName","Samsung Galaxy S9 WQHD GoogleAPI Emulator");
	    caps.setCapability("deviceOrientation","portrait");
	    caps.setCapability("browserName","");
	    caps.setCapability("platformVersion","9.0");
	    caps.setCapability("platformName","Android");
	    caps.setCapability("app","https://github.com/appium/android-apidemos/releases/download/v3.1.0/ApiDemos-debug.apk");
	    //Give Cloud(Sauce Labs) details
	  	String USERNAME="rohankumargattu";
	  	String ACCESSKEY="b7e91619ca4340b98305a6ed4a2437b5";
	  	URL u=new URL("http://"+USERNAME+":"+ACCESSKEY+"@ondemand.saucelabs.com:80/wd/hub");
	  	//Create driver object to start session in cloud
	  	AndroidDriver driver=new AndroidDriver(u,caps);
	  	WebDriverWait wait=new WebDriverWait(driver,20);
	  	System.out.println("App launched");
	    wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Views"))).click();
	    wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.className("android.widget.ListView")));
	    //bottom to top swipe
		while(2>1)
		{
			try
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("TextClock"))).click();
				break; //terminate from loop
			}
			catch(Exception ex)
			{
				TouchAction ta=new TouchAction(driver);
				Point p=new Point(535,2535);
				ta.press(PointOption.point(p)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(0,-1000)).perform();
			}
		}
	    String msg=wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.className("android.widget.LinearLayout"))).getText();
	    System.out.println(msg);
	    driver.quit(); //close session in cloud
	}
}
