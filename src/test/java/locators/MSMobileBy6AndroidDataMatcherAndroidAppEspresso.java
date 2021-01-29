package locators;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileSelector;

public class MSMobileBy6AndroidDataMatcherAndroidAppEspresso
{
	public static void main(String[] args) throws Exception
	{
		/*
		driver.findElement(MobileSelector.ANDROID_DATA_MATCHER.toString(),"hamcrest matcher code").click();
		driver.findElement(MobileBy.androidDataMatcher("hamcrest matcher code")).click();
		driver.findElementByAndroidDataMatcher("hamcrest matcher code").click();
		*/
		
		//Hancrest(JSON based JAVA) code
		/*
		{
			"name":"hasEntry",
			"args":["content-desc","End Call"]
		}
		*/
		
		//driver.findElementByAndroidDataMatcher("{\"name\":\"hasEntry\",\"args\":[\"content-desc\",\"End Call\"]}").click();
		
	}
}
