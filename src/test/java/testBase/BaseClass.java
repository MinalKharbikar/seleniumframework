package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class BaseClass {
 
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass (groups = {"Sanity","Regression"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
		
		//Loading config.properties file
        FileReader file = new FileReader("./src//test//resources//config.properties");
        p = new Properties();
        p.load(file);
        
        
		logger = LogManager.getLogger(this.getClass());
		
		if ("remote".equalsIgnoreCase(p.getProperty("execution_env")))
		{
		    URL gridUrl = new URL("http://localhost:4444");

		    switch (br.toLowerCase())
		    {
		        case "chrome":
		            ChromeOptions chromeOptions = new ChromeOptions();
		            chromeOptions.setPlatformName(os.equalsIgnoreCase("mac") ? "macOS" : "Windows 11");
		            driver = new RemoteWebDriver(gridUrl, chromeOptions);
		            break;

		        case "edge":
		            EdgeOptions edgeOptions = new EdgeOptions();
		            edgeOptions.setPlatformName(os.equalsIgnoreCase("mac") ? "macOS" : "Windows 11");
		            driver = new RemoteWebDriver(gridUrl, edgeOptions);
		            break;

		        case "firefox":
		            FirefoxOptions firefoxOptions = new FirefoxOptions();
		            firefoxOptions.setPlatformName(os.equalsIgnoreCase("mac") ? "macOS" : "Windows 11");
		            driver = new RemoteWebDriver(gridUrl, firefoxOptions);
		            break;

		        default:
		            System.out.println("No matching browser");
		            return;
		    }

		    driver.manage().deleteAllCookies();
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    driver.get(p.getProperty("appURL"));
		    driver.manage().window().maximize();
		}
		
		if (p.getProperty("execution_env").equals("local"))
		{
		
		switch (br.toLowerCase())
	   {
		case "chrome": driver = new ChromeDriver(); break;
		case "edge": driver = new EdgeDriver(); break;
		case "firefox": driver = new FirefoxDriver(); break;
		default : System.out.println("Invalid browser name.. ");
		return;
		}
				
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	//	driver.get("https://opencart.dreamvention.com/");
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
		}
	
	@AfterClass (groups = {"Sanity","Regression"})
	public void tearDown()
	{
		driver.quit();
	}
	
	
	public String randomString() {
	    String generatedstring =  RandomStringUtils.randomAlphabetic(5);
	    return generatedstring;
	}
	
	public String randomNumber() {
	    String generatednumber =  RandomStringUtils.randomNumeric(10);
	    return generatednumber;
	}
	
	public String randomAlphaNumeric() {
		String generatedstring =  RandomStringUtils.randomAlphabetic(3);
	    String generatednumber=  RandomStringUtils.randomNumeric(3);
	   // return (generatednumber+generatedstring);
	    return (generatednumber+"@"+generatedstring); // to add special character
	}
	
	public String captureScreen(String tname) throws IOException {

	    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

	    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

	    String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
	    File targetFile = new File(targetFilePath);

	    sourceFile.renameTo(targetFile);

	    return targetFilePath;
	}

}
