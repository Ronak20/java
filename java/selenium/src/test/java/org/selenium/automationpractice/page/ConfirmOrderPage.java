package org.selenium.automationpractice.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmOrderPage extends Page{
	
	@FindBy(id="cart_navigation")
	private WebElement cartNavigation;
	
	@FindBy(className="cheque-indent")
	private WebElement confirmText;

	public ConfirmOrderPage(WebDriver driver) {
		super(driver);
	}
	
	public void clickConfirmOrder(){
		WebElement confirmBtn = cartNavigation.findElement(By.tagName("button"));
		confirmBtn.click();
	}
	
	public String getConfirmText(){
		return confirmText.findElement(By.tagName("strong")).getText();
	}

}
