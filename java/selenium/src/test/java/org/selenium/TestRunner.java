package org.selenium;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.selenium.byexample.ByExample;

public class TestRunner {

	public static void main(String[] args) {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		junit.run(ByExample.class);
	}

}
