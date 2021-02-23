package iosappsiteautomation;

import java.io.File;
import java.util.HashMap;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Test67 
{
	public static void main(String[] args) throws Exception
	{
		//Start Appium server programmatically
		AppiumServiceBuilder sb=new AppiumServiceBuilder();
        //Use any port, in case the default 4723 is already taken (maybe by another Appium server)
        sb.usingAnyFreePort();
        //Tell serviceBuilder where node is installed.
        sb.usingDriverExecutable(new File("/usr/local/bin/node")); //run "which node" at terminal
        //Tell serviceBuilder where Appium is installed.
        sb.withAppiumJS(new File("/usr/local/bin/appium")); //run "which appium" at terminal
        //The XCUITest driver requires that a path to the Carthage binary is in the PATH variable. 
        //"AppiumDriverLocalService" runs Appium, it does not re-use our default PATH.
        //So, we need to specify new PATH environment variable in the "AppiumServiceBuilder"
        HashMap<String,String> ev=new HashMap<String,String>();
        ev.put("PATH","/usr/local/bin:"+System.getenv("PATH"));
        sb.withEnvironment(ev);
        //Create object to "AppiumDriverLocalService" class by using "AppiumServiceBuilder"
        AppiumDriverLocalService as=AppiumDriverLocalService.buildService(sb);
        //Start server and display URL
        as.start();
        System.out.println(as.getUrl().toString());
        Thread.sleep(10000);
        //Stop Appium server
        as.stop();
	}
}
