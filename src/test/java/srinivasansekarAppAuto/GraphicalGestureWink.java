package srinivasansekarAppAuto;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class GraphicalGestureWink
{
	public static void main(String[] args) throws Exception
	{
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ce081718334a5b0b05");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.0.0");
		dc.setCapability("appPackage","com.example.android.apis");
		dc.setCapability("appActivity","com.example.android.apis.graphics.FingerPaint");
		//Start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
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
		Thread.sleep(2000);
		int[] xs= {350,800,350,800,350,800,350,800};
		int[] ys= {600,600,1000,1000,1400,1400,1800,1800};
		//int[] xs= {350};  //just to check with 1 face
		//int[] ys= {600};  //just to check with 1 face
		for(int i=0;i<8;i++)
		{
			Point head=new Point(xs[i],ys[i]);
			Point lefteye=head.moveBy(-50,-50);
			Point righteye=head.moveBy(50,-50);
			Point mouth=head.moveBy(0, 50);
			drawCircle(driver,head,150,30,"full");
			drawCircle(driver,lefteye,20,2,"wink");
			drawCircle(driver,righteye,20,20,"full");
			drawCircle(driver,mouth,40,20,"half");
		}
		//Close app
		driver.closeApp();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
	public static void drawCircle(AndroidDriver driver,Point origin,double radius,int steps,String shape)
	{
		Point firstpoint=new Point((int)(origin.x+radius),origin.y);
		PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
		Sequence circle=new Sequence(finger,1);
		circle.addAction(finger.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),firstpoint.x,firstpoint.y));
		circle.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		for(int i=1;i<=steps;i++)
		{
			if(shape.equalsIgnoreCase("wink"))
			{
				double theta=Math.PI*((double)i/steps);
				int x=(int)Math.floor(Math.cos(theta)*radius);
				int y=(int)Math.floor(Math.sin(theta)*radius);
				Point point=new Point(origin.x+x,origin.y+y);
				circle.addAction(finger.createPointerMove(Duration.ofMillis(20),PointerInput.Origin.viewport(),point.x,point.y));
			}
			else if(shape.equalsIgnoreCase("half"))
			{
				double theta=Math.PI*((double)i/steps);
				int x=(int)Math.floor(Math.cos(theta)*radius);
				int y=(int)Math.floor(Math.sin(theta)*radius);
				Point point=new Point(origin.x+x,origin.y+y);
				circle.addAction(finger.createPointerMove(Duration.ofMillis(20),PointerInput.Origin.viewport(),point.x,point.y));
			}
			else
			{
				double theta=2*Math.PI*((double)i/steps);
				int x=(int)Math.floor(Math.cos(theta)*radius);
				int y=(int)Math.floor(Math.sin(theta)*radius);
				Point point=new Point(origin.x+x,origin.y+y);
				circle.addAction(finger.createPointerMove(Duration.ofMillis(20),PointerInput.Origin.viewport(),point.x,point.y));
			}
		}
		circle.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(circle));
	}
}
