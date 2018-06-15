package locators;

import org.openqa.selenium.By;

public class Locators {

	public static By google_signInButton = By.xpath("//a[text() = 'Sign in']");
	public static By google_emailOrPhoneInput = By.id("identifierId");
	public static By google_nextButtonSignInForm = By.id("identifierNext");
	public static By google_PasswordInput = By.name("password");
	public static By google_PasswordNextSignInForm = By.id("passwordNext");
	public static By google_appsIconbutton = By.xpath("//div[@id='gbwa']/div[1]/a");
	public static By google_appsIconGmail = By.id("gb23");
	public static By google_gmailLink = By.xpath("//a[text()='Gmail']");
	
	//inside gmail inbox
	public static By google_gmailInboxTBody = By.xpath("//div[@class= 'Cp']/div/table/tbody");
	public static By virtruUnlockMessage = By.xpath("//tbody/tr/td/a/img");
	public static By virtruListedEmailLink = google_gmailInboxTBody.xpath("//div[contains(@class, 'login-page')]/a[1]");
	
	
}
