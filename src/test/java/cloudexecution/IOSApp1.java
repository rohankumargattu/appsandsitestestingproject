package cloudexecution;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;

public class IOSApp1
{
	public static void main(String[] args) throws Exception
	{
		//Capabilities related to app and ios simulator in cloud
		DesiredCapabilities caps = DesiredCapabilities.iphone();
		caps.setCapability("appiumVersion", "1.20.1");
		caps.setCapability("deviceName","iPhone 8 Plus Simulator");
		caps.setCapability("deviceOrientation", "portrait");
		caps.setCapability("platformVersion","14.3");
		caps.setCapability("platformName", "iOS");
		caps.setCapability("browserName", "");
		caps.setCapability("app","https://github.com/cloudgrey-io/the-app/releases/download/v1.10.0/TheApp-v1.10.0.app.zip");
		
		//Define Credentials and create URL
		String username="rohankumargattu";
		String accesskey="b7e91619ca4340b98305a6ed4a2437b5";
		URL u=new URL("http://"+username+":"+accesskey+"@ondemand.saucelabs.com:80/wd/hub");
		
		IOSDriver driver=new IOSDriver(u,caps);
		System.out.println("App launched");
		//Do login
		WebDriverWait w=new WebDriverWait(driver,20);
		w.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Login Screen"))).click();
		w.until(ExpectedConditions.elementToBeClickable(MobileBy.name("username"))).click();//for virtal keyboard
		w.until(ExpectedConditions.elementToBeClickable(MobileBy.name("username"))).sendKeys("alice");
		w.until(ExpectedConditions.elementToBeClickable(MobileBy.name("password"))).sendKeys("mypassword");
		Thread.sleep(5000);
		w.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("(//*[@name='loginBtn'])[2]"))).click();
		try
		{
			w.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("(//*[@name='Logout'])[2]")));
			System.out.println("Login test passed");
		}
		catch(Exception ex)
		{
			System.out.println("Login test failed");
		}
		//Close app and session
		driver.quit();
	}
}
