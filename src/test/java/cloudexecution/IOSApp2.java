package cloudexecution;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;

public class IOSApp2
{
	public static void main(String[] args) throws Exception
	{
		//Capabilities related to app and any ios real device(as per availability) in cloud
		DesiredCapabilities caps=new DesiredCapabilities();
	    caps.setCapability("username","rohankumargattu");
	    caps.setCapability("accessKey","b7e91619ca4340b98305a6ed4a2437b5");
	    caps.setCapability("deviceName","iPhone 7");  //space required
	    caps.setCapability("deviceOrientation", "portrait");
	    caps.setCapability("platformVersion","12.2");
	    caps.setCapability("platformName", "iOS");
	    caps.setCapability("browserName", "");
		caps.setCapability("app","https://github.com/cloudgrey-io/the-app/releases/download/v1.10.0/TheApp-v1.10.0.app.zip");
		//Give Cloud(Sauce Labs) details
		String USERNAME="rohankumargattu";
		String ACCESSKEY="b7e91619ca4340b98305a6ed4a2437b5";
		String endpoint="http://"+USERNAME+":"+ACCESSKEY+"@ondemand.saucelabs.com:80/wd/hub";
		URL u=new URL(endpoint);
		IOSDriver driver=new IOSDriver(u,caps);
		Thread.sleep(5000);
		System.out.println("App launched");
		//Do login
		WebDriverWait w=new WebDriverWait(driver,20);
		w.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Login Screen"))).click();
		w.until(ExpectedConditions.elementToBeClickable(MobileBy.name("username"))).click();
		Thread.sleep(5000);
		w.until(ExpectedConditions.elementToBeClickable(MobileBy.name("username"))).sendKeys("alice");
		Thread.sleep(5000);
		w.until(ExpectedConditions.elementToBeClickable(MobileBy.name("password"))).sendKeys("mypassword");
		Thread.sleep(5000);
		w.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("(//*[@name='loginBtn'])[2]"))).click();
		try
		{
			w.until(ExpectedConditions.elementToBeClickable(MobileBy.name("Logout")));
			System.out.println("Login test passed");
		}
		catch(Exception ex)
		{
			System.out.println("Login test failed");
		}
		//Close site
		driver.quit();
	}
}
