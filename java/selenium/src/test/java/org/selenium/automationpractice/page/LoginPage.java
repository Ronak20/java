package org.selenium.automationpractice.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page {

	@FindBy(id = "email")
	private WebElement emailField;

	@FindBy(id = "passwd")
	private WebElement passwordField;

	@FindBy(id = "SubmitLogin")
	private WebElement submitBtn;

	public LoginPage(WebDriver driver) {
		super(driver);
		if (driver == null || !driver.getTitle().equals("Login - My Store")) {
			throw new IllegalArgumentException("This is not login page. Current location is " + driver.getCurrentUrl());
		}
	}

	public void enterEmail(String email) {
		emailField.sendKeys(email);
	}

	public void enterPasswd(String password) {
		passwordField.sendKeys(password);
	}

	public Page submitLoginForm() {
		submitBtn.click();
		
		if(driver.getCurrentUrl().contains("step=1")){
			return new AddressPage(driver);
		}else{
			return new AccountPage(driver);
		}
	}

}