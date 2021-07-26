package com.sun.edu.script.sbook;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.edu.script.CommonTestCase;
import com.sun.edu.util.XLSHelper;

public class DetailBook extends CommonTestCase {
	@Test(dataProvider = "setLogin", priority = 1)
	public void login(String email, String pass) {
		super.login(email, pass);
	}

	@DataProvider
	public Object[][] dataTC40() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 4, 2, 2, 1);
		return data;
	}

	@DataProvider
	public Object[][] dataTC41() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 4, 3, 3, 3);
		return data;
	}

	@DataProvider
	public Object[][] dataTC42() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 4, 4, 4, 1);
		return data;
	}

	@DataProvider
	public Object[][] dataTC44() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 4, 5, 5, 2);
		return data;
	}

	@Test(dataProvider = "dataTC40", priority = 2)
	public void testTC40(String book) {
		closeDebug();
		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries");
		WebElement form = driver.findElement(By.cssSelector("#header-search"));
		WebElement inputSearch = form.findElement(By.cssSelector("input[type='text']"));
		inputSearch.clear();
		inputSearch.sendKeys(book);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement items = driver.findElement(By
				.cssSelector("a[href='https://stg-api-book.sun-asterisk.vn/birthday-libraries/sach-cua-thuy-2299\']"));
		js.executeScript("arguments[0].scrollIntoView();", items);
		items.click();
	}

	// detail book match with infor book
	@Test(dataProvider = "dataTC41", priority = 3)
	public void testTC41(String book, String titleBook, String authorBook) {
		boolean result = false;
		WebElement form = driver.findElement(By.cssSelector(".product-info-main"));
		WebElement title = form.findElement(By.cssSelector(".page-title"));
		WebElement author = form.findElement(By.cssSelector("#span-author"));
		if ((title.getText() == titleBook) && author.getText() == authorBook) {
			result = true;
		}
		try {
			assertTrue(result);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}
	}

	// like book successfully
	@Test(dataProvider = "dataTC42", priority = 4)
	public void testTC42(String reaction) {
		WebElement form = driver.findElement(By.cssSelector(".product-info-main"));
		WebElement btnreact = form.findElement(By.cssSelector(".vote-book"));
		btnreact.click();

		WebElement showPeople = form.findElement(By.cssSelector("#show-people-favorite"));
		showPeople.click();

		WebElement showFavorite = driver.findElement(By.cssSelector("#people-favorite"));
		List<WebElement> element = showFavorite.findElements(By.cssSelector(".show-people-list"));
		try {
			assertEquals(element.size(), reaction);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}
		WebElement btnClose = showFavorite.findElement(By.cssSelector(".close"));
		btnClose.click();
	}

	// Check total view
	@Test(priority = 5)
	public void testTC43() {
		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries/sach-cua-thuy-2299");
		WebElement view = driver.findElement(By.cssSelector(".view-sku > .reviews-actions-book > span"));
		int totalView = Integer.parseInt(view.getText());
		// System.out.println("view" + totalView);
		driver.navigate().refresh();
		WebElement newView = driver.findElement(By.cssSelector(".reviews-actions-book > span"));
		int newTotalView = Integer.parseInt(newView.getText());
		try {
			assertEquals(totalView++, newTotalView);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}
	}

	// select present successfully
	@Test(dataProvider = "dataTC44", priority = 6)
	public void testTC44(String book, String expected) {
		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries/sach-cua-thuy-2299");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement btnSelectPresent = driver.findElement(By.cssSelector("#select_present"));
		btnSelectPresent.click();

		WebElement myPresent = driver.findElement(
				By.cssSelector("a[href='https://stg-api-book.sun-asterisk.vn/birthday-libraries/my-present']"));
		myPresent.click();

		WebElement title = driver.findElement(By.cssSelector(".product-name"));
		try {
			assertEquals(title.getText(), expected);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}

	}

	// select present unsuccessfully
	@Test(priority = 7)
	public void testTC45() {
		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries/sach-cua-thuy-2299");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement btnAdd = driver.findElement(By.cssSelector("#btn-add"));
		btnAdd.click();

		WebElement btnSelectPresent = driver.findElement(By.cssSelector("#select_present"));
		btnSelectPresent.click();

		wait(".swal-button--confirm", 30);
		WebElement modal = driver.findElement(By.cssSelector(".swal-button--confirm"));
		String modalText = modal.getText();

		modal.click();
		try {
			assertEquals(modalText, "OK");
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CreateBookUnsuccess.jpg");
		}

	}

	public void closeDebug() {
		WebElement btnExits = driver.findElement(By.cssSelector(".phpdebugbar-close-btn"));
		btnExits.click();
	}

	public void wait(String element, Integer time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(element)));
	}

	public void capture(String path) {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("C:\\Users\\nguyen.thi.thuyb\\Pictures\\Capture\\" + path));
		} catch (IOException ioe) {
			// TODO: handle exception
			ioe.printStackTrace();
		}
	}
}
