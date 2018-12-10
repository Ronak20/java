package org.selenium.automationpractice.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressPage extends Page {

	@FindBy(name = "processAddress")
	private WebElement checkoutBtn;

	public AddressPage(WebDriver driver) {
		super(driver);
	}

	public ShippingPage clickCheckout() {
		checkoutBtn.click();
		return new ShippingPage(driver);
	}

}
