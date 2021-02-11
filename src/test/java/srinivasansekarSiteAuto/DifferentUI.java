package srinivasansekarSiteAuto;

import java.net.URL;
import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Button;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DifferentUI 
{
	public static void main(String[] args) throws Exception
	{
		//Get Platform as computer.mobile
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter platform like computer/mobile");
		String p=sc.nextLine();
		//Automation
		if(p.equalsIgnoreCase("computer"))
		{
			//System.setProperty("webdriver.chrome.driver","E:\\Automation\\chromedriver\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://www.youtube.com/");
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search_query")));
			driver.findElement(By.name("search_query")).sendKeys("rangamma mangamma");
			Thread.sleep(5000);
			driver.findElement(By.xpath("(//button[@aria-label='Search'])[1]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Rangamma Mangamma Video Song")));
			driver.findElement(By.partialLinkText("Rangamma Mangamma Video Song")).click();
			Thread.sleep(5000);
			//Video icons automation
			Screen s=new Screen();
			if(s.exists("skipad.png")!=null)
			{
				s.click();
			}
			else if(s.exists("skipads.png")!=null) 
			{
				s.click();
			}
			if(s.exists("nothanks.png")!=null)
			{
				s.click();
			}
			//Move mouse pointer to video body
			Location l=new Location(300,300);
			s.wheel(l,Button.LEFT,0);
			s.click("pause.png");
			Thread.sleep(10000);
			s.wheel(l,Button.LEFT,0);
			s.click("play.png");
			Thread.sleep(5000);
			s.wheel(l,Button.LEFT,0);
			s.mouseMove("volume.png");
			Thread.sleep(3000);
			Match e=s.find("bubble.png");
			int x=e.getX();
			int y=e.getY();
			Location l2=new Location(x-20,y);
			s.dragDrop(e,l2);
			Thread.sleep(5000);
			Location l3=new Location(x+10,y);
			s.dragDrop(e,l3);
			Thread.sleep(5000);
			//Close site
			driver.close();	
		}
		else if(p.equalsIgnoreCase("mobile"))
		{
			//Details of app and device
			DesiredCapabilities dc=new DesiredCapabilities();
			dc.setCapability(CapabilityType.BROWSER_NAME,"chrome");
			dc.setCapability("deviceName","ce081718334a5b0b05");
			dc.setCapability("platformName","android");
			dc.setCapability("platformVersion","8.0.0");
			//Start appium server
			Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723\"");
			//Get address of appium Server
			URL u=new URL("http://0.0.0.0:4723/wd/hub");
			AndroidDriver driver;
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
			//Launch site
			driver.get("https://www.youtube.com/");
			String context=driver.getContext();
			System.out.println("Current context is "+context);
			driver.context("NATIVE_APP");
			Thread.sleep(5000);
			//Search for Video
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='Search YouTube']")));
			driver.findElement(By.xpath("//*[@text='Search YouTube']")).click();
			try
			{
				if(driver.findElement(By.xpath("//*[@class='android.widget.EditText']")).isDisplayed())
				{
					driver.findElement(By.xpath("//*[@class='android.widget.EditText']")).sendKeys("nageswara rao testing tools");
				}
			}
			catch(Exception ex)
			{
				driver.findElement(By.xpath("//*[@resource-id='com.android.chrome:id/tab_switcher_button']")).click();
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@text='Search YouTube']")).click();
				driver.findElement(By.xpath("//*[@class='android.widget.EditText']")).sendKeys("nageswara rao testing tools");
			}
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@text='Search YouTube']")).click();
			//Start video
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@text,'What should a Modern day Tester become')]")));
			driver.findElement(By.xpath("//*[contains(@text,'What should a Modern day Tester become')]")).click();
			Thread.sleep(10000);
			//Pause video
			WebElement e=driver.findElementByAndroidUIAutomator("new UiSelector().text(\"video\")");
			TouchAction ta1=new TouchAction(driver);
			ta1.tap(ElementOption.element(e));
			WaitOptions wo=new WaitOptions();
			wo.withDuration(Duration.ofMillis(100));
			TouchAction ta2=new TouchAction(driver);
			ta2.waitAction(wo).tap(ElementOption.element(e));
			MultiTouchAction ma=new MultiTouchAction(driver);
			ma.add(ta1).add(ta2).perform();
			Thread.sleep(10000);
			//Resume video
			driver.findElement(By.xpath("//*[@text='play']")).click();
			Thread.sleep(10000);
			//Close app
			driver.closeApp();
			//Stop appium server
			Runtime.getRuntime().exec("taskkill /F /IM node.exe");
			Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");	
		}
		else
		{
			System.out.println("Wrong Platform name");
			System.exit(0);
		}
	}
}
