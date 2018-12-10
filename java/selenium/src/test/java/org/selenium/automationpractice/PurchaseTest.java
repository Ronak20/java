package org.selenium.automationpractice;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.selenium.AutConstants;
import org.selenium.AutUtil;
import org.selenium.automationpractice.page.AddressPage;
import org.selenium.automationpractice.page.ConfirmOrderPage;
import org.selenium.automationpractice.page.HomePage;
import org.selenium.automationpractice.page.LoginPage;
import org.selenium.automationpractice.page.PaymentPage;
import org.selenium.automationpractice.page.ShippingPage;
import org.selenium.automationpractice.page.SummaryPage;

public class PurchaseTest {

	private static WebDriver driver = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		//driver = AutUtil.getRemoteWebDriver();
		driver = AutUtil.getFirefoxDriver();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testPurchase() throws Exception {
		driver.get(AutConstants.ROOT_URL);

		HomePage homePage = new HomePage(driver);
		homePage.addToCartFirstItem();
		SummaryPage summaryPage = homePage.checkout();
		LoginPage loginPage = summaryPage.summaryCheckout();
		loginPage.enterEmail("ronakbchaudhari@gmail.com");
		loginPage.enterPasswd("200190");
		AddressPage addressPage = (AddressPage) loginPage.submitLoginForm();
		ShippingPage shippingPage = addressPage.clickCheckout();

		shippingPage.clickAgrement();
		PaymentPage paymentPage = shippingPage.clickCheckout();
		ConfirmOrderPage confirmOrderPage = paymentPage.clickBankwire();
		confirmOrderPage.clickConfirmOrder();

		Assert.assertEquals("Your order on My Store is complete.", confirmOrderPage.getConfirmText());
	}

}
