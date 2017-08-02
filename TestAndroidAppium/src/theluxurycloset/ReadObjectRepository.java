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
		File objRepo = new File("ObjectRepository.properties");
		FileInputStream fis = new FileInputStream(objRepo);
		Properties pro = new Properties();
		pro.load(fis);
		System.out.println("Loaded file");
		Thread.sleep(5000);

		WebElement btnLoginLaunch = driver.findElement(By.xpath(pro.getProperty("btnLoginLaunch.xpath")));
		btnLoginLaunch.click();
		Thread.sleep(5000);

		WebElement emailField = driver.findElement(By.xpath(pro.getProperty("emailField.xpath")));
		emailField.sendKeys(pro.getProperty("email"));
		Thread.sleep(5000);
		System.out.println("Entered email");

		WebElement pwdField = driver.findElement(By.xpath(pro.getProperty("pwdField.xpath")));
		pwdField.sendKeys(pro.getProperty("pwd"));
		Thread.sleep(5000);
		System.out.println("Entered pwd");

		WebElement login = driver.findElement(By.xpath(pro.getProperty("btnLogin.xpath")));
		login.click();
		Thread.sleep(10000);
	}

	@AfterMethod
	public void afterTest() throws MalformedURLException {
		driver.quit();
	}

}
