package srinivasansekarAppAuto;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class ToastedMessagesAutomation2 
{
	public static void main(String[] args) throws Exception
	{
		//Start appium sever
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		//Get address of appium server
		URL u=new URL("http://0.0.0.0:4723/wd/hub");
		//Define desired capabilities related to device and app
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ce081718334a5b0b05");
	    dc.setCapability("platformName","android");
	    dc.setCapability("platformVersion","8.0.0");
		dc.setCapability("appPackage","com.sec.android.app.popupcalculator");
	    dc.setCapability("appActivity","com.sec.android.app.popupcalculator.Calculator");
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
		//App Automation
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='8']")));
			KeyEvent k=new KeyEvent(AndroidKey.HOME);
			driver.pressKey(k);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@resource-id='com.sec.android.app.launcher:id/iconview_imageView'])[4]")));
			driver.findElement(By.xpath("(//*[@resource-id='com.sec.android.app.launcher:id/iconview_imageView'])[4]")).click();
			//Search for AnyDesk app and click
			while(2>1)
			{
				try
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='AnyDesk']"))).click();
					break;
				}
				catch(Exception exe)
				{
					//Get device screen dimensions
					int width=driver.manage().window().getSize().getWidth();
					int height=driver.manage().window().getSize().getHeight();
					//Swipe right to left
					PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
					Sequence swipe=new Sequence(finger,1);
					Interaction i1=finger.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),(int) (width*0.8),height/2);
					swipe.addAction(i1);
					Interaction i2=finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
					swipe.addAction(i2);
					Interaction i3=finger.createPointerMove(Duration.ofMillis(5000),PointerInput.Origin.viewport(),(int) (width*0.3),height/2);
					swipe.addAction(i3);
					Interaction i4=finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
					swipe.addAction(i4);
					driver.perform(Arrays.asList(swipe));
				}
			}
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id='com.anydesk.anydeskandroid:id/action_bar_status']"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.Toast")));
			String toasted_msg=driver.findElementByXPath("/hierarchy/android.widget.Toast").getText();
			System.out.println(toasted_msg);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		String pn=driver.getCurrentPackage();
		driver.terminateApp(pn);
		driver.launchApp();
		//Close App
		driver.closeApp();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe"); 
	}
}
