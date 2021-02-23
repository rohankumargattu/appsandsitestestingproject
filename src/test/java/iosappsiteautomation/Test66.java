package iosappsiteautomation;

import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Test66 
{
	public static void main(String[] args) throws Exception
	{
		//Run "appium" command at terminal to start appium server at default port
		URL u=new URL("http://0.0.0.0:4723/wd/hub");
		//Provide capabilities related to Simulator and App
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,"XCUITest");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME,"iOS");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,"13.5");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME,"iPhone 8");
		dc.setCapability(MobileCapabilityType.APP,"/Users/nrstt/batch249/VodQAReactNative.app");
		//Declare driver object to launch app via appium server
		IOSDriver driver;
		while(2>1)
		{
			try
			{
				driver=new IOSDriver(u,dc);
				break;
			}
			catch(Exception ex)
			{
			}
		}
		//Test automation

	}
}
