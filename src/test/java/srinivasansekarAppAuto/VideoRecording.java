package srinivasansekarAppAuto;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class VideoRecording 
{
	public static void main(String[] args) throws Exception
	{
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
	    dc.setCapability("adbExecTimeout","50000"); //in msec //extra
		dc.setCapability("appPackage","com.vodqareactnative");
	    dc.setCapability("appActivity","com.vodqareactnative.MainActivity");
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
			//Stat video recording
			AndroidStartScreenRecordingOptions asr=new AndroidStartScreenRecordingOptions();
	        asr.withVideoSize("1280x720");
	        asr.withTimeLimit(Duration.ofSeconds(200));
			driver.startRecordingScreen(asr);
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='LOG IN']")));
			driver.findElement(By.xpath("//*[@text='LOG IN']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Drag & Drop']"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Drag me!']")));
			MobileElement e1=(MobileElement) driver.findElement(By.xpath("//*[@text='Drag me!']"));
			MobileElement e2=(MobileElement) driver.findElement(By.xpath("//*[@text='Drop here.']"));
			TouchAction ta=new TouchAction(driver);
			WaitOptions wo=new WaitOptions();
			wo.withDuration(Duration.ofMillis(5000));
			ta.press(ElementOption.element(e1)).waitAction(wo).moveTo(ElementOption.element(e2)).release().perform();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Circle dropped']")));
			//Stop recording and save video file
	        String videobase64string=driver.stopRecordingScreen();
	        byte[] decode=Base64.getDecoder().decode(videobase64string);
	        File f=new File("androidclip.mp4");
	        FileUtils.writeByteArrayToFile(f,decode);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
        //Close App
	  	driver.quit();
	  	//Stop appium server
	  	Runtime.getRuntime().exec("taskkill /F /IM node.exe");
	  	Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
