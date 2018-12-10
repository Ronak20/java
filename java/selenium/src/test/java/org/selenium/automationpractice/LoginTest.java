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
import org.selenium.automationpractice.page.AccountPage;
import org.selenium.automationpractice.page.HomePage;
import org.selenium.automationpractice.page.LoginPage;

public class LoginTest {

	private static WebDriver driver = null;

	@BeforeClass
	public static void setUpBeforeClass() {

	}

	@AfterClass
	public static void setUpAfterClass() {

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
	public void testValidLoginPageObject() {
		driver.get(AutConstants.ROOT_URL);

		HomePage homePage = new HomePage(driver);
		LoginPage loginPage = homePage.clickSignIn();
		loginPage.enterEmail("ronakbchaudhari@gmail.com");
		loginPage.enterPasswd("200190");

		AccountPage accountPage = (AccountPage) loginPage.submitLoginForm();

		String accountBody = accountPage.getBody();

		Assert.assertNotNull(accountBody);
		Assert.assertEquals("My account - My Store", accountPage.getTitle());
	}

}
