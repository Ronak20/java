package org.selenium.byexample;

import java.io.File;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.selenium.AutUtil;

public class DesiredCapabilitiesExample {

	private static WebDriver driver;

	@BeforeClass
	public static void setupOnce() {
		driver = AutUtil.getFirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://the-internet.herokuapp.com");
	}

	@AfterClass
	public static void tearDownOnce() {
		// driver.quit();
	}

	/*@Test
	public void testScreenshot() {
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getAbsolutePath());
	}

	@Test
	public void testMultipleWindows() {
		driver.get("http://the-internet.herokuapp.com/windows");
		String handle1 = driver.getWindowHandle();
		
		WebElement linkWe = driver.findElement(By.linkText("Click Here"));
		linkWe.click();
		
		String handle2 = driver.getWindowHandle();
		
		driver.switchTo().window(handle1);
	}*/
	
	/*@Test
	public void testFrame() {
		driver.get("http://the-internet.herokuapp.com/nested_frames");
		
		driver.switchTo().frame(1);
		WebElement tagWe = driver.findElement(By.tagName("body"));
		String tagText1 = tagWe.getText();
		System.out.println(tagText1);
		
		driver.switchTo().defaultContent();
		
		WebElement topFrameWe = driver.findElement(By.name("frame-top"));
		driver.switchTo().frame(topFrameWe);
		
		WebElement leftFrameWe = driver.findElement(By.name("frame-left"));
		driver.switchTo().frame(leftFrameWe);
		WebElement tag0We = driver.findElement(By.tagName("body"));
		String tagText0 = tag0We.getText();
		System.out.println(tagText0);
		
	}*/
	
	/*@Test
	public void testAlert() {
		driver.get("http://the-internet.herokuapp.com/javascript_alerts");
		
		List<WebElement> elements = driver.findElements(By.tagName("button"));
		
		WebElement alertBtn = elements.get(0);
		WebElement confirmBtn = elements.get(1);
		WebElement promptBtn = elements.get(2);
		
		WebElement resultPara = driver.findElement(By.id("result"));
		
		alertBtn.click();
		
		driver.switchTo().alert().accept();
		
		Assert.assertEquals("You successfuly clicked an alert", resultPara.getText());
		
		confirmBtn.click();
		
		driver.switchTo().alert().accept();
		
		Assert.assertEquals("You clicked: Ok", resultPara.getText());
		
		confirmBtn.click();
		
		driver.switchTo().alert().dismiss();
		
		Assert.assertEquals("You clicked: Cancel", resultPara.getText());
		
		promptBtn.click();
		
		Alert prompt = driver.switchTo().alert();
		prompt.sendKeys("test");
		prompt.accept();
		
		Assert.assertEquals("You entered: test", resultPara.getText());
		
		promptBtn.click();
		Alert prompt2 = driver.switchTo().alert();
		prompt.dismiss();
		
		Assert.assertEquals("You entered: null", resultPara.getText());
	}*/
	
	@Test
	public void testNavigate() {
		driver.get("http://the-internet.herokuapp.com/");
		
		WebElement link = driver.findElement(By.linkText("A/B Testing"));
		link.click();
		
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		
	}

}
