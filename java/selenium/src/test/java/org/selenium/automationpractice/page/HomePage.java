package org.selenium.automationpractice.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends Page {

	@FindBy(className = "login")
	private WebElement signInLink;

	@FindBy(linkText = "Proceed to checkout")
	private WebElement checkoutLink;
	
	@FindBy(id="homefeatured")
	private WebElement featuredDiv;

	public HomePage(WebDriver driver) {
		super(driver);
		if (driver == null || !driver.getTitle().equals("My Store")) {
			throw new IllegalArgumentException("This is not home page. Current location is " + driver.getCurrentUrl());
		}
	}

	public LoginPage clickSignIn() {
		signInLink.click();
		return new LoginPage(driver);
	}

	public void addToCartFirstItem() {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		WebElement firstItem = featuredDiv.findElements(By.tagName("li")).get(0);
		executor.executeScript("arguments[0].scrollIntoView();", firstItem);
		
		Actions action = new Actions(driver);
		action.moveToElement(firstItem).build().perform();
		
		WebElement firstItemAddToCartBtn = driver.findElements(By.className("ajax_add_to_cart_button")).get(0);
		executor.executeScript("arguments[0].click();", firstItemAddToCartBtn);
	}

	public SummaryPage checkout() {
		new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(checkoutLink));
		checkoutLink.click();
		return new SummaryPage(driver);
	}

}
