package com.sun.edu.script.books;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.edu.script.CommonTestCase;
import com.sun.edu.util.XLSHelper;

public class CreateNewBook extends CommonTestCase{
	
	@DataProvider
	public Object[][] duongNTLogin() {
		Object[][] data = XLSHelper.retrieveCells("DuongNT.xls", 0, 2, 2, 2);
		return data;
	}
	@Test(dataProvider = "duongNTLogin", priority = 1)
	public void login(String email, String pass) {
		super.login(email, pass);
	}
	
	@DataProvider
	public Object[][] setBookInfo() {
//		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 2);
		Object[][] data = XLSHelper.retrieveCells("book.xls", 0, 2, 2, 6);
		return data;
	}
//	Create book successfully
	@Test(dataProvider = "setBookInfo", priority = 2)
	public void tc1CreateBook(String title, String author, String avatar, String category, String subCategory, String description) {
		closeModalPhone();
		System.out.println("TC1");
		WebElement prePoint = driver.findElement(By.cssSelector("#reward-points"));
		int expectedPoint = Integer.parseInt(prePoint.getText()) + 15;
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.sendKeys(title);
		
		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.sendKeys(author);
		
		WebElement btnUploadFile = form.findElement(By.cssSelector("input[name='avatar']"));
		btnUploadFile.sendKeys(avatar);
		
		WebElement upload = driver.findElement(By.cssSelector(".modal-body .btn-dark"));
		js.executeScript("arguments[0].scrollIntoView();", upload);
		upload.click();
		
		Select categoryFiled = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		
		categoryFiled.selectByValue(category);
		
		wait("input[type='checkbox'][value='" + subCategory +"']", 5000);
							
		WebElement checkboxSubCategory = form.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory +"']"));
		checkboxSubCategory.click();
		int x = checkboxSubCategory.getLocation().getX();
		int y = checkboxSubCategory.getLocation().getY() - 100;
//		js.executeScript("arguments[0].scrollIntoView(true);", checkboxSubCategory);
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		
		WebElement inputDescription = form.findElement(By.cssSelector("textarea[name='description']"));
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
		
		WebElement afterPoint = driver.findElement(By.cssSelector("#reward-points"));
		String afterPointValue = afterPoint.getText();
		
		String[] realOutput = {afterPointValue, modalText};
		String[] expectedOutput = {String.valueOf(expectedPoint), "OK"};
		assertEquals(realOutput, expectedOutput);
	}
	
	@DataProvider
	public Object[][] setBookInfoWithBlankAvatar() {
//		Object[][] data = XLSHelper.retrieveCells("user.xls", 2, 2);
		Object[][] data = XLSHelper.retrieveCells("book.xls", 0, 3, 3, 6);
		return data;
	}
//	Create book successfully with blank avatar
	@Test(dataProvider = "setBookInfoWithBlankAvatar", priority = 3)
	public void tc2CreateBook(String title, String author, String avatar, String category, String subCategory, String description) {
		System.out.println("TC2");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement prePoint = driver.findElement(By.cssSelector("#reward-points"));
		int expectedPoint = Integer.parseInt(prePoint.getText()) + 15;
		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/books/create");
		
		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.sendKeys(title);
		
		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.sendKeys(author);
		
		Select categoryFiled = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		categoryFiled.selectByValue(category);
		
		wait("input[type='checkbox'][value='" + subCategory +"']", 5000);
							
		WebElement checkboxSubCategory = driver.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory +"']"));
		Point point_checkbox = checkboxSubCategory.getLocation();
		int x = point_checkbox.getX();
		int y = point_checkbox.getY() - 100;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		checkboxSubCategory.click();
		
		WebElement inputDescription = form.findElement(By.cssSelector("textarea[name='description']"));
		js.executeScript("arguments[0].scrollIntoView(true);", inputDescription);
		inputDescription.sendKeys(description);
		
		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView(true);", btnSubmit);
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
		
		WebElement afterPoint = driver.findElement(By.cssSelector("#reward-points"));
		String afterPointValue = afterPoint.getText();
		WebElement body = driver.findElement(By.cssSelector("body"));
		body.click();
		
		String[] realOutput = {afterPointValue, modalText};
		String[] expectedOutput = {String.valueOf(expectedPoint), "OK"};
		assertEquals(realOutput, expectedOutput);
	}
	
	@DataProvider
	public Object[][] setBookInfoWithBlankName() {
		Object[][] data = XLSHelper.retrieveCells("book.xls", 0, 4, 4, 7);
		return data;
	}
