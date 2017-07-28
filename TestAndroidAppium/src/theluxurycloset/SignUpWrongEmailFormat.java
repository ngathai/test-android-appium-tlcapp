package theluxurycloset;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SignUpWrongEmailFormat {
	WebDriver driver;

	@BeforeClass
	public void setUp() throws MalformedURLException {
		// Set up desired capabilities and pass the Android app-activity and
		// app-package to Appium
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("BROWSER_NAME", "Android");
		capabilities.setCapability("VERSION", "5.5.1");
		capabilities.setCapability("deviceName", "sony-c6602-BX903GED23");
		capabilities.setCapability("platformName", "Android");

		capabilities.setCapability("appPackage", "com.theluxurycloset.tclapplication.dev");
		capabilities.setCapability("appActivity",
				"com.theluxurycloset.tclapplication.activity.SplashScreen.SplashActivity");
		capabilities.setCapability("unicodeKeyboard", "true");
		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}
	
	@Test
	public void signUpWrongEmailFormat() throws Exception {
		// locate the Text on the calculator by using By.name()
		Thread.sleep(10000);
		WebElement btnSignup = driver.findElement(By.xpath("//android.widget.TextView[@text='Sign Up']"));
		btnSignup.click();
		System.out.println("Tutorial > click Login");
		Thread.sleep(5000);

		WebElement email = driver.findElement(By.xpath("//android.widget.EditText[@text='Enter your email address']"));
		email.sendKeys("thaithianhnga");
		System.out.println("Enter email");
		Thread.sleep(5000);

		WebElement pwd = driver.findElement(By.xpath("//android.widget.EditText[@NAF='true']"));
		pwd.sendKeys("123456");
		System.out.println("Enter password");
		Thread.sleep(5000);
		
		WebElement signUp = driver.findElement(By.xpath("//android.widget.TextView[@text='Sign Up']"));
		signUp.click();
		System.out.println("click Sign Up");
		Thread.sleep(5000);

		WebElement error = driver.findElement(By.xpath("//android.widget.TextView[@bounds = '[30,318][526,364]']"));
		String invEmail = error.getText();
		System.out.println("Get text "+invEmail+"");
		
		Assert.assertEquals(invEmail, "Please Enter a Valid Email");
	
		Thread.sleep(10000);
	}

	 @AfterClass
	 public void teardown() {
	 driver.quit();
	 }
}
