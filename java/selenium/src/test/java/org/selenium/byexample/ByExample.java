package org.selenium.byexample;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.selenium.AutUtil;

public class ByExample {

	private static WebDriver driver = null;

	@BeforeClass
	public static void setupOnce() {
		driver = AutUtil.getFirefoxDriver();
		driver.get("http://the-internet.herokuapp.com/login");
	}

	@AfterClass
	public static void tearDownOnce() {
		// driver.quit();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testById() {
		WebElement usernameWe = driver.findElement(By.id("username"));
		usernameWe.sendKeys("tomsmith");
	}

	@Test
	public void testByName() {
		WebElement passwordWe = driver.findElement(By.name("password"));
		passwordWe.sendKeys("SuperSecretPassword!");
	}

	@Test
	public void testByTagName() {
		WebElement btnWe = driver.findElement(By.tagName("button"));
		Assert.assertEquals("Login", btnWe.getText());
	}

	@Test
	public void testByCssClass() {
		WebElement btnWe = driver.findElement(By.className("radius"));
		Assert.assertEquals("Login", btnWe.getText());
	}

	@Test
	public void testByLinkText() {
		WebElement linkWe = driver.findElement(By.linkText("Elemental Selenium"));
		Assert.assertEquals("http://elementalselenium.com/", linkWe.getAttribute("href"));
	}

	@Test
	public void testPartialLinkTest() {
		WebElement partialLinkWe = driver.findElement(By.partialLinkText("Elemental"));
		Assert.assertEquals("http://elementalselenium.com/", partialLinkWe.getAttribute("href"));
	}

	@Test
	public void testXPath() {
		WebElement xpathWe = driver.findElement(By.xpath("//*[@id=\"login\"]/div[1]/div/label"));
		Assert.assertEquals("Username", xpathWe.getText());
	}

	@Test
	public void testCssSelector() {
		WebElement xpathWe = driver.findElement(By.cssSelector("#login > div:nth-child(1) > div > label"));
		Assert.assertEquals("Username", xpathWe.getText());
	}

	@Test
	public void testClear() {
		WebElement usernameWe = driver.findElement(By.id("username"));
		usernameWe.clear();
		usernameWe.sendKeys("tomsmith");
		WebElement passwordWe = driver.findElement(By.name("password"));
		passwordWe.clear();
		passwordWe.sendKeys("SuperSecretPassword!");
	}

	@Test
	public void testSubmit() {
		WebElement loginFormWe = driver.findElement(By.id("login"));
		loginFormWe.submit();
	}

	@Test
	public void testCssValue() {
		WebElement btnWe = driver.findElement(By.tagName("button"));
		Assert.assertEquals("rgb(255, 255, 255)", btnWe.getCssValue("color"));
	}

	@Test
	public void testGetLocation() {
		WebElement btnWe = driver.findElement(By.tagName("button"));
		Point point = btnWe.getLocation();
		System.out.println(point);
	}

	@Test
	public void testSize() {
		WebElement btnWe = driver.findElement(By.tagName("button"));
		Dimension dimension = btnWe.getSize();
		System.out.println(dimension);
	}

	@Test
	public void testGetTagName() {
		WebElement loginFormWe = driver.findElement(By.id("login"));
		Assert.assertEquals("form", loginFormWe.getTagName());
	}

	@Test
	public void testIsDisplayed() {
		WebElement loginFormWe = driver.findElement(By.id("login"));
		Assert.assertEquals(Boolean.TRUE, loginFormWe.isDisplayed());
	}

	@Test
	public void testIsEnabled() {
		WebElement loginFormWe = driver.findElement(By.id("login"));
		Assert.assertEquals(Boolean.TRUE, loginFormWe.isEnabled());
	}

	// TODO
	@Test
	public void testIsSelected() {

	}
}
