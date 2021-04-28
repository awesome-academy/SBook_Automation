package com.sun.edu.script.books;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.sun.edu.script.CommonTestCase;

public class CreateNewBook extends CommonTestCase{
	
	@Test(dataProvider = "SetLogin")
	public void login(String email, String pass) {
		super.login(email, pass);
	}
	
//	Create book successfully
	@Test(dataProvider = "setBookInfo")
	public void tc1CreateBook(String title, String author, String avatar, String category, String subCategory, String description) {
		clickCreateBook();
		WebElement form = driver.findElement(By.cssSelector("#add-book-request"));
		WebElement inputTitle = form.findElement(By.cssSelector("input[name='title']"));
		inputTitle.sendKeys(title);
		
		WebElement inputAuthor = form.findElement(By.cssSelector("input[name='author']"));
		inputAuthor.sendKeys(author);
		
//		WebElement btnUploadFile = form.findElement(By.cssSelector("input[name='avatar']"));
//		btnUploadFile.sendKeys(avatar);
//		
//		wait("#book-avatar-modal");
//		WebElement upload = driver.findElement(By.cssSelector(".modal-body .btn-dark"));
//		upload.click();
		
		Select categoryFiled = new Select(form.findElement(By.cssSelector("select[name='parent-category']")));
		categoryFiled.selectByValue(category);
		
		wait("input[type='checkbox'][value='" + subCategory +"']", 5000);
							
		WebElement checkboxSubCategory = form.findElement(By.cssSelector("input[type='checkbox'][value='" + subCategory +"']"));
		checkboxSubCategory.click();
		
		WebElement inputDescription = form.findElement(By.cssSelector("textarea[name='description']"));
		inputDescription.sendKeys(description);
		
		WebElement btnSubmit = form.findElement(By.cssSelector("#btn-info"));
		btnSubmit.click();
	}
	
	private void clickCreateBook() {
		Actions actions = new Actions(driver);
		actions.moveByOffset(1200, 67).click().perform();
		wait("a[href='https://stg-api-book.sun-asterisk.vn/books/create']", 5000);
	
		WebElement btnShareBook = driver.findElement(By.cssSelector("a[href='https://stg-api-book.sun-asterisk.vn/books/create']"));
		btnShareBook.click();
	}
}
