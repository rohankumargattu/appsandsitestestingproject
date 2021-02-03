package mobilegestures;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class MGUsingSeleniumPinch 
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
		dc.setCapability("appPackage","com.vodqareactnative");
		dc.setCapability("appActivity","com.vodqareactnative.MainActivity");
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
		//App Automation
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='LOG IN']"))).click();
			while(2>1)
			{
				try
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Photo View']"))).click();
					break;
				}
				catch(Exception exe)
				{
					//Get device dimensions
					int width=driver.manage().window().getSize().getWidth();
					int height=driver.manage().window().getSize().getHeight();
					//Swipe Logic
					PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
					Sequence swipe=new Sequence(finger,1);
					Interaction i1=finger.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),width/2,(int) (height*0.8));
					swipe.addAction(i1);
					Interaction i2=finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
					swipe.addAction(i2);
					Interaction i3=finger.createPointerMove(Duration.ofMillis(1000),PointerInput.Origin.viewport(),width/2,(int) (height*0.3));
					swipe.addAction(i3);
					Interaction i4=finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
					swipe.addAction(i4);
					driver.perform(Arrays.asList(swipe));
				}
			}
			
			//Zooming
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='android.widget.ImageView']")));
			MobileElement ele=(MobileElement) driver.findElement(By.xpath("//*[@class='android.widget.ImageView']"));
			Point source=ele.getCenter();
			Dimension dim=ele.getSize();
			PointerInput finger1=new PointerInput(PointerInput.Kind.TOUCH,"botanavelu");
			PointerInput finger2=new PointerInput(PointerInput.Kind.TOUCH,"choopuduvelu");
			Sequence s1=new Sequence(finger1,1);
			Sequence s2=new Sequence(finger2,1);
			Interaction i1=finger1.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),source.x,source.y);
			s1.addAction(i1);
			Interaction i2=finger2.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),source.x,source.y);
			s2.addAction(i2);
			Interaction i3=finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
			s1.addAction(i3);
			Interaction i4=finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
			s2.addAction(i4);
			Interaction i5=finger1.createPointerMove(Duration.ofMillis(2000),PointerInput.Origin.viewport(),source.x,source.y-dim.height/2-10);
			s1.addAction(i5);
			Interaction i6=finger2.createPointerMove(Duration.ofMillis(2000),PointerInput.Origin.viewport(),source.x,source.y+dim.height/2-10);
			s2.addAction(i6);
			Interaction i7=finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
			s1.addAction(i7);
			Interaction i8=finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
			s2.addAction(i8);
			driver.perform(Arrays.asList(s1,s2));
			
			
			/*PointerInput finger1=new PointerInput(PointerInput.Kind.TOUCH,"botanavelu");
			Sequence s1=new Sequence(finger1,1);
			Interaction i1=finger1.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),source.x,source.y);
			s1.addAction(i1);
			Interaction i3=finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
			s1.addAction(i3);
			Interaction i5=finger1.createPointerMove(Duration.ofMillis(1000),PointerInput.Origin.viewport(),source.x,source.y-dim.height/2-10);
			s1.addAction(i5);
			Interaction i7=finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
			s1.addAction(i7);
			
			
			PointerInput finger2=new PointerInput(PointerInput.Kind.TOUCH,"choopuduvelu");
			Sequence s2=new Sequence(finger2,1);
			Interaction i2=finger2.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),source.x,source.y);
			s2.addAction(i2);
			Interaction i4=finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
			s2.addAction(i4);
			Interaction i6=finger2.createPointerMove(Duration.ofMillis(1000),PointerInput.Origin.viewport(),source.x,source.y+dim.height/2-10);
			s2.addAction(i6);
			Interaction i8=finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
			s2.addAction(i8);
			
			driver.perform(Arrays.asList(s1,s2));*/
			
			Thread.sleep(3000);
			//Pinch
			PointerInput finger3=new PointerInput(PointerInput.Kind.TOUCH,"napeddavelu");
			PointerInput finger4=new PointerInput(PointerInput.Kind.TOUCH,"nachinnavelu");
			Sequence s3=new Sequence(finger3,1);
			Sequence s4=new Sequence(finger4,1);
			Interaction i9=finger3.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),source.x,source.y-dim.getHeight()/2+10);
			s3.addAction(i9);
			Interaction i10=finger4.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),source.x,source.y+dim.getHeight()/2-10);
			s4.addAction(i10);
			Interaction i11=finger3.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
			s3.addAction(i11);
			Interaction i12=finger4.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
			s4.addAction(i12);
			Interaction i13=finger3.createPointerMove(Duration.ofMillis(2000),PointerInput.Origin.viewport(),source.x,source.y);
			s3.addAction(i13);
			Interaction i14=finger4.createPointerMove(Duration.ofMillis(2000),PointerInput.Origin.viewport(),source.x,source.y);
			s4.addAction(i14);
			Interaction i15=finger3.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
			s3.addAction(i15);
			Interaction i16=finger4.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
			s4.addAction(i16);
			driver.perform(Arrays.asList(s3,s4));
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		Thread.sleep(5000);
		//Close app
		driver.closeApp();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
