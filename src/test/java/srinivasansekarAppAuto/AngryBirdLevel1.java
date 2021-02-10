package srinivasansekarAppAuto;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.Base64;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;

public class AngryBirdLevel1 
{
	public static void main(String[] args) throws Exception
	{
		//Download app from https://apkpure.com/angry-birds-classic/com.rovio.angrybirds
		//Install app into AVD via "adb -s xxxxxx install path-of-apk-file"
		//Capture images of required elements via snipping tool or short keys
		//Save images in project folder with ".png" extension
		//Start appium sever
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		//Get appium server address
		URL u=new URL("http://0.0.0.0:4723/wd/hub");
		//Define desired capabilities related to device and app
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability("deviceName","ce081718334a5b0b05");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.0.0");
		dc.setCapability("appPackage","com.rovio.angrybirds");
		dc.setCapability("appActivity","com.rovio.fusion.App");
		//dc.setCapability("orientation","LANDSCAPE");
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
		//Game automation
		try
		{	
			Thread.sleep(12000);
			findImageWithOptimization(driver,"checkmark.png").click();
			Thread.sleep(3000);
			WebElement birdEl=findImageWithOptimization(driver,"red-bird-in-slingshot.png");
			shootBird(driver,birdEl,-280,140);
			Thread.sleep(14000);
			findImageWithOptimization(driver,"level-cleared-three-stars.png");
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//Close App
		driver.quit();
		//Stop appium server
		//Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		//Runtime.getRuntime().exec("taskkill /F /IM cmd.exe"); 
	}

	private static WebElement findImageWithOptimization(AndroidDriver driver,String imageName) throws Exception 
	{
		File f=new File(imageName);
		Path refImgPath =f.toPath();
		byte[] b=Files.readAllBytes(refImgPath);
		String imageData=Base64.getEncoder().encodeToString(b);
		WebElement el=null;
		double max=1.0;
		double min=0.0;
		double stopsearch=0.05; //Zeno's paradox
		double check=0;
		NotFoundException notFound=null; //declare object to an exception class
		driver.setSetting(Setting.CHECK_IMAGE_ELEMENT_STALENESS,false);
		while(Math.abs(max-min)>stopsearch) 
		{
			check=(max+min)/2;
			driver.setSetting(Setting.IMAGE_MATCH_THRESHOLD,check);
			try 
			{
				el=driver.findElementByImage(imageData);
				min=check;
			} 
			catch(NotFoundException err) 
			{
				max=check;
				notFound=err;
			}
		}
		if (el!=null) 
		{
			System.out.println(imageName+" was found at the highest threshold of:"+check);
			return(el);	//Method Terminate here when this statement executes
		}
		System.out.println(imageName+" could not be found even at a low threshold:"+check);
		throw(notFound);
	}

	private static void shootBird(AndroidDriver driver, WebElement birdEl,int xOffset,int yOffset)
	{
		Rectangle rect=birdEl.getRect();
		Point start=new Point(rect.x+rect.width/2,rect.y+rect.height/2);
		Point end=start.moveBy(xOffset,yOffset);
		PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
		Sequence shoot=new Sequence(finger, 0);
		shoot.addAction(finger.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),start.x,start.y));
		shoot.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		shoot.addAction(finger.createPointerMove(Duration.ofMillis(750),PointerInput.Origin.viewport(),end.x,end.y));
		shoot.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(shoot));
	}
}