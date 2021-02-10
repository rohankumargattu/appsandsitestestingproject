package srinivasansekarAppAuto;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;

public class AppUpgradeVisualRegressionTestOpenCV
{
	public static void main(String[] args) throws Exception
	{
		//Visual Testing via OpenCV
		//Enter data to submit
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter some data into text box in echobox");
		String text=sc.nextLine();
		sc.close();
		//Start appium sever
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
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
		Thread.sleep(10000);
		//Create a folder for results
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt=new Date();
		File resfolder=new File("VTResOn"+sf.format(dt));
		resfolder.mkdir();
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Echo Box']")));
			//Take screen shot of Home screen in version-1
			File hs=driver.getScreenshotAs(OutputType.FILE);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='Echo Box']"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("messageInput"))).sendKeys(text);
			wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("messageSaveBtn"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId(text)));
			wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("messageInput"))).clear();
			Thread.sleep(2000);
			//Take screenshot of saved message screen in version-1
			File ms=driver.getScreenshotAs(OutputType.FILE);
			//Launch upgraded version
			driver.installApp("E:\\Automation\\TheApp-v1.10.0.apk");
			driver.activateApp("io.cloudgrey.the_app");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Echo Box']")));
			Thread.sleep(2000);
			//Test the Home screen in version-2 with version-1
			doVisualCheck(driver,"homescreen",hs,resfolder);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='Echo Box']"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId(text)));
			Thread.sleep(2000);
			//Test the saved message screen in version-2 with version-1
			doVisualCheck(driver,"msgscreen",ms,resfolder);
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
	public static void doVisualCheck(AndroidDriver driver,String checkName,File baseImg, File resfolder) throws Exception 
	{
		SimilarityMatchingOptions opts=new SimilarityMatchingOptions();
		opts.withEnabledVisualization();
		File newImg=driver.getScreenshotAs(OutputType.FILE);
		SimilarityMatchingResult res=driver.getImagesSimilarity(baseImg,newImg,opts);
		//If the similarity is not high enough, consider the check to have failed
		if(res.getScore()<1.0) //1.0 means 100%
		{
			File difffile=new File(resfolder.getAbsolutePath()+"/FAIL_"+checkName+".png");
			res.storeVisualization(difffile);
			System.out.println("Visual check of "+checkName+
					" failed; similarity match was only "+res.getScore()+
					", and below the threshold of 1.0. Visualization written to "+
					difffile.getAbsolutePath());
		}
		else
		{
			System.out.println(
					String.format("Visual check of '%s' passed; similarity match was %f",
							checkName, res.getScore()));
		}
	}
}