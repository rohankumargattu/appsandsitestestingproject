package mobilegestures;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class MGUsingAppiumDoubleTap 
{
	public static void main(String[] args) throws Exception
	{
		//Start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//Get address of appium Server
		URL u=new URL("http://127.0.0.1:4723/wd/hub");
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ce081718334a5b0b05");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.0.0");
		dc.setCapability("appPackage","com.vodqareactnative");
		dc.setCapability("appActivity","com.vodqareactnative.MainActivity");
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
		//App Automation
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='LOG IN']"))).click();
			TouchAction ta=new TouchAction(driver);
			WaitOptions wo=new WaitOptions();
			wo.withDuration(Duration.ofMillis(5000));
			while(2>1)
			{
				try
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Double Tap']"))).click();
					break;
				}
				catch(Exception exe)
				{
					//Get device dimensions
					int width=driver.manage().window().getSize().getWidth();
					int height=driver.manage().window().getSize().getHeight();
					//Swipe Logic
					ta.press(ElementOption.point(width/2,(int) (height*0.8))).waitAction(wo).moveTo(ElementOption.point(width/2,(int) (height*0.2))).release().perform();
				}
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Double Tap Me']")));
			MobileElement ele=(MobileElement) driver.findElement(By.xpath("//*[@text='Double Tap Me']"));
			WaitOptions wo1=new WaitOptions();
			wo1.withDuration(Duration.ofMillis(300));
			TouchAction ta1=new TouchAction(driver);
			ta1.tap(ElementOption.element(ele));
			TouchAction ta2=new TouchAction(driver);
			ta2.tap(ElementOption.element(ele));
			MultiTouchAction ma=new MultiTouchAction(driver);
			ma.add(ta1).add(ta2).perform();
			//Validations
			try
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Double tap successful!']")));
				if(driver.findElement(By.xpath("//*[@text='Double tap successful!']")).isDisplayed())
				{
					System.out.println("Double tap test passed");
					driver.findElement(By.xpath("//*[@text='OK']")).click();
				}
			}
			catch(Exception e)
			{
				System.out.println("Double tap test failed");
				SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
				Date dt=new Date();
				String fname=sf.format(dt);
				File src=driver.getScreenshotAs(OutputType.FILE);
				File dest=new File(fname+".png");
				FileHandler.copy(src, dest);
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
