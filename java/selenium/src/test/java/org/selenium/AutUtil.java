package org.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AutUtil {

	private static Properties prop = new Properties();
	private static String USERNAME = null;
	private static String ACCESS_KEY = null;

	static {
		try (InputStream input = new FileInputStream(
				new File(AutUtil.class.getResource("/seleniumtest.properties").toURI()))) {
			// load a properties file
			prop.load(input);

			// get the property value and print it out
			USERNAME = prop.getProperty("saucelab.username");
			ACCESS_KEY = prop.getProperty("saucelab.key");
		} catch (IOException | URISyntaxException ex) {
			ex.printStackTrace();
		}
	}

	private static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

	private static String GECKO_DRIVER = "E://Onedrive/java/java/selenium/src/test/resources/geckodriver-v0.23.0-win64/geckodriver.exe";

	public static WebDriver getFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver", GECKO_DRIVER);
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	/**
	 * Use this method to run tests in saucelab
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	public static WebDriver getRemoteWebDriver() throws MalformedURLException {
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setCapability("platform", "Windows 10");
		caps.setCapability("version", "latest");

		WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
		return driver;
	}

}
