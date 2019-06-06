package com.sussex.pageObjects;

import org.openqa.selenium.WebDriver;

public class HomePage {
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

}



