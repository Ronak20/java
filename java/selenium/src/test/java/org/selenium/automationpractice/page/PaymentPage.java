package org.selenium.automationpractice.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends Page {

	@FindBy(className = "bankwire")
	private WebElement bankWireBtn;

	public PaymentPage(WebDriver driver) {
		super(driver);
	}
	
	public ConfirmOrderPage clickBankwire(){
		bankWireBtn.click();
		return new ConfirmOrderPage(driver);
	}

}
