package org.selenium.automationpractice.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends Page {

	@FindBy(id = "my-account")
	private WebElement accountBody;

	public AccountPage(WebDriver driver) {
		super(driver);

		if (driver == null || !driver.getTitle().equals("My account - My Store")) {
			throw new IllegalArgumentException("This is not account page. Current location is " + driver.getCurrentUrl());
		}
	}

	public String getBody() {
		return accountBody.getText();
	}

	public String getTitle() {
		return driver.getTitle();
	}

}
