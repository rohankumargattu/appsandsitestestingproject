package srinivasansekarAppAuto;

import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class XpathTesting
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
			/*wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='android.widget.Button'][@instance='7']")));
			driver.findElement(By.xpath("//*[@class='android.widget.Button'][@instance='7']")).click();*/
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='android.widget.Button'])[7]")));
			List<WebElement> l=driver.findElements(By.xpath("//*[@class='android.widget.Button'][@index='1']"));
			System.out.println(l.size());
			Thread.sleep(5000);
			l.get(2).click();
			Thread.sleep(5000);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		driver.closeApp();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
