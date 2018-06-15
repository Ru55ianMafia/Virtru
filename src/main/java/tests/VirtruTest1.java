package tests;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import virtru.BasePage;
import locators.Locators;

public class VirtruTest1 extends BasePage{

	public VirtruTest1(WebDriver driver) {
		super(driver);
	}
	
	// import the locators
	static Locators locator = new Locators();
	
	public static void main(String[] args) {
		
		//start test
		startTestInChrome("https://www.google.com");		
		//actual test
		runVirtruTest();
		//teardown
		quiteChrome();
	}


	@Test
	public static void runVirtruTest() {
		
		//sign in button
		explicitWaitForElement(locator.google_signInButton, 10);
		clickButton(locator.google_signInButton);
		
		//signin form
		/*
		 * This is dummy data. In real world scenario, I would use encryption/decryption
		 * methods to decipher password. Never use plaintext credentials. For demo
		 * purposes only
		 */
		loginFormGmailAction("virtruvlad", "pass12345!", locator.google_emailOrPhoneInput, locator.google_PasswordInput, locator.google_nextButtonSignInForm, locator.google_PasswordNextSignInForm);
		
		//signed in main page
		explicitWaitForElement(locator.google_gmailLink, 10);
		clickButton(locator.google_gmailLink);		
		
		//select email
		clickEmailBySubject("virtru");
		
		//virtru feature
		clickButton(locator.virtruUnlockMessage);
		// verify message
		if(verifyVirtruEmail("Hello! My name is Vladimir and I would love to work for Virtru!")) {
			System.out.println("Successfully Decrypted message!");
		} 
		
		// wait for a few seconds to visually process
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}
	
	
	
}