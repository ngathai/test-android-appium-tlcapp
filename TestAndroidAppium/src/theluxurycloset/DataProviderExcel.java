package theluxurycloset;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DataProviderExcel {
	WebDriver driver;
	Workbook wb;
	Sheet dataSheet;
	int numrow;

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

	@DataProvider(name = "testData")
	public Object[][] getTestData() throws BiffException, IOException {
		wb = Workbook.getWorkbook(new File("C:\\Users\\Nadie\\Git\\test-android-appium-tlcapp\\TestAndroidAppium\\testData.xls"));
		dataSheet = wb.getSheet("data");
		numrow = dataSheet.getRows();

		Object[][] getTestData = new Object[numrow][dataSheet.getColumns()];

		for (int i = 1; i < numrow; i++) {
			getTestData[i][0] = dataSheet.getCell(0, i).getContents();
			getTestData[i][1] = dataSheet.getCell(1, i).getContents();
			System.out.println(""+getTestData[i][0]+" and "+getTestData[i][1]+"");

		}
		return getTestData;
		
	}

	@Test(dataProvider = "testData")
	public void loginValid(String email, String pwd) throws InterruptedException {
		Thread.sleep(10000);
		WebElement btnLogin = driver.findElement(By.xpath("//android.widget.TextView[@text='Log In']"));
		btnLogin.click();
		Thread.sleep(5000);
		
		WebElement emailField = driver.findElement(By
				.xpath("//android.widget.EditText[@text='Enter your email address']"));
		emailField.sendKeys(email);
		Thread.sleep(5000);
		
		WebElement pwdField = driver.findElement(By
				.xpath("//android.widget.EditText[@NAF='true']"));
		pwdField.sendKeys(pwd);
		Thread.sleep(5000);
		
		WebElement login = driver.findElement(By.xpath("//android.widget.TextView[@text = 'Log In']"));
		login.click();
		Thread.sleep(10000);
	}
	@AfterMethod
	public void afterTest() {
		driver.quit();
	}
}
