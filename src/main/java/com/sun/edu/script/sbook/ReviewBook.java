package com.sun.edu.script.sbook;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
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

public class ReviewBook extends CommonTestCase {
	@Test(dataProvider = "setLogin", priority = 1)
	public void login(String email, String pass) {
		super.login(email, pass);
		closeDebug();
	}

	@DataProvider
	public Object[][] dataTC46() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 5, 3, 3, 3);
		return data;
	}

	@DataProvider
	public Object[][] dataTC47() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 5, 4, 4, 4);
		return data;
	}

	@DataProvider
	public Object[][] dataTC48() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 5, 5, 5, 3);
		return data;
	}

	@DataProvider
	public Object[][] dataTC49() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 5, 6, 6, 4);
		return data;
	}

	@DataProvider
	public Object[][] dataTC50() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 5, 7, 7, 4);
		return data;
	}

	@DataProvider
	public Object[][] dataTC51() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 5, 8, 8, 3);
		return data;
	}

	@DataProvider
	public Object[][] dataTC52() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 5, 9, 9, 3);
		return data;
	}

	@DataProvider
	public Object[][] dataTC53() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 5, 11, 11, 4);
		return data;
	}

	@DataProvider
	public Object[][] dataTC54() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 5, 12, 12, 4);
		return data;
	}

	@DataProvider
	public Object[][] dataTC55() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 5, 13, 13, 4);
		return data;
	}

	@DataProvider
	public Object[][] dataTC56() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 5, 14, 14, 4);
		return data;
	}

	@DataProvider
	public Object[][] dataTC57() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 5, 15, 15, 4);
		return data;
	}

	@DataProvider
	public Object[][] dataTC58() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 5, 16, 16, 1);
		return data;
	}

	@DataProvider
	public Object[][] dataTC59() {
		Object[][] data = XLSHelper.retrieveCells("user.xls", 5, 17, 17, 2);
		return data;
	}

	// Review book successfull
