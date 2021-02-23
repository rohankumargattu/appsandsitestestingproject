package iosappsiteautomation;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashMap;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.Setting;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Test73 
{
	public static void main(String[] args) throws Exception
	{
		//Capture required element ".png" image using Command+shift+4
		//Save that image file in a personal folder
		//Start Appium server programmatically
		AppiumServiceBuilder sb=new AppiumServiceBuilder();
		sb.usingAnyFreePort();
        sb.usingDriverExecutable(new File("/usr/local/bin/node"));
		sb.withAppiumJS(new File("/usr/local/bin/appium"));
		HashMap<String,String> ev=new HashMap();
		ev.put("PATH","/usr/local/bin:"+System.getenv("PATH"));
		sb.withEnvironment(ev);
		AppiumDriverLocalService as=AppiumDriverLocalService.buildService(sb);
		as.start();
		//Provide capabilities related to IOS Simulator and App
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability("automationName","XCUITest");
		dc.setCapability("platformName","iOS");
		dc.setCapability("platformVersion","13.5");
		dc.setCapability("deviceName","iPhone 8");
		dc.setCapability("app","com.apple.mobileslideshow");
		//Declare driver object to launch app via appium server
		IOSDriver driver=new IOSDriver(as.getUrl(),dc);
		try
		{
			driver.setSetting(Setting.CHECK_IMAGE_ELEMENT_STALENESS,false);
			Thread.sleep(10000);
			//Click on all photos using regular locator
			driver.findElementByXPath("//*[@label='All Photos']").click();
			Thread.sleep(10000);
			//Click on a specific photo using image
			File f=new File("/Users/nrstt/batch249/mypic.png");
			Path p=f.toPath();
			byte[] b=Files.readAllBytes(p);
			String picdata=Base64.getEncoder().encodeToString(b);
			WebElement e=findImageWithOptimization(driver,picdata);
			e.click();
			Thread.sleep(10000);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//Close app
		driver.closeApp();
		//Stop Appium server
		as.stop();
	}
	
	private static WebElement findImageWithOptimization(IOSDriver driver,String imageData) throws Exception 
	{
        WebElement el=null;
        double max=1.0;
        double min=0.0;
        double check=0;
        NotFoundException notFound=null;
        while(Math.abs(max-min)>0.05) 
        {
            check=(max+min)/2;
            driver.setSetting(Setting.IMAGE_MATCH_THRESHOLD, check);
            try 
            {
                el=driver.findElementByImage(imageData);
                min = check;
            } 
            catch(NotFoundException err) 
            {
                max=check;
                notFound=err;
            }
        }
        if(el!=null) 
        {
            System.out.println("Image was found at the highest threshold of: "+check);
            return(el);
        }
        else
        {
        	System.out.println("Image could not be found even at a low threshold as: "+check);
        	throw notFound;
        }
    }
}