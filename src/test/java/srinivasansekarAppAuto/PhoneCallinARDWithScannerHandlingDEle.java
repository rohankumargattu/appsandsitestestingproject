package srinivasansekarAppAuto;

import java.net.URL;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class PhoneCallinARDWithScannerHandlingDEle 
{
	public static void main(String[] args) throws Exception
	{
		//Give a mobile number
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter contact name to call");
		String cname=sc.nextLine();
		sc.close();
		//Details of app and device(AVD)
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
		//Automation
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='CONTACTS']")));
			driver.findElement(By.xpath("//*[@text='CONTACTS']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[contains(@text,'Search')]")));
			driver.findElement(By.xpath("//android.widget.EditText[contains(@text,'Search')]")).sendKeys(cname);
			Thread.sleep(2000); //Mandatory wait for search results outcome
			try
			{
				if(driver.findElement(By.xpath("//*[@text='No results found']")).isDisplayed())
				{
					System.out.println("No contacts found to call");
				}
			}
			catch(Exception exe)
			{
				List<MobileElement> cnamereslist=driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.samsung.android.contacts:id/cliv_name_textview']"));
				for(int i=0;i<cnamereslist.size();i++)
				{
					String cnameres=cnamereslist.get(i).getAttribute("text");
					if(cname.equalsIgnoreCase(cnameres))
					{
						driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.samsung.android.contacts:id/cliv_name_textview']")).click();
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='SIM card 1 call Button']")));
						driver.findElement(By.xpath("//*[@content-desc='SIM card 1 call Button']")).click();
						Thread.sleep(20000);
						try
						{
							if(driver.findElement(By.xpath("//*[@text='Dialling']")).isDisplayed())
							{
								System.out.println("Call Was not Lifted");
								driver.findElementByAndroidUIAutomator("new UiSelector().description(\"End call\")").click();
							}
						}
						catch(Exception lifted)
						{
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@content-desc,'Keypad')]")));
							System.out.println("Call Was Lifted");
							Thread.sleep(20000);
							driver.findElementByAndroidUIAutomator("new UiSelector().description(\"End call\")").click();
							try
							{
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='CANCEL']")));
								driver.findElement(By.xpath("//*[@text='CANCEL']")).click();
							}
							catch(Exception e)
							{
								System.out.println("No call cost message");
							}
						}
					}
					else
					{
						System.out.println("Does not match with given name");
					}
					break;
				}
			}
		}
		catch(Exception ee)
		{
			System.out.println(ee.getMessage());
		}
		//Close app
		driver.closeApp();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
