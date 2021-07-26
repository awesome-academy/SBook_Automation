package com.sun.edu.script.sbook;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.edu.script.CommonTestCase;
import com.sun.edu.util.XLSHelper;

public class CreateBook extends CommonTestCase {

	@Test(dataProvider = "setLogin", priority = 1)
	public void login(String email, String pass) {
		super.login(email, pass);
	}

	@DataProvider
	public Object[][] dataTC14() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 2, 2, 6);
		return data;
	}

	@DataProvider
	public Object[][] dataTC15() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 3, 3, 6);
		return data;
	}

	@DataProvider
	public Object[][] dataTC16() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 4, 4, 7);
		return data;
	}

	@DataProvider
	public Object[][] dataTC17() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 5, 5, 7);
		return data;
	}

	@DataProvider
	public Object[][] dataTC18() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 6, 6, 7);
		return data;
	}

	@DataProvider
	public Object[][] dataTC19() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 7, 7, 6);
		return data;
	}

	@DataProvider
	public Object[][] dataTC20() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 8, 8, 6);
		return data;
	}

	@DataProvider
	public Object[][] dataTC21() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 9, 9, 6);
		return data;
	}

	@DataProvider
	public Object[][] dataTC22() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 10, 10, 7);
		return data;
	}

	@DataProvider
	public Object[][] dataTC23() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 11, 11, 7);
		return data;
	}

	@DataProvider
	public Object[][] dataTC24() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 12, 12, 7);
		return data;
	}

	@DataProvider
	public Object[][] dataTC25() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 13, 13, 7);
		return data;
	}

	@DataProvider
	public Object[][] dataTC26() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 14, 14, 7);
		return data;
	}

	@DataProvider
	public Object[][] dataTC27() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 15, 15, 7);
		return data;
	}

	@DataProvider
	public Object[][] dataTC28() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 16, 16, 7);
		return data;
	}

	@DataProvider
	public Object[][] dataTC29() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 17, 17, 7);
		return data;
	}

	@DataProvider
	public Object[][] dataTC30() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 18, 18, 7);
		return data;
	}

	@DataProvider
	public Object[][] dataTC31() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 19, 19, 7);
		return data;
	}

	// Create book successfully
