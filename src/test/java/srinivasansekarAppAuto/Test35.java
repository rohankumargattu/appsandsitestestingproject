package srinivasansekarAppAuto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Test35
{
	public static void main(String[] args) throws Exception
	{
		//Start appium sever programmatically
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		//Get appium server address
		URL u=new URL("http://0.0.0.0:4723/wd/hub");
		//Define desired capabilities related to device and app
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
	    dc.setCapability("deviceName","");
	    dc.setCapability("platformName","android");  
	    dc.setCapability("platformVersion","8.0.0");
	    dc.setCapability("appPackage","");
	    dc.setCapability("appActivity","");
		//Launch app in device through appium server by creating driver object
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
		//Test automation
		try
		{
			Thread.sleep(5000);
			//Get details related to app
			System.out.println("About App info in to text file:");
			Map<String,String> m1=driver.getAppStringMap();
			File f1=new File("appinfo.txt");
			FileWriter fw1=new FileWriter(f1);
			BufferedWriter bw1=new BufferedWriter(fw1);
			for(Map.Entry e:m1.entrySet())
			{  
				bw1.write(e.getKey()+"="+e.getValue());  
				bw1.newLine();
			} 
			bw1.close();
			fw1.close();
			//Get app session details in device
			System.out.println("About session info in to text file:");
			Map<String,Object> m2=driver.getSessionDetails();
			File f2=new File("sessioninfo.txt");
			FileWriter fw2=new FileWriter(f2);
			BufferedWriter bw2=new BufferedWriter(fw2);
			for(Map.Entry e:m2.entrySet())
			{  
				bw2.write(e.getKey()+"="+e.getValue().toString());  
				bw2.newLine();
			}
			bw2.close();
			fw2.close();
			//Get settings stored for this test session
			System.out.println("About Settings info in to text file:");
			Map<String,Object> m3=driver.getSettings();
			File f3=new File("settingsinfo.txt");
			FileWriter fw3=new FileWriter(f3);
			BufferedWriter bw3=new BufferedWriter(fw3);
			for(Map.Entry e:m3.entrySet())
			{  
				bw3.write(e.getKey()+"="+e.getValue().toString());  
				bw3.newLine();
			}
			bw3.close();
			fw3.close();
			//Get performance types
			System.out.println("Abot Performance types:");
			List<String> l1=driver.getSupportedPerformanceDataTypes();
			for(String v:l1)
			{
				System.out.println(v);  
			}
			//Get specific performance type details
			for(int i=0;i<l1.size();i++)
			{
				System.out.println("About "+l1.get(i)+" into text file");
				File f=new File(l1.get(i)+".txt");
				FileWriter fw=new FileWriter(f);
				BufferedWriter bw=new BufferedWriter(fw);
				List<List<Object>> l2=driver.getPerformanceData("com.jio.media.ondemand",l1.get(i),1000);
				//Store data matrix(table) in file
				for(int j=0;j<l2.size();j++) //each row
				{
					for(int k=0;k<l2.get(j).size();k++) //each column in that row
					{
						try 
						{
							bw.write(l2.get(j).get(k).toString()+"\t");
						}
						catch(Exception ex)
						{
							bw.write("N/A\t");
						}
					}
					bw.newLine(); //new line
				} 
				bw.close();
				fw.close();
			}
			Thread.sleep(10000);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//Close App
		driver.closeApp();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe"); 
	}
}