//	Create book unsuccessfully with blank name
	@Test(dataProvider = "setBookInfoWithBlankName", priority = 4)
	public void tc3CreateBook(String title, String author, String avatar, String category, String subCategory, String description, String error) {
		System.out.println("TC3");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/books/create");
		
		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));

//		input book name
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.sendKeys(title);
		
		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.sendKeys(author);
		
//		select category
		Select categoryFiled = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		categoryFiled.selectByValue(category);
		wait("input[type='checkbox'][value='" + subCategory +"']", 5000);
		WebElement checkboxSubCategory = driver.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory +"']"));
		Point point_checkbox = checkboxSubCategory.getLocation();
		int x = point_checkbox.getX();
		int y = point_checkbox.getY() - 100;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		checkboxSubCategory.click();
		
//		input description
		WebElement inputDescription = form.findElement(By.cssSelector("textarea[name='description']"));
		js.executeScript("arguments[0].scrollIntoView(true);", inputDescription);
		inputDescription.sendKeys(description);
		
		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView(true);", btnSubmit);
		btnSubmit.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement resultError = driver.findElement(By.cssSelector(".swal-modal > .swal-text")); 
		WebElement body = driver.findElement(By.cssSelector("body"));
		body.click();
		assertEquals(resultError.getText(), error);
	}
	
	@DataProvider
	public Object[][] setBookInfoWithBlankAuthor() {
		Object[][] data = XLSHelper.retrieveCells("book.xls", 0, 5, 5, 7);
		return data;
	}
//	Create book unsuccessfully with blank author
	@Test(dataProvider = "setBookInfoWithBlankAuthor", priority = 5)
	public void tc4CreateBook(String title, String author, String avatar, String category, String subCategory, String description, String error) {
		System.out.println("TC4");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/books/create");
		
		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));

//		input book name
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.sendKeys(title);
		
		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.sendKeys(author);
		
//		select category
		Select categoryFiled = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		categoryFiled.selectByValue(category);
		wait("input[type='checkbox'][value='" + subCategory +"']", 5000);
		WebElement checkboxSubCategory = driver.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory +"']"));
		Point point_checkbox = checkboxSubCategory.getLocation();
		int x = point_checkbox.getX();
		int y = point_checkbox.getY() - 100;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");
		checkboxSubCategory.click();
		
//		input description
		WebElement inputDescription = form.findElement(By.cssSelector("textarea[name='description']"));
		js.executeScript("arguments[0].scrollIntoView(true);", inputDescription);
		inputDescription.sendKeys(description);
		
		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView(true);", btnSubmit);
		btnSubmit.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement resultError = driver.findElement(By.cssSelector(".swal-modal > .swal-text")); 
		WebElement body = driver.findElement(By.cssSelector("body"));
		body.click();
		assertEquals(resultError.getText(), error);
	}
	
	@DataProvider
	public Object[][] setBookInfoWithAllBlank() {
		Object[][] data = XLSHelper.retrieveCells("book.xls", 0, 6, 6, 7);
		return data;
	}
	
//	Create book unsuccessfully with all blank
//	Day la TC co Bug can log
	@Test(dataProvider = "setBookInfoWithAllBlank", priority = 6)
	public void tc5CreateBook(String title, String author, String avatar, String category, String subCategory, String description, String error) {
		System.out.println("TC5");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.navigate().to("https://stg-api-book.sun-asterisk.vn/books/create");
		System.out.println(title + "  " + author + "   " + avatar + "   " + category + "  " + subCategory);
		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		
		WebElement btnSubmit = form.findElement(By.cssSelector("button[type='submit']"));
		js.executeScript("arguments[0].scrollIntoView(true);", btnSubmit);
		btnSubmit.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String resultError = driver.findElement(By.cssSelector(".swal-modal > .swal-text")).getText(); 
		WebElement body = driver.findElement(By.cssSelector("body"));
		body.click();
		assertEquals(resultError, error);
	}
	
	private void closeModalPhone() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebElement phone = driver.findElement(By.cssSelector("#phone_modal"));
		System.out.println();
		if(phone.isDisplayed()) {
			WebElement close = driver.findElement(By.cssSelector("button[type='button']"));
			close.click();
		}
		clickCreateBook();
	}
	
	private void clickCreateBook() {
		wait("a[href='https://stg-api-book.sun-asterisk.vn/books/create']", 5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebElement btnShareBook = driver.findElement(By.cssSelector("a[href='https://stg-api-book.sun-asterisk.vn/books/create']"));
		js.executeScript("arguments[0].scrollIntoView(true);", btnShareBook);
		btnShareBook.click();
	}
}
