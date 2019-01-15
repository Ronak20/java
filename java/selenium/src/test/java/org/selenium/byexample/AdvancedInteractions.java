package org.selenium.byexample;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.selenium.AutUtil;

public class AdvancedInteractions {

	private static WebDriver driver = null;

	@BeforeClass
	public static void setupOnce() {
		driver = AutUtil.getFirefoxDriver();
		//driver.get("http://the-internet.herokuapp.com/login");
		driver.manage().window().maximize();
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

	/*@Test
	public void testActionBuilder() {
		Actions actions = new Actions(driver);
		WebElement usernameWe = driver.findElement(By.id("username"));
		WebElement passwordWe = driver.findElement(By.name("password"));
		WebElement btnWe = driver.findElement(By.tagName("button"));

		actions.sendKeys(usernameWe, "tomsmith").sendKeys(passwordWe, "SuperSecretPassword!").click(btnWe).perform();

		WebElement logoutWe = driver.findElement(By.linkText("Logout"));
		logoutWe.click();
	}*/

	/*@Test
	public void testMoveByOffset() throws InterruptedException {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");
		WebElement btnWe = driver.findElement(By.id("column-a"));
		Point point = btnWe.getLocation();
		Actions actions = new Actions(driver);
		System.out.println(point.getX() + 50);
		System.out.println(point.getY() + 50);
		System.out.println(point.getX() + 50 + 250);
		System.out.println(point.getY() + 100 + 100);
		actions.doubleClick(btnWe).moveByOffset(200, 0).release().perform();
	}*/
	
	/*@Test
	public void testMoveToElement() {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");
		WebElement btnAWe = driver.findElement(By.id("column-a"));
		WebElement btnBWe = driver.findElement(By.id("column-b"));
		
		Actions actions = new Actions(driver);
		actions.clickAndHold(btnAWe).moveToElement(btnBWe).release(btnBWe).perform();
	}*/
	
	/*@Test
	public void testDragAndDrop() {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");
		WebElement btnAWe = driver.findElement(By.id("column-a"));
		WebElement btnBWe = driver.findElement(By.id("column-b"));
		
		Actions actions = new Actions(driver);
		actions.dragAndDrop(btnAWe, btnBWe).perform();
	}*/
	
	@Test
	public void testKeyUpAndDown() {
		driver.get("http://the-internet.herokuapp.com/key_presses");
		
		Actions actions = new Actions(driver);
		actions.sendKeys("A").perform();

		WebElement pWe = driver.findElement(By.id("result"));
		Assert.assertEquals("You entered: A", pWe.getText());
	}
}
