package com.sussex.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public void refreshPage() {
		// TODO Auto-generated method stub
		driver.navigate().refresh();
	}
	
	//Objects to handle cookies
	By AcceptCookies = By.id("CloseCookieMsg");
	public void acceptCookies() {
		driver.findElement(AcceptCookies).isDisplayed();
		driver.findElement(AcceptCookies).isEnabled();
		driver.findElement(AcceptCookies).click();
	}

	// Object for Login Button On RegistrationPage
	By loginButton = By
			.xpath("//a[@class='primary dataEntryPanelActionButton link dataLink alignInherit buttonStyle']");

	public void loginButton() {
		driver.findElement(loginButton).isDisplayed();
		driver.findElement(loginButton).isEnabled();
		driver.findElement(loginButton).click();
	}

	// Object for Login in LoginPage :- Username,Password,LoginButton
	// Object for Login user

	By username = By.name("LogonName");

	public void enterUserName(String uname) {
		driver.findElement(username).clear();
		driver.findElement(username).sendKeys(uname);
		driver.findElement(username).sendKeys(Keys.TAB);
	}

	// Object for password
	By password1 = By.id("Password");

	public void enterPassword(String password) {
		
		driver.findElement(password1).clear();
		driver.findElement(password1).sendKeys(password);
	}
	
	

	// Object for Login button
	By loginbtn = By.id("BtnLogOn");

	public HomePage clickLoginbutton() {
		
			driver.findElement(loginbtn).isDisplayed();
			driver.findElement(loginbtn).isEnabled();
			driver.findElement(loginbtn).click();
			return new HomePage(driver);
			
	}

	// Objects for Login
	public LoginPage login(String uname, String pass) {
		// TODO Auto-generated method stub
		enterUserName(uname);
		enterPassword(pass);
		clickLoginbutton();
		return new LoginPage(driver);

	}

	// Object for UOS Logo
	By UOSLogo = By.xpath("//a[@class='themedLargeLogo']");

	public WebElement uosLogo() {
		return driver.findElement(UOSLogo);
		}

	// Objects for Log On Details Text
	By YourDetails = By.xpath("//*[@id=\"LogonPanel\"]/div[1]/h2");

	public String verifyYourDetailsText() {
		return driver.findElement(YourDetails).getText();
	}

	// Objects for Terms and Condition on Login Page
	By TermsCondition = By.xpath("//button[@id='lnkTnc']");
	By LogOnPopUp = By.xpath("//div[@id='LogonPopupTitle']");

	public String verifyTermsConditionLink() {
		driver.findElement(TermsCondition).isDisplayed();
		driver.findElement(TermsCondition).isEnabled();
		driver.findElement(TermsCondition).click();
		return driver.findElement(LogOnPopUp).getText();

	}

	// Objects for Close popup window
	By ClosePopUp = By.xpath("//span[@class='icon24 glyph']");

	public void closePopupWindow() {
		driver.findElement(ClosePopUp).click();
	}

	// Objects for CheckBox Keep Me Logged In
	By KeepMeLogOnCheckbox = By.xpath("//*[@id=\"RememberMe\"]/div[1]");

	public void verifyKeepMeLogOnCheckbox() {
		driver.findElement(KeepMeLogOnCheckbox).isDisplayed();
		driver.findElement(KeepMeLogOnCheckbox).isEnabled();
		driver.findElement(KeepMeLogOnCheckbox).isSelected();
	}

	// Objects for Accessibility Mode Checkbox
	By AccessibilityModeCheckbox = By.xpath("//*[@id=\"ChkAccessibility\"]/div[1]");

	public void AccessibilityModeCheckbox() {
		driver.findElement(AccessibilityModeCheckbox).isDisplayed();
		driver.findElement(AccessibilityModeCheckbox).isEnabled();
		driver.findElement(AccessibilityModeCheckbox).isSelected();
	}

	// Object for Forgot Password link on Registration Page
	By ForgotPasswordLink = By.linkText("Forgot password?");

	public void forgotPasswordLinkOnRegPage() {
		driver.findElement(ForgotPasswordLink).isDisplayed();
		driver.findElement(ForgotPasswordLink).isEnabled();
		driver.findElement(ForgotPasswordLink).click();
	}
	// Object for Forgot password Validation text

	By ForgotPassValidationText = By.xpath("(//*[@id=ValidationSummary]/div/ul/li)");

	public String forgotPassValidationText() {
		driver.findElement(ForgotPassValidationText).isDisplayed();
		return driver.findElement(ForgotPassValidationText).getText();
	}
	
	
	
	//objects on log in page

	By LogOnText = By.xpath("//h2[contains(text(),'Log on using your details')]");

	public String getLogOnText(){
	driver.findElement(LogOnText).isDisplayed();
	return driver.findElement(LogOnText).getText();
	}

	// //Objects for Default Check in Box
	 By DefaultChk = By.xpath("//*[@id='RememberMe']/div[1]");

	 public void defaultChk(){
	driver.findElement(DefaultChk).isDisplayed();
	driver.findElement(DefaultChk);
	
	 }
	 
	//Objects for Optional Check boxes appearing in Log in page

	 By OptionalChk = By.xpath("//*[@id='RememberMe']/div[1]");

	 public void optionalChk(){
	driver.findElement(OptionalChk).isDisplayed();
	driver.findElement(OptionalChk);
	 }
	 
	 //Objects for Link terms & Condition

	 By LinkTermsCondition = By.partialLinkText("View Terms");

	 public String verifyLink(){
	  driver.findElement(LinkTermsCondition).isDisplayed();
	  driver.findElement(LinkTermsCondition).isEnabled();
	  //driver.findElement(LinkTermsCondition).click();
return driver.findElement(LinkTermsCondition).getText();

	 }


}
