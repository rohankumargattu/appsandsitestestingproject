package srinivasansekarAppAuto;

import java.net.URL;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class PhoneCallinAVD 
{
	public static void main(String[] args) throws Exception
	{
		//Give a mobile number
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a mobile number to dial");
		String mbno=sc.nextLine();
		sc.close();
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","emulator-5554");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","4.2.2");
		dc.setCapability("appPackage","com.android.contacts");
		dc.setCapability("appActivity","com.android.contacts.activities.DialtactsActivity");
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
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='backspace']")));
			for(int i=0;i<mbno.length();i++)
			{
				char d=mbno.charAt(i);
				String y="";
				switch(d)
				{
					case '0':
						y="zero";
						break;
					case '1':
						y="one";
						break;
					case '2':
						y="two";
						break;
					case '3':
						y="three";
						break;
					case '4':
						y="four";
						break;
					case '5':
						y="five";
						break;
					case '6':
						y="six";
						break;
					case '7':
						y="seven";
						break;
					case '8':
						y="eight";
						break;
					case '9':
						y="nine";
						break;
				}
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='"+y+"']")));
				driver.findElement(By.xpath("//*[@content-desc='"+y+"']")).click();
			} //for loop closing
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='dial']")));
			driver.findElement(By.xpath("//*[@content-desc='dial']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='0:20']")));
			driver.findElement(By.xpath("//*[@content-desc='End']")).click();
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
