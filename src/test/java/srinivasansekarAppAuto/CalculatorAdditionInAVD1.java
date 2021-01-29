package srinivasansekarAppAuto;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class CalculatorAdditionInAVD1
{
	public static void main(String[] args) throws Exception
	{
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","emulator-5554");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","4.2.2");
		dc.setCapability("appPackage","com.android.calculator2");
		dc.setCapability("appActivity","com.android.calculator2.Calculator");
		//Start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
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
		//Locate and operate elements
		try
		{
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			driver.findElement(By.xpath("//*[@text='9']")).click();
			driver.findElement(By.xpath("//*[@content-desc='plus']")).click();
			driver.findElement(By.xpath("//*[@text='5']")).click();
			driver.findElement(By.xpath("//*[@content-desc='equals']")).click();
			Thread.sleep(3000);
			String x=driver.findElement(By.xpath("//*[@class='android.widget.EditText']")).getAttribute("text");
			System.out.println(x);
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
