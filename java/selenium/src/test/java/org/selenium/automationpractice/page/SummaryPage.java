package org.selenium.automationpractice.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SummaryPage extends Page {

	@FindBy(className = "standard-checkout")
	private WebElement summaryCheckoutLink;

	public SummaryPage(WebDriver driver) {
		super(driver);
		if (driver == null || !driver.getTitle().equals("Order - My Store")) {
			throw new IllegalArgumentException("This is not summary page. Current location is " + driver.getCurrentUrl());
		}
	}

	public LoginPage summaryCheckout() {
		summaryCheckoutLink.click();
		return new LoginPage(driver);
	}
}
