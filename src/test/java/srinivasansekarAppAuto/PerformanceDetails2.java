package srinivasansekarAppAuto;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class PerformanceDetails2
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
		dc.setCapability("appPackage","com.sec.android.app.popupcalculator");
		dc.setCapability("appActivity","com.sec.android.app.popupcalculator.Calculator");
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
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='7']")));
			driver.findElement(By.xpath("//*[@text='7']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='4']")));
			driver.findElement(By.xpath("//*[@text='4']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='5']")));
			driver.findElement(By.xpath("//*[@text='5']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='8']")));
			driver.findElement(By.xpath("//*[@text='8']")).click();
			//Get Performance attribute names
			List<String> pa=driver.getSupportedPerformanceDataTypes();
			for(int i=0;i<pa.size();i++)
			{
				System.out.print(pa.get(i) +"\t");
			}
			System.out.println();
			/*ArrayList<String> a=new ArrayList<String>(driver.getSupportedPerformanceDataTypes());
			for(int i=0;i<a.size();i++)
			{
				System.out.print(a.get(i) +"\t");
			}
			System.out.println();*/
			
			//Get Performance data
			System.out.println("CPU Information");
			List<List<Object>> ci=driver.getPerformanceData("com.sec.android.app.popupcalculator","cpuinfo",3000);
			for(int i=0;i<ci.size();i++)
			{
				for(int j=0;j<ci.get(i).size();j++)
				{
					try
					{
						System.out.print(ci.get(i).get(j).toString() +"\t");
					}
					catch(Exception exe)
					{
						System.out.print("0");
					}
				}
				System.out.println();
			}
			
			//Get Performance data
			System.out.println("Memory Information");
			List<List<Object>> mi=driver.getPerformanceData("com.sec.android.app.popupcalculator","memoryinfo",3000);
			for(int i=0;i<mi.size();i++)
			{
				for(int j=0;j<mi.get(i).size();j++)
				{
					try
					{
						System.out.print(mi.get(i).get(j).toString() +"\t");
					}
					catch(Exception exe)
					{
						System.out.print("0");
					}
				}
				System.out.println();
			}
			
			//Get Performance data
			System.out.println("Battery Information");
			List<List<Object>> bi=driver.getPerformanceData("com.sec.android.app.popupcalculator","batteryinfo",3000);
			for(int i=0;i<bi.size();i++)
			{
				for(int j=0;j<bi.get(i).size();j++)
				{
					try
					{
						System.out.print(bi.get(i).get(j).toString() +"\t");
					}
					catch(Exception exe)
					{
						System.out.print("0");
					}
				}
				System.out.println();
			}
			
			//Get Performance data
			System.out.println("Network Information");
			List<List<Object>> ni=driver.getPerformanceData("com.sec.android.app.popupcalculator","networkinfo",3000);
			for(int i=0;i<ni.size();i++)
			{
				for(int j=0;j<ni.get(i).size();j++)
				{
					try
					{
						System.out.print(ni.get(i).get(j).toString() +"\t");
					}
					catch(Exception exe)
					{
						System.out.print("0");
					}
				}
				System.out.println();
			}
			
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
