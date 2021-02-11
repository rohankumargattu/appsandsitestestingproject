package srinivasansekarAppAuto;

import java.net.URL;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.ElementOption;

public class TikTok
{
	public static void main(String[] args) throws Exception
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("How many no of videos from tiktok you want to watch..?");
		int n=sc.nextInt();
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ce081718334a5b0b05");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.0.0");
		dc.setCapability("appPackage","com.voadqareactnative");
		dc.setCapability("appActivity","com.vodqareactnative.MinActivity");
		//Start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723\"");
		//Get address of appium Server
		URL u=new URL("http://0.0.0.0:4723/wd/hub");
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
		//Automation
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='LOG IN']")));
			KeyEvent k=new KeyEvent(AndroidKey.HOME);
			driver.pressKey(k);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@content-desc,'TikTok')]")));
			driver.findElement(By.xpath("//*[contains(@content-desc,'TikTok')]")).click();
			Thread.sleep(8000);
			for(int i=1;i<=n;i++)
			{
				Thread.sleep(20000);
				TouchAction ta=new TouchAction(driver);
				int w=driver.manage().window().getSize().getWidth();
				int h=driver.manage().window().getSize().getHeight();
				int x1=w/2;
				int y1=(int) (h*0.8);
				int x2=w/2;
				int y2=(int) (h*0.2);
				ta.press(ElementOption.point(x1,y1)).moveTo(ElementOption.point(x2,y2)).release().perform();
			}
			driver.pressKey(k);
			driver.launchApp();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='LOG IN']")));
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//Close app
		driver.closeApp();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
