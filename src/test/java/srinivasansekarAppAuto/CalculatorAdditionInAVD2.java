package srinivasansekarAppAuto;

import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class CalculatorAdditionInAVD2
{
	public static void main(String[] args) throws Exception
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter input1");
		String x=sc.nextLine();
		System.out.println("Enter input2");
		String y=sc.nextLine();
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","emulator-5554");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","4.2.2");
		dc.setCapability("appPackage","com.android.calculator2");
		dc.setCapability("appActivity","com.android.calculator2.Calculator");
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
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			for(int i=0;i<x.length();i++)
			{
				char d=x.charAt(i);
				driver.findElement(By.xpath("//*[@text='"+d+"']")).click();
			}
			driver.findElement(By.xpath("//*[@content-desc='plus']")).click();
			for(int i=0;i<y.length();i++)
			{
				char d=y.charAt(i);
				driver.findElement(By.xpath("//*[@text='"+d+"']")).click();
			}
			driver.findElement(By.xpath("//*[@content-desc='equals']")).click();
			String z=driver.findElement(By.xpath("//*[@class='android.widget.EditText']")).getAttribute("text");
			if(Integer.parseInt(z)==Integer.parseInt(x)+Integer.parseInt(y))
			{
				System.out.println("Addition test passed");
			}
			else
			{
				System.out.println("Addition test failed");
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
