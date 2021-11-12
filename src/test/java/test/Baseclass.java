package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Baseclass {

	DesiredCapabilities cap =new DesiredCapabilities();
	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest test;

	@BeforeTest
	public void ReportSetup() {
		report = new ExtentReports("ExtentReports.html"); 
	}

	@BeforeMethod
	public void setup() throws IOException {

		// TODO Auto-generated method stub
		setDriver();
		driver.get("https://www.simplilearn.com/");
		//System.setProperty("webdriver.gecko.driver", "geckodriver");
		//WebDriver driver1 = new GeckoDriverInfo();
		//driver1.get("https://www.simplilearn.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);			
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@AfterTest
	public void ReportTearDown() {
		report.flush();
		report.close();
	}

	public void setDriver() throws IOException {

		InputStream input = new FileInputStream("config.properties");

		Properties prop = new Properties();

		prop.load(input);

		String BrowserName = prop.getProperty("browser");

		if(BrowserName.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver", "chromedriver");
			driver = new ChromeDriver();

		}else{

			cap.setPlatform(Platform.LINUX);
			cap.setBrowserName("chrome");
			URL url = new URL("http://172.17.0.1:4444/wd/hub");

			//WebDriver driver1 = new ChromeDriver();
			driver = new RemoteWebDriver(url,cap);
		}
	}
}
