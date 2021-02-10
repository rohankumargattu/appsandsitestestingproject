package srinivasansekarAppAuto;

import java.net.URL;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class AppUpgradeFunctionalRegressionTest
{
	public static void main(String[] args) throws Exception
	{
		//Enter data to submit
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter some data into text box in echobox");
		String text=sc.nextLine();
		sc.close();
		//Start appium sever
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		//Get appium server address
		URL u=new URL("http://0.0.0.0:4723/wd/hub");
		//Define desired capabilities related to device and app
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ce081718334a5b0b05");
	    dc.setCapability("platformName","android");
	    dc.setCapability("platformVersion","8.0.0");
	    dc.setCapability("autoGrantPermissions","true"); //extra 
	  	dc.setCapability("adbExecTimeout","50000"); //extra in msec 
		dc.setCapability("app","E:\\Automation\\TheApp-v1.9.0.apk");
		dc.setCapability("uninstallOtherPackages","io.cloudgrey.the_app");
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
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Echo Box']"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Say something']"))).sendKeys(text);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Save']"))).click();
			String msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='"+text+"']"))).getText();
			//Install new version of same app, without uninstalling old version
			//(this is called as app upgradation)
			driver.installApp("E:\\Automation\\TheApp-v1.10.0.apk");
			driver.activateApp("io.cloudgrey.the_app");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Echo Box']"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Save']")));
			String uamsg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='"+text+"']"))).getText();
			if(msg.equals(uamsg))
			{
				System.out.println("App Upgradation Functional Regression passed");
			}
			else
			{
				System.out.println("App Upgradation Functional Regression failed");
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//Close App
		driver.closeApp();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe"); 
	}
}
