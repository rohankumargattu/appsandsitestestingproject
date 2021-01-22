package srinivasansekarAppAuto;

import java.net.URL;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class PhoneCallinARD
{
	public static void main(String[] args) throws Exception
	{
		//Give a mobile number
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter 1st 10 digit mobile number to dial");
		String mbno1=sc.nextLine();
		System.out.println("Enter 2nd 10 digit mobile number to dial");
		String mbno2=sc.nextLine();
		sc.close();
		//Details of app and device(ARD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ce081718334a5b0b05");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.0.0");
		dc.setCapability("appPackage","com.samsung.android.contacts");
		dc.setCapability("appActivity","com.android.dialer.DialtactsActivity");
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
		//App automation
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='Keypad']")));
			driver.findElement(By.xpath("//*[@content-desc='Keypad']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='5']")));
			for(int i=0;i<mbno1.length();i++)
			{
				char d=mbno1.charAt(i);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='"+d+"']")));
				driver.findElement(By.xpath("//*[@content-desc='"+d+"']")).click();
			}
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='Call button']")));
			driver.findElement(By.xpath("//*[@content-desc='Call button']")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@text='Dialling']")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@content-desc,'Add call')]")));
			driver.findElement(By.xpath("//*[contains(@content-desc,'Add call')]")).click();
			for(int i=0;i<mbno2.length();i++)
			{
				char d=mbno2.charAt(i);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='"+d+"']")));
				driver.findElement(By.xpath("//*[@content-desc='"+d+"']")).click();
			}
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='Call button']")));
			driver.findElement(By.xpath("//*[@content-desc='Call button']")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@text='Dialling']")));
			Thread.sleep(5000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='Merge']")));
			driver.findElement(By.xpath("//*[@content-desc='Merge']")).click();
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='02:00']")));
			Thread.sleep(40000);
			try
			{
				if(driver.findElement(By.xpath("//*[@content-desc='End call']")).isDisplayed())
				{
					driver.findElement(By.xpath("//*[@content-desc='End call']")).click();
				}
			}
			catch(Exception exc)
			{
			}
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='CANCEL']")));
			driver.findElement(By.xpath("//*[@text='CANCEL']")).click();
			//Close app
			driver.closeApp();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
