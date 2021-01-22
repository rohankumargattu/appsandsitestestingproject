package srinivasansekarAppAuto;

import java.net.URL;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class CalculatorAppTestingAVD
{
	public static void main(String[] args) throws Exception
	{
		//Enter values for Mathematical operation
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter input 1");
		String i1=sc.nextLine();
		System.out.println("Enter input 2");
		String i2=sc.nextLine();
		System.out.println("Enter operation as plus/minus/times/divide");
		String op=sc.nextLine();
		sc.close();
		AndroidDriver driver = null;
		if(op.equals("plus") || op.equals("minus") || op.equals("times") || op.equals("divide"))
		{
			//Start appium server programmatically
			Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
			//Get address of appium Server
			URL u=new URL("http://127.0.0.1:4723/wd/hub");
			//Desired Capabilities of app and device(AVD)
			DesiredCapabilities dc=new DesiredCapabilities();
			dc.setCapability(CapabilityType.BROWSER_NAME,"");
			dc.setCapability("deviceName","emulator-5554");
			dc.setCapability("platformName","android");
			dc.setCapability("platformVersion","5.1.1");
			dc.setCapability("appPackage","com.android.calculator2");
			dc.setCapability("appActivity","com.android.calculator2.Calculator");
			//Create driver object
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
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='9']")));
				//Enter input 1
				for(int i=0;i<i1.length();i++)
				{
					char c=i1.charAt(i);
					if(c=='-')
					{
						driver.findElement(By.xpath("//*[@content-desc='minus']")).click();
					}
					else
					{
						driver.findElement(By.xpath("//*[@text='"+c+"']")).click();
					}
				}
				
				//Click Math operation button
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='"+op+"']"))).click();
				
				//Enter input 2
				for(int i=0;i<i2.length();i++)
				{
					char c=i2.charAt(i);
					if(c=='-')
					{
						driver.findElement(By.xpath("//*[@content-desc='minus']")).click();
					}
					else
					{
						driver.findElement(By.xpath("//*[@text='"+c+"']")).click();
					}
				}
				
				//Click equals button
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='equals']"))).click();
				
				int result=Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@resource-id='com.android.calculator2:id/formula']"))).getText());
				//int result=Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@resource-id='com.android.calculator2:id/formula']"))).getAttribute("text"));
				int x=Integer.parseInt(i1);
				int y=Integer.parseInt(i2);
				//Validations
				if(op.equals("plus"))
				{
					if(result==(x+y))
					{
						System.out.println("Addition test passed");
					}
					else
					{
						System.out.println("Addition test failed");
					}
				}
				else if(op.equals("minus"))
				{
					if(result==(x-y))
					{
						System.out.println("Subtraction test passed");
					}
					else
					{
						System.out.println("Subtraction test failed");
					}
				}
				else if(op.equals("times"))
				{
					if(result==(x*y))
					{
						System.out.println("Multiplication test passed");
					}
					else
					{
						System.out.println("Multiplication test failed");
					}
				}
				else
				{
					if(result==(x/y))
					{
						System.out.println("Division test passed");
					}
					else
					{
						System.out.println("Division test failed");
					}
				}
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
		}
		else
		{
			System.out.println("School ki poledha..? Em cheyyalo correct ga cheppu...");
			System.exit(0);
		}
		
		//Close app
		driver.closeApp();	
		
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