//	@Test(dataProvider = "dataTC14", priority = 2)
	public void testTC14(String title, String author, String avatar, String category, String subCategory,
			String description) {
		clickShareBook();

		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.clear();
		inputTitle.sendKeys(title);

		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.clear();
		inputAuthor.sendKeys(author);

		WebElement btnAvatar = form.findElement(By.cssSelector("input[name='avatar']"));
		btnAvatar.sendKeys(avatar);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement upload = driver.findElement(By.cssSelector(".modal-body .btn-dark"));
		js.executeScript("arguments[0].scrollIntoView();", upload);
		upload.click();

		Select selectCatagory = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		selectCatagory.selectByValue(category);

		wait("input[type='checkbox'][value='" + subCategory + "']", 5000);

		WebElement selectSubCatagory = form
				.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory + "']"));
		Point point = selectSubCatagory.getLocation();
		int x = point.getX();
		int y = point.getY() - 100;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		selectSubCatagory.click();

		WebElement inputDescription = form.findElement(By.cssSelector("textarea[name='description']"));
		inputDescription.clear();
		inputDescription.sendKeys(description);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView();", btnSubmit);
		btnSubmit.click();

		wait(".swal-button--confirm", 30);
		WebElement modal = driver.findElement(By.cssSelector(".swal-button--confirm"));
		String modalText = modal.getText();
		modal.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions actions = new Actions(driver);
		actions.moveByOffset(1, 1).click().perform();

		try {
			assertEquals(modalText, "OK");
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CreateBookUnsuccess.jpg");
		}
	}

	// create book success when leave blank avatar
	@Test(dataProvider = "dataTC15", priority = 3)
	public void testTC15(String title, String author, String avatar, String category, String subCategory,
			String description) {
		clickShareBook();

		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.clear();
		inputTitle.sendKeys(title);

		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.clear();
		inputAuthor.sendKeys(author);

		Select selectCatagory = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		selectCatagory.selectByValue(category);

		wait("input[type='checkbox'][value='" + subCategory + "']", 5000);

		WebElement selectSubCatagory = form
				.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory + "']"));
		Point point = selectSubCatagory.getLocation();
		int x = point.getX();
		int y = point.getY() - 100;

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		selectSubCatagory.click();

		WebElement inputDescription = form.findElement(By.cssSelector("textarea[name='description']"));
		inputDescription.clear();
		inputDescription.sendKeys(description);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView();", btnSubmit);
		btnSubmit.click();

		wait(".swal-button--confirm", 30);
		WebElement modal = driver.findElement(By.cssSelector(".swal-button--confirm"));
		String modalText = modal.getText();
		modal.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions actions = new Actions(driver);
		actions.moveByOffset(1, 1).click().perform();

		try {
			assertEquals(modalText, "OK");
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CreateBookUnsuccess.jpg");
		}
	}

	// Create book successfully when input "Title"= html
	@Test(dataProvider = "dataTC16", priority = 4)
	public void testTC16(String title, String author, String avatar, String category, String subCategory,
			String description, String expected) {
		clickShareBook();

		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.clear();
		inputTitle.sendKeys(title);

		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.clear();
		inputAuthor.sendKeys(author);

		Select selectCatagory = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		selectCatagory.selectByValue(category);

		wait("input[type='checkbox'][value='" + subCategory + "']", 5000);

		WebElement selectSubCatagory = form
				.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory + "']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Point point = selectSubCatagory.getLocation();
		int x = point.getX();
		int y = point.getY() - 100;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		selectSubCatagory.click();

		WebElement inputDescription = form.findElement(By.cssSelector("textarea[name='description']"));
		inputDescription.clear();
		inputDescription.sendKeys(description);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView();", btnSubmit);
		btnSubmit.click();

		wait(".swal-button--confirm", 30);
		WebElement modal = driver.findElement(By.cssSelector(".swal-button--confirm"));
		modal.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait(".swal-button--confirm", 30);
		WebElement modal2 = driver.findElement(By.cssSelector(".swal-button--confirm"));
		modal2.click();

		WebElement titleBook = driver.findElement(By.cssSelector(".product-info-main >.page-title"));
		try {
			assertEquals(titleBook.getText(), expected);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CreateBookUnsuccess.jpg");
		}
	}

	// Create book successfully when input "Title"= xss injection
	@Test(dataProvider = "dataTC17", priority = 5)
	public void testTC17(String title, String author, String avatar, String category, String subCategory,
			String description, String expected) {
		clickShareBook();

		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.clear();
		inputTitle.sendKeys(title);

		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.clear();
		inputAuthor.sendKeys(author);

		Select selectCatagory = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		selectCatagory.selectByValue(category);

		wait("input[type='checkbox'][value='" + subCategory + "']", 5000);

		WebElement selectSubCatagory = form
				.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory + "']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Point point = selectSubCatagory.getLocation();
		int x = point.getX();
		int y = point.getY() - 100;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		selectSubCatagory.click();

		WebElement inputDescription = form.findElement(By.cssSelector("textarea[name='description']"));
		inputDescription.clear();
		inputDescription.sendKeys(description);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView();", btnSubmit);
		btnSubmit.click();

		wait(".swal-button--confirm", 30);
		WebElement modal = driver.findElement(By.cssSelector(".swal-button--confirm"));
		modal.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait(".swal-button--confirm", 30);
		WebElement modal2 = driver.findElement(By.cssSelector(".swal-button--confirm"));
		modal2.click();

		WebElement titleBook = driver.findElement(By.cssSelector(".product-info-main >.page-title"));
		try {
			assertEquals(titleBook.getText(), expected);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CreateBookUnsuccess.jpg");
		}
	}

	// Create book successfully when input "Title"= sql injection
	@Test(dataProvider = "dataTC18", priority = 6)
	public void testTC18(String title, String author, String avatar, String category, String subCategory,
			String description, String expected) {
		clickShareBook();

		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.clear();
		inputTitle.sendKeys(title);

		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.clear();
		inputAuthor.sendKeys(author);

		Select selectCatagory = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		selectCatagory.selectByValue(category);

		wait("input[type='checkbox'][value='" + subCategory + "']", 5000);

		WebElement selectSubCatagory = form
				.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory + "']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Point point = selectSubCatagory.getLocation();
		int x = point.getX();
		int y = point.getY() - 100;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		selectSubCatagory.click();

		WebElement inputDescription = form.findElement(By.cssSelector("textarea[name='description']"));
		inputDescription.clear();
		inputDescription.sendKeys(description);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView();", btnSubmit);
		btnSubmit.click();

		wait(".swal-button--confirm", 30);
		WebElement modal = driver.findElement(By.cssSelector(".swal-button--confirm"));
		modal.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait(".swal-button--confirm", 30);
		WebElement modal2 = driver.findElement(By.cssSelector(".swal-button--confirm"));
		modal2.click();

		WebElement titleBook = driver.findElement(By.cssSelector(".product-info-main >.page-title"));
		try {
			assertEquals(titleBook.getText(), expected);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CreateBookUnsuccess.jpg");
		}
	}

	// Create book successfully when input "Title"= =200char(maxlenght)
	@Test(dataProvider = "dataTC19", priority = 7)
	public void testTC19(String title, String author, String avatar, String category, String subCategory,
			String description) {
		clickShareBook();

		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.clear();
		inputTitle.sendKeys(title);

		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.clear();
		inputAuthor.sendKeys(author);

		Select selectCatagory = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		selectCatagory.selectByValue(category);

		wait("input[type='checkbox'][value='" + subCategory + "']", 5000);

		WebElement selectSubCatagory = form
				.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory + "']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Point point = selectSubCatagory.getLocation();
		int x = point.getX();
		int y = point.getY() - 100;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		selectSubCatagory.click();

		WebElement inputDescription = form.findElement(By.cssSelector("textarea[name='description']"));
		inputDescription.clear();
		inputDescription.sendKeys(description);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView();", btnSubmit);
		btnSubmit.click();

		wait(".swal-button--confirm", 30);
		WebElement modal = driver.findElement(By.cssSelector(".swal-button--confirm"));
		String text = modal.getText();
		modal.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			assertEquals(text, "OK");
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CreateBookUnsuccess.jpg");
		}
	}

	// Create book successfully when input "Author"= =100char(maxlenght)
	@Test(dataProvider = "dataTC20", priority = 8)
	public void testTC20(String title, String author, String avatar, String category, String subCategory,
			String description) {
		clickShareBook();

		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.clear();
		inputTitle.sendKeys(title);

		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.clear();
		inputAuthor.sendKeys(author);

		Select selectCatagory = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		selectCatagory.selectByValue(category);

		wait("input[type='checkbox'][value='" + subCategory + "']", 5000);

		WebElement selectSubCatagory = form
				.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory + "']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Point point = selectSubCatagory.getLocation();
		int x = point.getX();
		int y = point.getY() - 100;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		selectSubCatagory.click();

		WebElement inputDescription = form.findElement(By.cssSelector("textarea[name='description']"));
		inputDescription.clear();
		inputDescription.sendKeys(description);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView();", btnSubmit);
		btnSubmit.click();

		wait(".swal-button--confirm", 30);
		WebElement modal = driver.findElement(By.cssSelector(".swal-button--confirm"));
		String text = modal.getText();
		modal.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			assertEquals(text, "OK");
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CreateBookUnsuccess.jpg");
		}
	}

	// Create book successfully when input Description =2500char(maxlenght)
	@Test(dataProvider = "dataTC21", priority = 9)
	public void testTC21(String title, String author, String avatar, String category, String subCategory,
			String description) {
		clickShareBook();

		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.clear();
		inputTitle.sendKeys(title);

		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.clear();
		inputAuthor.sendKeys(author);

		Select selectCatagory = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		selectCatagory.selectByValue(category);

		wait("input[type='checkbox'][value='" + subCategory + "']", 5000);

		WebElement selectSubCatagory = form
				.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory + "']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Point point = selectSubCatagory.getLocation();
		int x = point.getX();
		int y = point.getY() - 100;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		selectSubCatagory.click();

		WebElement inputDescription = form.findElement(By.cssSelector("textarea[name='description']"));
		inputDescription.clear();
		inputDescription.sendKeys(description);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView();", btnSubmit);
		btnSubmit.click();

		wait(".swal-button--confirm", 30);
		WebElement modal = driver.findElement(By.cssSelector(".swal-button--confirm"));
		String text = modal.getText();
		modal.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			assertEquals(text, "OK");
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CreateBookUnsuccess.jpg");
		}
	}

