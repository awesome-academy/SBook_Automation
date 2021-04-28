package com.sun.edu.script;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.sun.edu.util.Settings;
import com.sun.edu.util.XLSHelper;

public abstract class CommonTestCase {
	private static Log log = LogFactory.getLog(CommonTestCase.class);
	public static WebDriver driver = null;

	@BeforeTest
	public void beforeTest() {
		System.setProperty(Settings.getSetting(Settings.WEBDRIVER), Settings.getSetting(Settings.WEBDRIVER_PATH));
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("disable-infobars");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("before");
	}

	@AfterTest
	public void end() {
		System.out.println("after");
		driver.quit();
	}

	@DataProvider
	public Object[][] SetLogin() {
//		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 2);
		Object[][] data = XLSHelper.retrieveCells("user.xls", 0, 2, 2, 2);
		return data;
	}

	public void login(String email, String password) {
		driver.get(Settings.getSetting(Settings.URL_LOGIN));
		if (!driver.findElements(By.cssSelector(".login_wsm")).isEmpty()) {
			driver.navigate().to(Settings.getSetting(Settings.WSM_LOGIN));
			loginWsm(email, password);
			driver.navigate().to(Settings.getSetting(Settings.URL_LOGIN));
		}

		if (!driver.findElements(By.cssSelector(".login_wsm")).isEmpty()) {
			WebElement btnLogin = driver.findElement(By.cssSelector(".login_wsm"));
			btnLogin.click();
		}
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
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),\"" + email + "\")]")));
		log.info("Login WSM is successfully!");
	}
}