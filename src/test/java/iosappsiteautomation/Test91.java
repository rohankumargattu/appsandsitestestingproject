package iosappsiteautomation;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Test91
{
	public static void main(String[] args)
	{
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
		//Common objects
		String BUNDLE_ID="io.cloudgrey.the-app";
		String APP_V1_0_0="https://github.com/cloudgrey-io/the-app/releases/download/v1.0.0/TheApp-v1.0.0.app.zip";
	    String APP_V1_0_1="https://github.com/cloudgrey-io/the-app/releases/download/v1.0.1/TheApp-v1.0.1.app.zip";
	    String APP_V1_0_2="https://github.com/cloudgrey-io/the-app/releases/download/v1.0.2/TheApp-v1.0.2.app.zip";
	    By msgInput=By.xpath("//XCUIElementTypeTextField[@name=\"messageInput\"]");
	    By savedMsg=MobileBy.AccessibilityId("savedMessage");
	    By saveMsgBtn=MobileBy.AccessibilityId("messageSaveBtn");
	    By echoBox=MobileBy.AccessibilityId("Echo Box");
	    String TEST_MESSAGE="Hello KALAM";
	    //Provide capabilities related to IOS Simulator and App
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability("automationName","XCUITest");
		dc.setCapability("platformName","iOS");
		dc.setCapability("platformVersion","13.5");
		dc.setCapability("deviceName","iPhone 8");
		dc.setCapability("app",APP_V1_0_2);
		//change this to APP_V1_0_1
        String appUpgradeVersion = APP_V1_0_1;
        // Open the app.
        IOSDriver driver=new IOSDriver(as.getUrl(),dc);
        driver.rotate(ScreenOrientation.LANDSCAPE);
        WebDriverWait wait = new WebDriverWait(driver,10);
        try 
        {
            wait.until(ExpectedConditions.presenceOfElementLocated(echoBox)).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(msgInput)).sendKeys(TEST_MESSAGE);
            driver.hideKeyboard();
            wait.until(ExpectedConditions.presenceOfElementLocated(saveMsgBtn)).click();
            try 
            {
            	//wrong locator
				wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Not Now")));
				driver.findElement(MobileBy.AccessibilityId("Not Now")).click();
				wait.until(ExpectedConditions.presenceOfElementLocated(saveMsgBtn)).click();
			} 
            catch(Exception e) 
            {
			}
            Thread.sleep(5000);
            //Remove version 1 and install upgraded version
            HashMap<String, String> bundleArgs = new HashMap<>();
            bundleArgs.put("bundleId",BUNDLE_ID);
            driver.executeScript("mobile:terminateApp", bundleArgs);
            HashMap<String, String> installArgs = new HashMap<>();
            installArgs.put("app",appUpgradeVersion);
            driver.executeScript("mobile:installApp",installArgs);
            Thread.sleep(10000);
            driver.executeScript("mobile:launchApp",bundleArgs);
            wait.until(ExpectedConditions.presenceOfElementLocated(echoBox)).click();
            String savedText=wait.until(ExpectedConditions.presenceOfElementLocated(savedMsg)).getText();
            if(savedText.equals(TEST_MESSAGE))
            {
            	System.out.println("Message correctly saved for version 1.0.1");
            }
            else
            {
            	System.out.println("Message not correctly saved for version 1.0.1");
            }
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
}
