package srinivasansekarSiteAuto;

import java.net.URL;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonUI 
{
	public static void main(String[] args) throws Exception
	{
		//Get platform name
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter platform as computer/mobile");
		String p=sc.nextLine();
		//Object Creation
		RemoteWebDriver driver=null;
		if(p.equalsIgnoreCase("computer"))
		{
			System.out.println("Enter browser name");
			String b=sc.nextLine();
			if(b.equalsIgnoreCase("chrome"))
			{
				//System.setProperty("webdriver.chrome.driver","E:\\Automation\\chromedriver\\chromedriver.exe");
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
			}
			else if(b.equalsIgnoreCase("firefox"))
			{
				//System.setProperty("webdriver.gecko.driver","E:\\Automation\\geckodriver\\geckodriver.exe");
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
			}
			else if(b.equalsIgnoreCase("edge"))
			{
				//System.setProperty("webdriver.edge.driver","E:\\Automation\\edgedriver\\edgedriver.exe");
				WebDriverManager.edgedriver().setup();
				driver=new EdgeDriver();
			}
			else if(b.equalsIgnoreCase("ie"))
			{
				//System.setProperty("webdriver.ie.driver","E:\\Automation\\IEDriverServer\\iedriverserver.exe");
				WebDriverManager.iedriver().setup();
				driver=new InternetExplorerDriver();
			}
			else if(b.equalsIgnoreCase("opera"))
			{
				//System.setProperty("webdriver.opera.driver","E:\\Automation\\operadriver\\operadriver.exe");
				WebDriverManager.operadriver().setup();
				driver=new OperaDriver();
			}
			else
			{
				System.out.println("Wrong browser name");
				System.exit(0);
			}
			//Maximize
			driver.manage().window().maximize();
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
		}
		else
		{
			System.out.println("Wrong Platform name");
			System.exit(0);
		}
		//Automation
		//Launch site
		driver.get("http://demo.guru99.com/test/newtours/");
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("REGISTER")));
		driver.findElement(By.linkText("REGISTER")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='firstName']")));
		driver.findElement(By.xpath("//*[@name='firstName']")).sendKeys("rohan");
		driver.findElement(By.xpath("//*[@name='lastName']")).sendKeys("kumar");
		driver.findElement(By.xpath("//*[@name='phone']")).sendKeys("9030957386");
		driver.findElement(By.xpath("//*[@name='userName']")).sendKeys("gatturohankumar@gmail.com");
		driver.findElement(By.xpath("//*[@name='address1']")).sendKeys("207");
		driver.findElement(By.xpath("//*[@name='address2']")).sendKeys("India");
		driver.findElement(By.xpath("//*[@name='city']")).sendKeys("Hyderabad");
		driver.findElement(By.xpath("//*[@name='state']")).sendKeys("Telangana");
		driver.findElement(By.xpath("//*[@name='postalCode']")).sendKeys("500035");
		WebElement e=driver.findElement(By.xpath("//*[@name='country']"));
		Select s=new Select(e);
		s.selectByVisibleText("INDIA");
		driver.findElement(By.xpath("//*[@name='email']")).sendKeys("gatturohankumar");
		driver.findElement(By.xpath("//*[@name='password']")).sendKeys("gatturohankumar134");
		driver.findElement(By.xpath("//*[@name='confirmPassword']")).sendKeys("gatturohankumar134");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='register']")));
		driver.findElement(By.xpath("//*[@name='register']")).click();
		Thread.sleep(5000);
		if(p.equalsIgnoreCase("computer"))
		{
			//Close site
			driver.close();
		}
		else
		{
			//Close site
			driver.close();
			//Stop appium server
			Runtime.getRuntime().exec("taskkill /F /IM node.exe");
			Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
		}
	}
}
