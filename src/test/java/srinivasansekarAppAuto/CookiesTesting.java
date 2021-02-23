package srinivasansekarAppAuto;

import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class CookiesTesting
{
	public static void main(String[] args) throws Exception
	{
		//Enter data from keyboard
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter device name iphone/simulator/ARD/AVD");
		String dn=sc.nextLine();
		sc.close();
		
		//Declare Global objects
		DesiredCapabilities dc;
		AppiumDriver driver=null;
		
		//Define Credentials and create URL
		String username="rohankumargattu";
		String accesskey="b7e91619ca4340b98305a6ed4a2437b5";
		URL u=new URL("http://"+username+":"+accesskey+"@ondemand.saucelabs.com:80/wd/hub");
		
		//Define desired capabilities
		if(dn.equals("simulator"))
		{
			dc = DesiredCapabilities.iphone();
			dc.setCapability("appiumVersion", "1.20.1");
			dc.setCapability("deviceName","iPhone 12 Pro Max Simulator");
			dc.setCapability("deviceOrientation", "portrait");
			dc.setCapability("platformVersion","14.3");
			dc.setCapability("platformName", "iOS");
			dc.setCapability("browserName", "Safari");
			//Create driver object
			//driver=new IOSDriver(u,dc);
			while(2>1)
			{
				try
				{
					driver=new IOSDriver(u,dc);
					break;
				}
				catch(Exception ex)
				{
				}
			}
		}
		else if(dn.equals("iphone"))
		{
			//Real devices are available in TestObject cloud
		}
		else if(dn.equals("AVD"))
		{
			dc = DesiredCapabilities.android();
			dc.setCapability("appiumVersion", "1.19.2");
			dc.setCapability("deviceName","Samsung Galaxy S9 FHD GoogleAPI Emulator");
			dc.setCapability("deviceOrientation", "portrait");
			dc.setCapability("browserName", "Chrome");
			dc.setCapability("platformVersion", "9.0");
			dc.setCapability("platformName","Android");
			//Create driver object
			//driver=new AndroidDriver(u,dc);
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
		}
		else if(dn.equals("ARD"))
		{
			//Real devices are available in TestObject cloud
		}
		else
		{
			System.out.println("Spelling chusko mundhu ah tarvata execute cheyyi");
			System.exit(0);
		}
		try
		{
			Thread.sleep(5000);
			//Launch site and do login via form filling
			WebDriverWait wait =new WebDriverWait(driver,10);
	        driver.get("http://expenseus.com/user/login");
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("user[email]"))).sendKeys("magnitiait@gmail.com");
	        driver.findElement(By.name("user[password]")).sendKeys("abdulkalam");
	        driver.findElement(By.xpath("//input[@value='Log in']")).click();
	        Thread.sleep(5000);
	        try
	        {
	        	if(driver.findElement(By.linkText("Logout")).isDisplayed())
	        	{
	        		System.out.println("Successful login");
	        	}
	        }
	        catch(Exception exc)
	        {
	        	System.out.println("Unsuccessful login");
	        }
	        //Get and display all cookies
	        Set<Cookie> cl=driver.manage().getCookies();
	        ArrayList<Cookie> ca=new ArrayList<Cookie>(cl);
	        /*for(Cookie c:ca)
	        {
	        	System.out.println(c.getName());
	        }*/
	        for(int i=0;i<ca.size();i++)
	        {
	        	System.out.println(ca.get(i).getName());
	        }
	        //Remember session cookie via Cookie object and delete for browser
	        Cookie loginCookie=driver.manage().getCookieNamed("PHPSESSID");
	        driver.manage().deleteCookieNamed("PHPSESSID");
	        Thread.sleep(20000);
	        //Launch site and do login by adding session cookie to browser
	        System.out.println("Using cookie");
	        driver.manage().addCookie(loginCookie);
	        driver.get("http://expenseus.com");
	        try
	        {
	        	if(driver.findElement(By.linkText("Logout")).isDisplayed())
	        	{
	        		System.out.println("Successful login");
	        	}
	        }
	        catch(Exception exc)
	        {
	        	System.out.println("Unsuccessful login");
	        }
	        Thread.sleep(20000);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//Close app
		driver.close();
	}
}