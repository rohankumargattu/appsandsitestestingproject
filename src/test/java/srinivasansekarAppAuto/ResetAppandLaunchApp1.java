package srinivasansekarAppAuto;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

public class ResetAppandLaunchApp1
{
	public static void main(String[] args) throws Exception
	{
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ce081718334a5b0b05");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.0.0");
		dc.setCapability("appPackage","com.sec.android.app.popupcalculator");
		dc.setCapability("appActivity","com.sec.android.app.popupcalculator.Calculator");
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
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='9']")));
			driver.findElement(By.xpath("//*[@text='9']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='Plus']")));
			driver.findElement(By.xpath("//*[@content-desc='Plus']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='5']")));
			driver.findElement(By.xpath("//*[@text='5']")).click();
			Thread.sleep(5000);
			/*wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='Equal']")));
			driver.findElement(By.xpath("//*[@content-desc='Equal']")).click();
			String x=driver.findElement(By.xpath("//*[@class='android.widget.EditText']")).getAttribute("text");*/
			driver.activateApp("com.paradisebiryani.foodcourt");
			Thread.sleep(5000);
			driver.launchApp();
			Thread.sleep(5000);
			driver.runAppInBackground(Duration.ofSeconds(15));
			Thread.sleep(3000);
			driver.terminateApp("com.paradisebiryani.foodcourt.ui.home.HomeActivity");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='9']")));
			Activity a=new Activity("com.samsung.android.contacts","com.android.dialer.DialtactsActivity");
			driver.startActivity(a);
			Thread.sleep(5000);
			driver.resetApp();
			Thread.sleep(5000);
			driver.runAppInBackground(Duration.ofSeconds(15));
			Thread.sleep(3000);
			driver.terminateApp("com.samsung.android.contacts");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='9']")));
			Thread.sleep(3000);
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
