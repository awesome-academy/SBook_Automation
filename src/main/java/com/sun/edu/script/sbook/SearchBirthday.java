package com.sun.edu.script.sbook;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.formula.functions.Count;
import org.apache.xalan.xsltc.dom.CurrentNodeListFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.edu.script.CommonTestCase;
import com.sun.edu.util.XLSHelper;

public class SearchBirthday extends CommonTestCase {

	@Test(dataProvider = "setLogin", priority = 1)
	public void login(String email, String pass) {
		super.login(email, pass);
	}

	@DataProvider
	public Object[][] dataTC32() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 3, 2, 2, 2);
		return data;
	}

	@DataProvider
	public Object[][] dataTC33() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 3, 3, 3, 2);
		return data;
	}

	@DataProvider
	public Object[][] dataTC34() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 3, 4, 4, 2);
		return data;
	}

	@DataProvider
	public Object[][] dataTC35() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 3, 5, 5, 2);
		return data;
	}

	@DataProvider
	public Object[][] dataTC36() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 3, 6, 6, 2);
		return data;
	}

	@DataProvider
	public Object[][] dataTC37() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 3, 7, 7, 2);
		return data;
	}

	@DataProvider
	public Object[][] dataTC38() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 3, 8, 8, 2);
		return data;
	}

	@DataProvider
	public Object[][] dataTC39() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 3, 9, 9, 5);
		return data;
	}

	// search co ket qua, title = tieng Viet
//	@Test(dataProvider = "dataTC32", priority = 2)
	public void testTC32(String book, String expected) {
		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries");
		WebElement form = driver.findElement(By.cssSelector("#header-search"));
		WebElement inputSearch = form.findElement(By.cssSelector("input[type='text']"));
		inputSearch.clear();
		inputSearch.sendKeys(book);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();
		WebElement searhAppend = driver.findElement(By.cssSelector(".suggestions-list"));
		List<WebElement> element = searhAppend.findElements(By.cssSelector(".result-entry"));

		try {
			assertEquals(element.size(), expected);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}

	}

	// not result found, data = sql injection
//	@Test(dataProvider = "dataTC33", priority = 3)
	public void testTC33(String book, String expected) {
		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries");
		WebElement form = driver.findElement(By.cssSelector("#header-search"));
		WebElement inputSearch = form.findElement(By.cssSelector("input[type='text']"));
		inputSearch.clear();
		inputSearch.sendKeys(book);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement result = driver.findElement(By.cssSelector(".suggestions-list"));
		try {
			assertEquals(result.getText(), expected);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}

	}

	// title= tieng Viet khong dau
//	@Test(dataProvider = "dataTC34", priority = 4)
	public void testTC34(String book, String expected) {
		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries");
		WebElement form = driver.findElement(By.cssSelector("#header-search"));
		WebElement inputSearch = form.findElement(By.cssSelector("input[type='text']"));
		inputSearch.clear();
		inputSearch.sendKeys(book);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		WebElement searhAppend = driver.findElement(By.cssSelector(".suggestions-list"));
		List<WebElement> element = searhAppend.findElements(By.cssSelector(".result-entry"));

		try {
			assertEquals(element.size(), expected);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}

	}

	// strim space
//	@Test(dataProvider = "dataTC35", priority = 5)
	public void testTC35(String book, String expected) {
		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries");
		WebElement form = driver.findElement(By.cssSelector("#header-search"));
		WebElement inputSearch = form.findElement(By.cssSelector("input[type='text']"));
		inputSearch.clear();
		inputSearch.sendKeys(book);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		WebElement searhAppend = driver.findElement(By.cssSelector(".suggestions-list"));
		List<WebElement> element = searhAppend.findElements(By.cssSelector(".result-entry"));

		try {
			assertEquals(element.size(), expected);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}

	}

	// title = html, no result found
