package com.sun.edu.script.sbook;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.edu.script.CommonTestCase;
import com.sun.edu.util.XLSHelper;

public class SuggestBook extends CommonTestCase {
	@Test(dataProvider = "setLogin", priority = 1)
	public void login(String email, String pass) {
		super.login(email, pass);
	}

	@DataProvider
	public Object[][] dataTC2() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 1, 2, 2, 2);
		return data;
	}

	@DataProvider
	public Object[][] dataTC3() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 1, 3, 3, 2);
		return data;
	}

	@DataProvider
	public Object[][] dataTC4() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 1, 4, 4, 2);
		return data;
	}

	@DataProvider
	public Object[][] dataTC5() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 1, 5, 5, 3);
		return data;
	}

	@DataProvider
	public Object[][] dataTC6() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 1, 6, 6, 3);
		return data;
	}

	@DataProvider
	public Object[][] dataTC7() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 1, 7, 7, 3);
		return data;
	}

	@DataProvider
	public Object[][] dataTC8() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 1, 8, 8, 3);
		return data;
	}

	@DataProvider
	public Object[][] dataTC9() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 1, 9, 9, 3);
		return data;
	}

	@DataProvider
	public Object[][] dataTC10() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 1, 10, 10, 3);
		return data;
	}
	
	@DataProvider
	public Object[][] dataTC11() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 1, 11, 11, 3);
		return data;
	}
	
	@DataProvider
	public Object[][] dataTC12() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 1, 12, 12, 3);
		return data;
	}
	
	@DataProvider
	public Object[][] dataTC13() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 1, 13, 13, 3);
		return data;
	}

	// Suggest sach thanh cong
	@Test(dataProvider = "dataTC2", priority = 2)
	public void testTC2(String name, String author) {

		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries");
		WebElement btnSuggest = driver.findElement(By.id("suggest-book"));
		btnSuggest.click();

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".form-suggest"))));

		WebElement form = driver.findElement(By.cssSelector(".form-suggest"));

		WebElement nameBook = form.findElement(By.cssSelector("input[name='title']"));
		nameBook.clear();
		nameBook.sendKeys(name);
		WebElement nameAuthor = form.findElement(By.cssSelector("input[name='author']"));
		nameAuthor.clear();
		nameAuthor.sendKeys(author);
		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		WebDriverWait anotherWait = new WebDriverWait(driver, 30);
		anotherWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("img"))));
		WebElement formCheck = driver.findElement(By.cssSelector("img"));

		try {
			assertNotNull(formCheck);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("suggestBook.jpg");
		}
	}

	// Suggest sach thanh cong input Name Book = maxlenght
	@Test(dataProvider = "dataTC3", priority = 3)
	public void testTC3(String name, String author) {

		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries");
		WebElement btnSuggest = driver.findElement(By.id("suggest-book"));
		btnSuggest.click();

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".form-suggest"))));

		WebElement form = driver.findElement(By.cssSelector(".form-suggest"));

		WebElement nameBook = form.findElement(By.cssSelector("input[name='title']"));
		nameBook.clear();
		nameBook.sendKeys(name);
		WebElement nameAuthor = form.findElement(By.cssSelector("input[name='author']"));
		nameAuthor.clear();
		nameAuthor.sendKeys(author);
		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		WebDriverWait anotherWait = new WebDriverWait(driver, 30);
		anotherWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("img"))));
		WebElement formCheck = driver.findElement(By.cssSelector("img"));

		try {
			assertNotNull(formCheck);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("suggestBook.jpg");
		}
	}

	// Suggest sach thanh cong input Author= maxlenght
	@Test(dataProvider = "dataTC4", priority = 4)
	public void testTC4(String name, String author) {

		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries");
		WebElement btnSuggest = driver.findElement(By.id("suggest-book"));
		btnSuggest.click();

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".form-suggest"))));

		WebElement form = driver.findElement(By.cssSelector(".form-suggest"));

		WebElement nameBook = form.findElement(By.cssSelector("input[name='title']"));
		nameBook.clear();
		nameBook.sendKeys(name);
		WebElement nameAuthor = form.findElement(By.cssSelector("input[name='author']"));
		nameAuthor.clear();
		nameAuthor.sendKeys(author);
		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		WebDriverWait anotherWait = new WebDriverWait(driver, 30);
		anotherWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("img"))));
		WebElement formCheck = driver.findElement(By.cssSelector("img"));

		try {
			assertNotNull(formCheck);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("suggestBook.jpg");
		}
	}

	// NameBook = blank
	@Test(dataProvider = "dataTC5", priority = 5)
	public void testTC5(String name, String author, String error) {

		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries");
		WebElement btnSuggest = driver.findElement(By.id("suggest-book"));
		btnSuggest.click();

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".form-suggest"))));

		WebElement form = driver.findElement(By.cssSelector(".form-suggest"));

		WebElement nameBook = form.findElement(By.cssSelector("input[name='title']"));
		nameBook.clear();
		nameBook.sendKeys(name);
		WebElement nameAuthor = form.findElement(By.cssSelector("input[name='author']"));
		nameAuthor.clear();
		nameAuthor.sendKeys(author);
		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		try {
			assertEquals(form.findElement(By.id("title-error")).getText(), error);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("nameBookBlank.jpg");
		}
	}

	// NameBook Invalid, < min lenght
	@Test(dataProvider = "dataTC6", priority = 6)
	public void testTC6(String name, String author, String error) {

		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries");
		WebElement btnSuggest = driver.findElement(By.id("suggest-book"));
		btnSuggest.click();

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".form-suggest"))));

		WebElement form = driver.findElement(By.cssSelector(".form-suggest"));

		WebElement nameBook = form.findElement(By.cssSelector("input[name='title']"));
		nameBook.clear();
		nameBook.sendKeys(name);
		WebElement nameAuthor = form.findElement(By.cssSelector("input[name='author']"));
		nameAuthor.clear();
		nameAuthor.sendKeys(author);
		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		try {
			assertEquals(form.findElement(By.id("title-error")).getText(), error);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("nameBookInvalid.jpg");
		}
	}

	// authorBlank = blank
	@Test(dataProvider = "dataTC7", priority = 7)
	public void testTC7(String name, String author, String error) {

		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries");
		WebElement btnSuggest = driver.findElement(By.id("suggest-book"));
		btnSuggest.click();

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".form-suggest"))));

		WebElement form = driver.findElement(By.cssSelector(".form-suggest"));

		WebElement nameBook = form.findElement(By.cssSelector("input[name='title']"));
		nameBook.clear();
		nameBook.sendKeys(name);
		WebElement nameAuthor = form.findElement(By.cssSelector("input[name='author']"));
		nameAuthor.clear();
		nameAuthor.sendKeys(author);
		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		try {
			assertEquals(form.findElement(By.id("author-error")).getText(), error);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("authorBlank.jpg");
		}
	}

	// authorInvalid, < min lenght
	@Test(dataProvider = "dataTC8", priority = 8)
	public void testTC8(String name, String author, String error) {

		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries");
		WebElement btnSuggest = driver.findElement(By.id("suggest-book"));
		btnSuggest.click();

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".form-suggest"))));

		WebElement form = driver.findElement(By.cssSelector(".form-suggest"));

		WebElement nameBook = form.findElement(By.cssSelector("input[name='title']"));
		nameBook.clear();
		nameBook.sendKeys(name);
		WebElement nameAuthor = form.findElement(By.cssSelector("input[name='author']"));
		nameAuthor.clear();
		nameAuthor.sendKeys(author);
		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		try {
			assertEquals(form.findElement(By.id("author-error")).getText(), error);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("authorInvalid.jpg");
		}
	}

