package org.selenium.automationpractice.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShippingPage extends Page {
	
	@FindBy(name="cgv")
	private WebElement agreeCB;
	
	@FindBy(name="processCarrier")
	private WebElement checkoutBtn;

	public ShippingPage(WebDriver driver) {
		super(driver);
	}
	
	public void clickAgrement(){
		agreeCB.click();
	}
	
	public PaymentPage clickCheckout(){
		checkoutBtn.click();
		return new PaymentPage(driver);
	}

}
