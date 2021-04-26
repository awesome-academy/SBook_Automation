package com.sun.edu.script;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.sun.edu.util.Settings;
import com.sun.edu.util.XLSHelper;

public abstract class CommonTestCase {
	public static WebDriver driver = null;

	@BeforeTest
	public void beforeTest() {
		System.setProperty(Settings.getSetting(Settings.WEBDRIVER), Settings.getSetting(Settings.WEBDRIVER_PATH));
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterTest
	public void end() {
//		driver.quit();
	}

	@DataProvider
	public Object[][] SetLogin() {
//		Object[][] data = { { "chu.anh.tuan@framgia.com.edev.test", "123456" } };
//		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 2);
		Object[][] data = XLSHelper.retrieveCells("user.xls", 0, 2, 2, 2);
		System.out.println(data[0][0]);
		return data;
	}

	public void login(String email, String password) {
		driver.get(Settings.getSetting(Settings.WSM_LOGIN));
		loginWsm(email, password);

		driver.navigate().to(Settings.getSetting(Settings.URL_LOGIN));

		driver.manage().window().maximize();
		WebElement btnLogin = driver.findElement(By.cssSelector(".login_wsm"));
		btnLogin.click();
	}

	private void loginWsm(String email, String password) {
		WebElement btnWsmLogin = driver.findElement(By.className("btn-login"));
		btnWsmLogin.click();
		WebElement wsmEmail = driver.findElement(By.cssSelector("#devise-login-form input[name='user[email]']"));
		wsmEmail.clear();
		wsmEmail.sendKeys(email);
		WebElement wsmPassword = driver.findElement(By.cssSelector("#devise-login-form input[name='user[password]']"));
		wsmPassword.clear();
		wsmPassword.sendKeys(password);
		WebElement wsmSubmit = driver.findElement(By.cssSelector("#devise-login-form button[type='submit']"));
		wsmSubmit.click();
		try {
			Thread.sleep(3000);
//			WebDriverWait wait = new WebDriverWait(driver, 15);
//			wait.until(
//					ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("p:contains('" + email + "')"))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}