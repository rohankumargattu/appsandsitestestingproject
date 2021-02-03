package mobilegestures;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class MGUsingAppiumHorizontalSwipeSliders 
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
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Slider']"))).click();
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
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Slider']")));
			//Slider 1
			//Swipe left to right and right to left horizontally
			//Get element dimensions
			MobileElement ele1=(MobileElement) driver.findElement(By.xpath("//*[@content-desc='slider']"));
			int width1=ele1.getSize().getWidth();
			int height1=ele1.getSize().getHeight();
			int x1=ele1.getLocation().getX();
			int y1=ele1.getLocation().getY();
			ta.press(ElementOption.point(x1,y1+width1/2)).waitAction(wo).moveTo(ElementOption.point((int) (x1+width1*0.5),y1+width1/2)).release().perform();
			ta.press(ElementOption.point((int) (x1+width1*0.5),y1+width1/2)).waitAction(wo).moveTo(ElementOption.point(x1,y1+width1/2)).release().perform();
			
			//Slider 2
			//Swipe left to right and right to left horizontally
			//Get element dimensions
			MobileElement ele2=(MobileElement) driver.findElement(By.xpath("//*[@content-desc='slider1']"));
			int width2=ele2.getSize().getWidth();
			int height2=ele2.getSize().getHeight();
			int x2=ele2.getLocation().getX();
			int y2=ele2.getLocation().getY();
			ta.press(ElementOption.point(x2,y2+width2/2)).waitAction(wo).moveTo(ElementOption.point((int) (x2+width2*0.5),y2+width2/2)).release().perform();
			ta.press(ElementOption.point((int) (x2+width2*0.5),y2+width2/2)).waitAction(wo).moveTo(ElementOption.point(x2,y2+width2/2)).release().perform();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		Thread.sleep(5000);
		//Close app
		driver.closeApp();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