// Name > 191 chars
	@Test(dataProvider = "dataTC9", priority = 9)
	public void testTC9(String name, String author, String error) {
		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries");
		WebElement btnSuggest = driver.findElement(By.id("suggest-book"));
		btnSuggest.click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".form-suggest"))));

		WebElement form = driver.findElement(By.cssSelector(".form-suggest"));

		WebElement nameBook = form.findElement(By.cssSelector("input[name='title']"));
		nameBook.clear();
		nameBook.sendKeys(name);
		WebElement nameAuthor = form.findElement(By.cssSelector("input[name='author']"));
		nameAuthor.clear();
		nameAuthor.sendKeys(author);
		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		try {
			assertEquals(form.findElement(By.id("title-error")).getText(), error);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("NameTooLong.jpg");
		}
	}

	// author > 100 chars
	@Test(dataProvider = "dataTC10", priority = 10)
	public void testTC10(String name, String author, String error) {

		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries");
		WebElement btnSuggest = driver.findElement(By.id("suggest-book"));
		btnSuggest.click();

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".form-suggest"))));

		WebElement form = driver.findElement(By.cssSelector(".form-suggest"));

		WebElement nameBook = form.findElement(By.cssSelector("input[name='title']"));
		nameBook.clear();
		nameBook.sendKeys(name);
		WebElement nameAuthor = form.findElement(By.cssSelector("input[name='author']"));
		nameAuthor.clear();
		nameAuthor.sendKeys(author);
		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();
		try {
			assertEquals(form.findElement(By.id("author-error")).getText(), error);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("AuthorTooLong.jpg");
		}
	}
	// Suggest sach thanh cong
		@Test(dataProvider = "dataTC11", priority = 11)
		public void testTC11(String name, String author) {

			driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries");
			WebElement btnSuggest = driver.findElement(By.id("suggest-book"));
			btnSuggest.click();

			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".form-suggest"))));

			WebElement form = driver.findElement(By.cssSelector(".form-suggest"));

			WebElement nameBook = form.findElement(By.cssSelector("input[name='title']"));
			nameBook.clear();
			nameBook.sendKeys(name);
			WebElement nameAuthor = form.findElement(By.cssSelector("input[name='author']"));
			nameAuthor.clear();
			nameAuthor.sendKeys(author);
			WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
			btnSubmit.click();

			WebDriverWait anotherWait = new WebDriverWait(driver, 30);
			anotherWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("img"))));
			WebElement formCheck = driver.findElement(By.cssSelector("img"));

			try {
				assertNotNull(formCheck);
			} catch (AssertionError e) {
				// TODO: handle exception
				capture("suggestBook.jpg");
			}
		}
		
		// Suggest sach thanh cong
		@Test(dataProvider = "dataTC12", priority = 12)
		public void testTC12(String name, String author) {

			driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries");
			WebElement btnSuggest = driver.findElement(By.id("suggest-book"));
			btnSuggest.click();

			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".form-suggest"))));

			WebElement form = driver.findElement(By.cssSelector(".form-suggest"));

			WebElement nameBook = form.findElement(By.cssSelector("input[name='title']"));
			nameBook.clear();
			nameBook.sendKeys(name);
			WebElement nameAuthor = form.findElement(By.cssSelector("input[name='author']"));
			nameAuthor.clear();
			nameAuthor.sendKeys(author);
			WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
			btnSubmit.click();

			WebDriverWait anotherWait = new WebDriverWait(driver, 30);
			anotherWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("img"))));
			WebElement formCheck = driver.findElement(By.cssSelector("img"));

			try {
				assertNotNull(formCheck);
			} catch (AssertionError e) {
				// TODO: handle exception
				capture("suggestBook.jpg");
			}
		}
		
		// Suggest sach thanh cong
		@Test(dataProvider = "dataTC13", priority = 13)
		public void testTC13(String name, String author) {

			driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries");
			WebElement btnSuggest = driver.findElement(By.id("suggest-book"));
			btnSuggest.click();

			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".form-suggest"))));

			WebElement form = driver.findElement(By.cssSelector(".form-suggest"));

			WebElement nameBook = form.findElement(By.cssSelector("input[name='title']"));
			nameBook.clear();
			nameBook.sendKeys(name);
			WebElement nameAuthor = form.findElement(By.cssSelector("input[name='author']"));
			nameAuthor.clear();
			nameAuthor.sendKeys(author);
			WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
			btnSubmit.click();

			WebDriverWait anotherWait = new WebDriverWait(driver, 30);
			anotherWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("img"))));
			WebElement formCheck = driver.findElement(By.cssSelector("img"));

			try {
				assertNotNull(formCheck);
			} catch (AssertionError e) {
				// TODO: handle exception
				capture("suggestBook.jpg");
			}
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