//	@Test(dataProvider = "dataTC36", priority = 6)
	public void testTC36(String book, String expected) {
		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries");
		WebElement form = driver.findElement(By.cssSelector("#header-search"));
		WebElement inputSearch = form.findElement(By.cssSelector("input[type='text']"));
		inputSearch.clear();
		inputSearch.sendKeys(book);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement result = driver.findElement(By.cssSelector(".suggestions-list"));
		try {
			assertEquals(result.getText(), expected);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}
	}

	// title = xss injection, no result found
//	@Test(dataProvider = "dataTC37", priority = 7)
	public void testTC37(String book, String expected) {
		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries");
		WebElement form = driver.findElement(By.cssSelector("#header-search"));
		WebElement inputSearch = form.findElement(By.cssSelector("input[type='text']"));
		inputSearch.clear();
		inputSearch.sendKeys(book);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement result = driver.findElement(By.cssSelector(".suggestions-list"));
		try {
			assertEquals(result.getText(), expected);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}
	}

	// search hien thi phan trang
//	@Test(dataProvider = "dataTC38", priority = 8)
	public void testTC38(String book, String expected) {
		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries");
		WebElement form = driver.findElement(By.cssSelector("#header-search"));
		WebElement inputSearch = form.findElement(By.cssSelector("input[type='text']"));
		inputSearch.clear();
		inputSearch.sendKeys(book);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		WebElement searhAppend1 = driver.findElement(By.cssSelector(".suggestions-list"));
		List<WebElement> element1 = searhAppend1.findElements(By.cssSelector(".result-entry"));
		int totalBook1 = element1.size();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement paginationElement = driver.findElement(By.cssSelector(".pagination"));
		int x = paginationElement.getLocation().getX();
		int y = paginationElement.getLocation().getY();
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		WebElement page = paginationElement.findElement(
				By.cssSelector("a[href='https://stg-api-book.sun-asterisk.vn/search?req=quy%E1%BB%83n&titles=2']"));
		page.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement searhAppend2 = driver.findElement(By.cssSelector(".suggestions-list"));
		List<WebElement> element2 = searhAppend2.findElements(By.cssSelector(".result-entry"));
		int totalBook2 = element2.size();
		int totalBook = totalBook1 + totalBook2;

		try {
			assertEquals(totalBook, expected);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}

	}

	// Check pagination
	@Test(dataProvider = "dataTC39", priority = 9)
	public void testTC39(String book, String recordPage1, String recordPage2, String nextPage, String previousPage) {
		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/birthday-libraries");
		WebElement form = driver.findElement(By.cssSelector("#header-search"));
		WebElement inputSearch = form.findElement(By.cssSelector("input[type='text']"));
		inputSearch.clear();
		inputSearch.sendKeys(book);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		WebElement searhAppend1 = driver.findElement(By.cssSelector(".suggestions-list"));
		List<WebElement> element1 = searhAppend1.findElements(By.cssSelector(".result-entry"));
		int totalBook1 = element1.size();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement paginationPage1 = driver.findElement(By.cssSelector(".pagination"));
		int x1 = paginationPage1.getLocation().getX();
		int y1 = paginationPage1.getLocation().getY();
		js.executeScript("window.scrollTo( " + x1 + ", " + y1 + ")", "");
		WebElement next = paginationPage1.findElement(By.cssSelector("a[rel='next']"));
		next.click();
		String currentNextPage = driver.getCurrentUrl();
		// System.out.println(currentNextPage);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement searhAppend2 = driver.findElement(By.cssSelector(".suggestions-list"));
		List<WebElement> element2 = searhAppend2.findElements(By.cssSelector(".result-entry"));
		int totalBook2 = element2.size();

		WebElement paginationPage2 = driver.findElement(By.cssSelector(".pagination"));
		WebElement previous = paginationPage2.findElement(By.cssSelector("a[rel='prev']"));
		previous.click();
		String currentPreviousPage = driver.getCurrentUrl();

		String[] actual = { Integer.toString(totalBook1), Integer.toString(totalBook2), currentNextPage,
				currentPreviousPage };
		String[] expected = { recordPage1, recordPage2, nextPage, previousPage };
		try {
			assertEquals(actual, expected);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}

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