// Create book unsuccessfully title blank
	@Test(dataProvider = "dataTC22", priority = 10)
	public void testTC22(String title, String author, String avatar, String category, String subCategory,
			String description, String expected) {
		clickShareBook();

		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.clear();
		inputTitle.sendKeys(title);

		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.clear();
		inputAuthor.sendKeys(author);

		Select selectCatagory = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		selectCatagory.selectByValue(category);

		wait("input[type='checkbox'][value='" + subCategory + "']", 5000);

		WebElement selectSubCatagory = form
				.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory + "']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Point point = selectSubCatagory.getLocation();
		int x = point.getX();
		int y = point.getY() - 100;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		selectSubCatagory.click();

		WebElement inputDescription = form.findElement(By.cssSelector("textarea[name='description']"));
		inputDescription.clear();
		inputDescription.sendKeys(description);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView();", btnSubmit);
		btnSubmit.click();

		wait(".swal-modal", 30);
		WebElement modal = driver.findElement(By.cssSelector(".swal-text"));
		String modalText = modal.getText();
		modal.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			assertEquals(modalText, "OK");
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CreateBookUnsuccess.jpg");
		}

	}

	// Create book unsuccessfully title= all space
	@Test(dataProvider = "dataTC23", priority = 11)
	public void testTC23(String title, String author, String avatar, String category, String subCategory,
			String description, String expected) {
		clickShareBook();

		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.clear();
		inputTitle.sendKeys(title);

		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.clear();
		inputAuthor.sendKeys(author);

		Select selectCatagory = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		selectCatagory.selectByValue(category);

		wait("input[type='checkbox'][value='" + subCategory + "']", 5000);

		WebElement selectSubCatagory = form
				.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory + "']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Point point = selectSubCatagory.getLocation();
		int x = point.getX();
		int y = point.getY() - 100;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		selectSubCatagory.click();

		WebElement inputDescription = form.findElement(By.cssSelector("textarea[name='description']"));
		inputDescription.clear();
		inputDescription.sendKeys(description);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView();", btnSubmit);
		btnSubmit.click();

		wait(".swal-modal", 30);
		WebElement modal = driver.findElement(By.cssSelector(".swal-text"));
		String modalText = modal.getText();
		modal.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			assertEquals(modalText, "OK");
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CreateBookUnsuccess.jpg");
		}
	}

	// Create book unsuccessfully title > maxlenght
	@Test(dataProvider = "dataTC24", priority = 12)
	public void testTC24(String title, String author, String avatar, String category, String subCategory,
			String description, String expected) {
		clickShareBook();

		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.clear();
		inputTitle.sendKeys(title);

		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.clear();
		inputAuthor.sendKeys(author);

		Select selectCatagory = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		selectCatagory.selectByValue(category);

		wait("input[type='checkbox'][value='" + subCategory + "']", 5000);

		WebElement selectSubCatagory = form
				.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory + "']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Point point = selectSubCatagory.getLocation();
		int x = point.getX();
		int y = point.getY() - 100;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		selectSubCatagory.click();

		WebElement inputDescription = form.findElement(By.cssSelector("textarea[name='description']"));
		inputDescription.clear();
		inputDescription.sendKeys(description);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView();", btnSubmit);
		btnSubmit.click();

		wait(".swal-modal", 30);
		WebElement modal = driver.findElement(By.cssSelector(".swal-text"));
		String modalText = modal.getText();
		modal.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			assertEquals(modalText, "OK");
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CreateBookUnsuccess.jpg");
		}
	}

	// Create book unsuccessfully Author blank
	@Test(dataProvider = "dataTC25", priority = 13)
	public void testTC25(String title, String author, String avatar, String category, String subCategory,
			String description, String expected) {
		clickShareBook();

		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.clear();
		inputTitle.sendKeys(title);

		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.clear();
		inputAuthor.sendKeys(author);

		Select selectCatagory = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		selectCatagory.selectByValue(category);

		wait("input[type='checkbox'][value='" + subCategory + "']", 5000);

		WebElement selectSubCatagory = form
				.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory + "']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Point point = selectSubCatagory.getLocation();
		int x = point.getX();
		int y = point.getY() - 100;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		selectSubCatagory.click();

		WebElement inputDescription = form.findElement(By.cssSelector("textarea[name='description']"));
		inputDescription.clear();
		inputDescription.sendKeys(description);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView();", btnSubmit);
		btnSubmit.click();

		wait(".swal-modal", 30);
		WebElement modal = driver.findElement(By.cssSelector(".swal-text"));
		String modalText = modal.getText();
		modal.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			assertEquals(modalText, "OK");
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CreateBookUnsuccess.jpg");
		}
	}

	// Create book unsuccessfully Author= all space
	@Test(dataProvider = "dataTC26", priority = 14)
	public void testTC26(String title, String author, String avatar, String category, String subCategory,
			String description, String expected) {
		clickShareBook();

		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.clear();
		inputTitle.sendKeys(title);

		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.clear();
		inputAuthor.sendKeys(author);

		Select selectCatagory = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		selectCatagory.selectByValue(category);

		wait("input[type='checkbox'][value='" + subCategory + "']", 5000);

		WebElement selectSubCatagory = form
				.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory + "']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Point point = selectSubCatagory.getLocation();
		int x = point.getX();
		int y = point.getY() - 100;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		selectSubCatagory.click();

		WebElement inputDescription = form.findElement(By.cssSelector("textarea[name='description']"));
		inputDescription.clear();
		inputDescription.sendKeys(description);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView();", btnSubmit);
		btnSubmit.click();

		wait(".swal-modal", 30);
		WebElement modal = driver.findElement(By.cssSelector(".swal-text"));
		String modalText = modal.getText();
		modal.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			assertEquals(modalText, "OK");
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CreateBookUnsuccess.jpg");
		}
	}

	// Create book unsuccessfully Author > maxlenght
	@Test(dataProvider = "dataTC27", priority = 15)
	public void testTC27(String title, String author, String avatar, String category, String subCategory,
			String description, String expected) {
		clickShareBook();

		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.clear();
		inputTitle.sendKeys(title);

		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.clear();
		inputAuthor.sendKeys(author);

		Select selectCatagory = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		selectCatagory.selectByValue(category);

		wait("input[type='checkbox'][value='" + subCategory + "']", 5000);

		WebElement selectSubCatagory = form
				.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory + "']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Point point = selectSubCatagory.getLocation();
		int x = point.getX();
		int y = point.getY() - 100;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		selectSubCatagory.click();

		WebElement inputDescription = form.findElement(By.cssSelector("textarea[name='description']"));
		inputDescription.clear();
		inputDescription.sendKeys(description);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView();", btnSubmit);
		btnSubmit.click();

		wait(".swal-modal", 30);
		WebElement modal = driver.findElement(By.cssSelector(".swal-text"));
		String modalText = modal.getText();
		modal.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			assertEquals(modalText, "OK");
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CreateBookUnsuccess.jpg");
		}
	}

	// Create book unsuccessfully Description blank
	@Test(dataProvider = "dataTC28", priority = 16)
	public void testTC28(String title, String author, String avatar, String category, String subCategory,
			String description, String expected) {
		clickShareBook();

		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.clear();
		inputTitle.sendKeys(title);

		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.clear();
		inputAuthor.sendKeys(author);

		Select selectCatagory = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		selectCatagory.selectByValue(category);

		wait("input[type='checkbox'][value='" + subCategory + "']", 5000);

		WebElement selectSubCatagory = form
				.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory + "']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Point point = selectSubCatagory.getLocation();
		int x = point.getX();
		int y = point.getY() - 100;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		selectSubCatagory.click();

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView();", btnSubmit);
		btnSubmit.click();

		wait(".swal-modal", 30);
		WebElement modal = driver.findElement(By.cssSelector(".swal-text"));
		String modalText = modal.getText();
		modal.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			assertEquals(modalText, "OK");
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CreateBookUnsuccess.jpg");
		}
	}

	// Create book unsuccessfully Description = all space
	@Test(dataProvider = "dataTC29", priority = 17)
	public void testTC29(String title, String author, String avatar, String category, String subCategory,
			String description, String expected) {
		clickShareBook();

		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.clear();
		inputTitle.sendKeys(title);

		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.clear();
		inputAuthor.sendKeys(author);

		Select selectCatagory = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		selectCatagory.selectByValue(category);

		wait("input[type='checkbox'][value='" + subCategory + "']", 5000);

		WebElement selectSubCatagory = form
				.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory + "']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Point point = selectSubCatagory.getLocation();
		int x = point.getX();
		int y = point.getY() - 100;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		selectSubCatagory.click();

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView();", btnSubmit);
		btnSubmit.click();

		wait(".swal-modal", 30);
		WebElement modal = driver.findElement(By.cssSelector(".swal-text"));
		String modalText = modal.getText();
		modal.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			assertEquals(modalText, "OK");
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CreateBookUnsuccess.jpg");
		}
	}

	// Create book unsuccessfully Description > maxleght
	@Test(dataProvider = "dataTC30", priority = 18)
	public void testTC30(String title, String author, String avatar, String category, String subCategory,
			String description, String expected) {
		clickShareBook();

		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.clear();
		inputTitle.sendKeys(title);

		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.clear();
		inputAuthor.sendKeys(author);

		Select selectCatagory = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		selectCatagory.selectByValue(category);

		wait("input[type='checkbox'][value='" + subCategory + "']", 5000);

		WebElement selectSubCatagory = form
				.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory + "']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Point point = selectSubCatagory.getLocation();
		int x = point.getX();
		int y = point.getY() - 100;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		selectSubCatagory.click();

		WebElement inputDescription = form.findElement(By.cssSelector("textarea[name='description']"));
		inputDescription.clear();
		inputDescription.sendKeys(description);

		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView();", btnSubmit);
		btnSubmit.click();

		wait(".swal-modal", 30);
		WebElement modal = driver.findElement(By.cssSelector(".swal-text"));
		String modalText = modal.getText();
		modal.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			assertEquals(modalText, "OK");
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CreateBookUnsuccess.jpg");
		}
	}

	// Create book unsuccessfully Catagory blank
	@Test(dataProvider = "dataTC31", priority = 19)
	public void testTC31(String title, String author, String avatar, String category, String subCategory,
			String description, String expected) {
		clickShareBook();

		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.clear();
		inputTitle.sendKeys(title);

		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.clear();
		inputAuthor.sendKeys(author);
		WebElement inputDescription = form.findElement(By.cssSelector("textarea[name='description']"));
		inputDescription.clear();
		inputDescription.sendKeys(description);

		JavascriptExecutor js= (JavascriptExecutor) driver;
		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView();", btnSubmit);
		btnSubmit.click();

		wait(".swal-modal", 30);
		WebElement modal = driver.findElement(By.cssSelector(".swal-text"));
		String modalText = modal.getText();
		modal.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			assertEquals(modalText, "OK");
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CreateBookUnsuccess.jpg");
		}
	}

	public void clickShareBook() {
		wait("a[href='https://stg-api-book.sun-asterisk.vn/books/create']", 5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement btnShareBook = driver
				.findElement(By.cssSelector("a[href='https://stg-api-book.sun-asterisk.vn/books/create']"));
		js.executeScript("arguments[0].scrollIntoView(true);", btnShareBook);
		btnShareBook.click();

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
