package theluxurycloset;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ReadObjectRepository {

	WebDriver driver;
	File objRepo, dataRepo;
	FileInputStream fisObject, fisData;
	Properties proObject, proData;

	@BeforeMethod
	public void beforeTest() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("BROWSER_NAME", "Android");
		capabilities.setCapability("VERSION", "5.5.1");
		capabilities.setCapability("deviceName", "sony-c6602-BX903GED23");
		capabilities.setCapability("platformName", "Android");

		capabilities.setCapability("appPackage", "com.theluxurycloset.tclapplication.dev");
		capabilities.setCapability("appActivity",
				"com.theluxurycloset.tclapplication.activity.SplashScreen.SplashActivity");
		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out.println("Before Test");
	}

	@Test
	public void readObjectToLogin() throws IOException, InterruptedException {
		objRepo = new File("ObjectRepository.properties");
		dataRepo = new File("DataRepository.properties");
		
		fisObject = new FileInputStream(objRepo);
		fisData = new FileInputStream(dataRepo);
		
		proObject = new Properties();
		proData = new Properties();
		
		proObject.load(fisObject);
		proData.load(fisData);
		
		System.out.println("Loaded file");
		Thread.sleep(5000);

		WebElement btnLoginLaunch = driver.findElement(By.xpath(proObject.getProperty("btnLoginLaunch.xpath")));
		btnLoginLaunch.click();
		Thread.sleep(10000);

		WebElement emailField = driver.findElement(By.xpath(proObject.getProperty("emailField.xpath")));
		emailField.sendKeys(proData.getProperty("emailLogin"));
		Thread.sleep(5000);
		System.out.println("Entered email");

		WebElement pwdField = driver.findElement(By.xpath(proObject.getProperty("pwdField.xpath")));
		pwdField.sendKeys(proData.getProperty("pwdLogin"));
		Thread.sleep(5000);
		System.out.println("Entered pwd");

		WebElement login = driver.findElement(By.xpath(proObject.getProperty("btnLogin.xpath")));
		login.click();
		Thread.sleep(20000);
		System.out.println("Click Login");
		
		WebElement lcShop = driver.findElement(By.xpath("//android.widget.ImageView[@bounds = '[63,107][156,210]']"));
		lcShop.isDisplayed();
		System.out.println(""+lcShop.isDisplayed());
		Thread.sleep(10000);
	}

	@AfterMethod
	public void afterTest() throws MalformedURLException {
		driver.quit();
	}

}