//	@Test(dataProvider = "dataTC46", priority = 2)
	public void testTC46(String url, String titleReview, String contentReview) {
		clickAddReview(url);
		WebElement form = driver.findElement(By.cssSelector(".contact-form"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		wait("label.star.star-5", 3000);
		WebElement selectRate = form.findElement(By.cssSelector("label.star.star-5"));
		selectRate.click();

		WebElement title = form.findElement(By.cssSelector("#title"));
		title.clear();
		title.sendKeys(titleReview);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement textarea = form.findElement(By.cssSelector("#mytextarea"));
		int x = textarea.getLocation().getX();
		int y = textarea.getLocation().getY() + 400;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");

		driver.switchTo().frame(0);
		WebElement content = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(content).perform();
		driver.findElement(By.xpath("/html/body")).sendKeys(contentReview);

		driver.switchTo().defaultContent();
		WebElement form2 = driver.findElement(By.cssSelector(".contact-form"));
		WebElement btnSubmit = form2.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();
		wait(".swal-modal", 3000);
		WebElement modal = driver.findElement(By.cssSelector(".swal-modal"));
		WebElement btnOk = modal.findElement(By.cssSelector(".swal-button--confirm"));
		btnOk.click();

		WebElement expected = driver.findElement(By.cssSelector(".review-title"));

		try {
			assertEquals(expected.getText(), titleReview);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}

	}

	// Review book successfully with "Title" = html
//	@Test(dataProvider = "dataTC47", priority = 3)
	public void testTC47(String url, String titleReview, String contentReview, String expectedReview) {
		clickAddReview(url);
		WebElement form = driver.findElement(By.cssSelector(".contact-form"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		wait("label.star.star-5", 3000);
		WebElement selectRate = form.findElement(By.cssSelector("label.star.star-5"));
		selectRate.click();

		WebElement title = form.findElement(By.cssSelector("#title"));
		title.clear();
		title.sendKeys(titleReview);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement textarea = form.findElement(By.cssSelector("#mytextarea"));
		int x = textarea.getLocation().getX();
		int y = textarea.getLocation().getY() + 400;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");

		driver.switchTo().frame(0);
		WebElement content = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(content).perform();
		driver.findElement(By.xpath("/html/body")).sendKeys(contentReview);

		driver.switchTo().defaultContent();
		WebElement form2 = driver.findElement(By.cssSelector(".contact-form"));
		WebElement btnSubmit = form2.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement modal = driver.findElement(By.cssSelector(".swal-modal"));
		WebElement btnOk = modal.findElement(By.cssSelector(".swal-button--confirm"));
		btnOk.click();

		WebElement expected = driver.findElement(By.cssSelector(".review-title"));

		try {
			assertEquals(expected.getText(), expectedReview);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}

	}

	// Review book successfully with "Title" contain special character
//	@Test(dataProvider = "dataTC48", priority = 4)
	public void testTC48(String url, String titleReview, String contentReview, String expectedReview) {
		clickAddReview(url);
		WebElement form = driver.findElement(By.cssSelector(".contact-form"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		wait("label.star.star-5", 3000);
		WebElement selectRate = form.findElement(By.cssSelector("label.star.star-5"));
		selectRate.click();

		WebElement title = form.findElement(By.cssSelector("#title"));
		title.clear();
		title.sendKeys(titleReview);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement textarea = form.findElement(By.cssSelector("#mytextarea"));
		int x = textarea.getLocation().getX();
		int y = textarea.getLocation().getY() + 400;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");

		driver.switchTo().frame(0);
		WebElement content = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(content).perform();
		driver.findElement(By.xpath("/html/body")).sendKeys(contentReview);

		driver.switchTo().defaultContent();
		WebElement form2 = driver.findElement(By.cssSelector(".contact-form"));
		WebElement btnSubmit = form2.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement modal = driver.findElement(By.cssSelector(".swal-modal"));
		WebElement btnOk = modal.findElement(By.cssSelector(".swal-button--confirm"));
		btnOk.click();

		WebElement expected = driver.findElement(By.cssSelector(".review-title"));

		try {
			assertEquals(expected.getText(), expectedReview);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}

	}

	// Review book successfully with "Title" - xss injection
//	@Test(dataProvider = "dataTC49", priority = 5)
	public void testTC49(String url, String titleReview, String contentReview, String expectedReview) {
		clickAddReview(url);
		WebElement form = driver.findElement(By.cssSelector(".contact-form"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		wait("label.star.star-5", 3000);
		WebElement selectRate = form.findElement(By.cssSelector("label.star.star-5"));
		selectRate.click();

		WebElement title = form.findElement(By.cssSelector("#title"));
		title.clear();
		title.sendKeys(titleReview);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement textarea = form.findElement(By.cssSelector("#mytextarea"));
		int x = textarea.getLocation().getX();
		int y = textarea.getLocation().getY() + 400;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");

		driver.switchTo().frame(0);
		WebElement content = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(content).perform();
		driver.findElement(By.xpath("/html/body")).sendKeys(contentReview);

		driver.switchTo().defaultContent();
		WebElement form2 = driver.findElement(By.cssSelector(".contact-form"));
		WebElement btnSubmit = form2.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement modal = driver.findElement(By.cssSelector(".swal-modal"));
		WebElement btnOk = modal.findElement(By.cssSelector(".swal-button--confirm"));
		btnOk.click();

		WebElement expected = driver.findElement(By.cssSelector(".review-title"));

		try {
			assertEquals(expected.getText(), expectedReview);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}

	}

	// Review book successfully with "Title" contain special character
//	@Test(dataProvider = "dataTC50", priority = 6)
	public void testTC50(String url, String titleReview, String contentReview, String expectedReview) {
		clickAddReview(url);
		WebElement form = driver.findElement(By.cssSelector(".contact-form"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		wait("label.star.star-5", 3000);
		WebElement selectRate = form.findElement(By.cssSelector("label.star.star-5"));
		selectRate.click();

		WebElement title = form.findElement(By.cssSelector("#title"));
		title.clear();
		title.sendKeys(titleReview);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement textarea = form.findElement(By.cssSelector("#mytextarea"));
		int x = textarea.getLocation().getX();
		int y = textarea.getLocation().getY() + 400;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");

		driver.switchTo().frame(0);
		WebElement content = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(content).perform();
		driver.findElement(By.xpath("/html/body")).sendKeys(contentReview);

		driver.switchTo().defaultContent();
		WebElement form2 = driver.findElement(By.cssSelector(".contact-form"));
		WebElement btnSubmit = form2.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement modal = driver.findElement(By.cssSelector(".swal-modal"));
		WebElement btnOk = modal.findElement(By.cssSelector(".swal-button--confirm"));
		btnOk.click();

		WebElement expected = driver.findElement(By.cssSelector(".review-title"));

		try {
			assertEquals(expected.getText(), expectedReview);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}

	}

	// Review book successfully with "Title" = 100 chars= maxlenght
//	@Test(dataProvider = "dataTC51", priority = 7)
	public void testTC51(String url, String titleReview, String contentReview, String expectedReview) {
		clickAddReview(url);
		WebElement form = driver.findElement(By.cssSelector(".contact-form"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		wait("label.star.star-5", 3000);
		WebElement selectRate = form.findElement(By.cssSelector("label.star.star-5"));
		selectRate.click();

		WebElement title = form.findElement(By.cssSelector("#title"));
		title.clear();
		title.sendKeys(titleReview);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement textarea = form.findElement(By.cssSelector("#mytextarea"));
		int x = textarea.getLocation().getX();
		int y = textarea.getLocation().getY() + 400;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");

		driver.switchTo().frame(0);
		WebElement content = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(content).perform();
		driver.findElement(By.xpath("/html/body")).sendKeys(contentReview);

		driver.switchTo().defaultContent();
		WebElement form2 = driver.findElement(By.cssSelector(".contact-form"));
		WebElement btnSubmit = form2.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement modal = driver.findElement(By.cssSelector(".swal-modal"));
		WebElement btnOk = modal.findElement(By.cssSelector(".swal-button--confirm"));
		btnOk.click();

		WebElement expected = driver.findElement(By.cssSelector(".review-title"));

		try {
			assertEquals(expected.getText(), expectedReview);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}
	}

	// Review book successfully with content has Image
	// @Test(dataProvider = "dataTC52", priority = 8)
	public void testTC52(String url, String titleReview, String contentReview) {
		clickAddReview(url);
		WebElement form = driver.findElement(By.cssSelector(".contact-form"));
		wait("label.star.star-5", 3000);
		WebElement selectRate = form.findElement(By.cssSelector("label.star.star-5"));
		selectRate.click();

		WebElement title = form.findElement(By.cssSelector("#title"));
		title.clear();
		title.sendKeys(titleReview);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement textarea = form.findElement(By.cssSelector("#mytextarea"));
		int x = textarea.getLocation().getX();
		int y = textarea.getLocation().getY() + 400;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");

		driver.switchTo().frame(0);
		WebElement content = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(content).perform();
		driver.findElement(By.xpath("/html/body")).sendKeys(contentReview);
		driver.switchTo().defaultContent();
		WebElement btn = driver.findElement(By.cssSelector("#cke_40"));
		btn.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement formUpload = driver.findElement(By.cssSelector(".cke_dialog_body"));
		WebElement urlImage = formUpload.findElement(By.cssSelector("#cke_88_textInput"));
		urlImage.sendKeys("https://5.imimg.com/data5/AB/RM/HJ/SELLER-96982249/button-rose-500x500.jpg");
		WebElement btnOK = driver.findElement(By.cssSelector(".cke_dialog_ui_button_ok"));
		btnOK.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement form2 = driver.findElement(By.cssSelector(".contact-form"));
		WebElement btnSubmit = form2.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement modal = driver.findElement(By.cssSelector(".swal-modal"));
		WebElement btnOk = modal.findElement(By.cssSelector(".swal-button--confirm"));
		btnOk.click();

		WebElement expected = driver.findElement(By.cssSelector(".review-title"));

		try {
			assertEquals(expected.getText(), titleReview);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}

	}

	// Review book unsuccessfully with content = blank
//	@Test(dataProvider = "dataTC54", priority = 9)
	public void testTC54(String url, String titleReview, String contentReview, String expectedReview) {
		clickAddReview(url);
		WebElement form = driver.findElement(By.cssSelector(".contact-form"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		wait("label.star.star-5", 3000);
		WebElement selectRate = form.findElement(By.cssSelector("label.star.star-5"));
		selectRate.click();

		WebElement title = form.findElement(By.cssSelector("#title"));
		title.clear();
		title.sendKeys(titleReview);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement textarea = form.findElement(By.cssSelector("#mytextarea"));
		int x = textarea.getLocation().getX();
		int y = textarea.getLocation().getY() + 400;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");

		WebElement form2 = driver.findElement(By.cssSelector(".contact-form"));
		WebElement btnSubmit = form2.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement error = driver.findElement(By.cssSelector(".alert-danger"));

		try {
			assertEquals(error.getText(), expectedReview);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}
	}

	// Review book unsuccessfully with "Title" < minlenght
//	@Test(dataProvider = "dataTC55", priority = 10)
	public void testTC55(String url, String titleReview, String contentReview, String expectedReview) {
		clickAddReview(url);
		WebElement form = driver.findElement(By.cssSelector(".contact-form"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		wait("label.star.star-5", 3000);
		WebElement selectRate = form.findElement(By.cssSelector("label.star.star-5"));
		selectRate.click();

		WebElement title = form.findElement(By.cssSelector("#title"));
		title.clear();
		title.sendKeys(titleReview);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement textarea = form.findElement(By.cssSelector("#mytextarea"));
		int x = textarea.getLocation().getX();
		int y = textarea.getLocation().getY() + 400;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");

		driver.switchTo().frame(0);
		WebElement content = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(content).perform();
		driver.findElement(By.xpath("/html/body")).sendKeys(contentReview);

		driver.switchTo().defaultContent();
		WebElement form2 = driver.findElement(By.cssSelector(".contact-form"));
		WebElement btnSubmit = form2.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement error = driver.findElement(By.cssSelector(".alert-danger"));

		try {
			assertEquals(error.getText(), expectedReview);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}
	}

	// Review book unsuccessfully with "Title" > maxlenght
//	@Test(dataProvider = "dataTC56", priority = 11)
	public void testTC56(String url, String titleReview, String contentReview, String expectedReview) {
		clickAddReview(url);
		WebElement form = driver.findElement(By.cssSelector(".contact-form"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		wait("label.star.star-5", 3000);
		WebElement selectRate = form.findElement(By.cssSelector("label.star.star-5"));
		selectRate.click();

		WebElement title = form.findElement(By.cssSelector("#title"));
		title.clear();
		title.sendKeys(titleReview);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement textarea = form.findElement(By.cssSelector("#mytextarea"));
		int x = textarea.getLocation().getX();
		int y = textarea.getLocation().getY() + 400;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");

		driver.switchTo().frame(0);
		WebElement content = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(content).perform();
		driver.findElement(By.xpath("/html/body")).sendKeys(contentReview);

		driver.switchTo().defaultContent();
		WebElement form2 = driver.findElement(By.cssSelector(".contact-form"));
		WebElement btnSubmit = form2.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement error = driver.findElement(By.cssSelector(".alert-danger"));

		try {
			assertEquals(error.getText(), expectedReview);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}
	}

	// Review book unsuccessfully with "Rate"= null
//	@Test(dataProvider = "dataTC57", priority = 12)
	public void testTC57(String url, String titleReview, String contentReview, String expectedReview) {
		clickAddReview(url);
		WebElement form = driver.findElement(By.cssSelector(".contact-form"));

		WebElement title = form.findElement(By.cssSelector("#title"));
		title.clear();
		title.sendKeys(titleReview);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement textarea = form.findElement(By.cssSelector("#mytextarea"));
		int x = textarea.getLocation().getX();
		int y = textarea.getLocation().getY() + 400;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");

		driver.switchTo().frame(0);
		WebElement content = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(content).perform();
		driver.findElement(By.xpath("/html/body")).sendKeys(contentReview);

		driver.switchTo().defaultContent();
		WebElement form2 = driver.findElement(By.cssSelector(".contact-form"));
		WebElement btnSubmit = form2.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement error = driver.findElement(By.cssSelector(".alert-danger"));

		try {
			assertEquals(error.getText(), expectedReview);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}
	}

	// Delete review successfully
//	@Test(dataProvider = "dataTC58",priority= 13)
	public void testTC58(String url) {
		driver.navigate().to(url);
		wait(".event-list", 3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.cssSelector(".event-list"));
		WebElement checkTitle = element.findElement(By.cssSelector(".review-title"));
		int x = checkTitle.getLocation().getX();
		int y = checkTitle.getLocation().getY();
		int y1 = y + 50;
		int y2 = y + 50;
		js.executeScript("window.scrollTo( " + x + ", " + y1 + ")");
		Actions actions = new Actions(driver);
		actions.moveToElement(checkTitle).perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement btnDelete = driver.findElement(By.cssSelector("button.notify"));
		btnDelete.click();

		wait(".swal-modal", 3000);
		WebElement form = driver.findElement(By.cssSelector(".swal-modal"));
		WebElement btnOK = form.findElement(By.cssSelector(".swal-button--danger"));
		btnOK.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Edit Title of review successfully
	@Test(dataProvider = "dataTC59", priority = 2)
	public void testTC59(String url, String titleReview) {
		driver.navigate().to(url);
		wait(".event-list", 3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.cssSelector(".event-list"));
		WebElement checkTitle = element.findElement(By.cssSelector(".review-title"));
		int x = checkTitle.getLocation().getX();
		int y = checkTitle.getLocation().getY();
		int y1 = y + 50;
		int y2 = y + 50;
		js.executeScript("window.scrollTo( " + x + ", " + y1 + ")");
		Actions actions = new Actions(driver);
		actions.moveToElement(checkTitle).perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement btnEdit = driver.findElement(By.cssSelector(
				"a[href='https://stg-api-book.sun-asterisk.vn/books/cho-toi-mau-giao-thi-qua-muon-2225/review/303/edit']"));
		btnEdit.click();

		WebElement form = driver.findElement(By.cssSelector(".contact-form"));
		WebElement title = form.findElement(By.cssSelector("#title"));
		title.clear();
		title.sendKeys(titleReview);

		WebElement form2 = driver.findElement(By.cssSelector(".contact-form"));
		WebElement btnSubmit = form2.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			assertEquals(driver.getCurrentUrl(), url);
		} catch (AssertionError e) {
			// TODO: handle exception
			capture("CaseFail.jpg");
		}

	}

	// Review book unsuccessfully with title = blank
	// @Test(dataProvider = "dataTC53", priority = 15)
	public void testTC53(String url, String titleReview, String contentReview, String expectedReview) {
		clickAddReview(url);
		WebElement form = driver.findElement(By.cssSelector(".contact-form"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		wait("label.star.star-5", 3000);
		WebElement selectRate = form.findElement(By.cssSelector("label.star.star-5"));
		selectRate.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement textarea = form.findElement(By.cssSelector("#mytextarea"));
		int x = textarea.getLocation().getX();
		int y = textarea.getLocation().getY() + 400;
		js.executeScript("window.scrollTo( " + x + ", " + y + ")", "");

		driver.switchTo().frame(0);
		WebElement content = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(content).perform();
		driver.findElement(By.xpath("/html/body")).sendKeys(contentReview);

		driver.switchTo().defaultContent();
		WebElement form2 = driver.findElement(By.cssSelector(".contact-form"));
		WebElement btnSubmit = form2.findElement(By.cssSelector("button[type='submit']"));
		btnSubmit.click();
		String urlCreate = url + "/review/create";
			try {
				assertEquals(driver.getCurrentUrl(),urlCreate);
			} catch (AssertionError e) {
				// TODO: handle exception
				capture("CaseFail.jpg");
			}
	}

	public void clickAddReview(String url) {
		driver.manage().window().maximize();
		driver.navigate().to(url);
		if (driver.findElements(By.cssSelector("a[href='" + url + "/review/create\']")).isEmpty()) {
			System.out.println("You reviewed this book");

		} else {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement btnAddReview = driver.findElement(By.cssSelector("a[href='" + url + "/review/create\']"));
			int x = btnAddReview.getLocation().getX();
			int y = btnAddReview.getLocation().getY() - 100;

			js.executeScript("window.scrollTo( " + x + ", " + y + ")");
			btnAddReview.click();
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
