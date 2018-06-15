package virtru;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import locators.Locators;

public class BasePage {

	public static WebDriver driver;

	// constructor
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	Locators locator = new Locators();

	public static void startTestInChrome(String URL) {

		System.out.println("Initializing Chrome...");
		// initialize chrome
		driver = new ChromeDriver();
		try {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
			System.out.println("Navigating to... " + URL);
			driver.get(URL);
			driver.manage().window().maximize();
		} catch (Exception e) {

			System.out.println("Unable to find and start chromedriver...");
			e.printStackTrace();
		}

	}
	
	public static void quiteChrome() {
		System.out.println("Closing ChromeDriver...");
		driver.close();
		driver.quit();
	}

	// explicit wait
	public static void explicitWaitForElement(By by, int time) {

		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public static void clickButton(By by) {

		driver.findElement(by).click();
	}

	public static String getTextOfElement(By by) {

		return driver.findElement(by).getText();
	}

	public static void enterText(By by, String text) {

		driver.findElement(by).sendKeys(text);
	}

	
	
	// *************************** SPECIFIC TASKS ********************************** \\
	
	public static void loginFormGmailAction(String email, String password, By email_locator, By password_locator, By nextEmail, By nextPassword) {

		System.out.println("Attempting to sign into gmail");
		try {
			// sign in form
			explicitWaitForElement(email_locator, 10);
			enterText(email_locator, email);
			// next button
			clickButton(nextEmail);
			/*
			 * This is dummy data. In real world scenario, I would use encryption/decryption
			 * methods to decipher password. Never use plaintext credentials. For demo
			 * purposes only
			 */
			explicitWaitForElement(password_locator, 10);
			enterText(password_locator, password);
			clickButton(nextPassword);
			System.out.println("Successfully signed in as " + email);
			
		} catch (Exception e) {
			System.out.println("Could not sign into gmail... Check stacktrace.");
			e.printStackTrace();
		}
	}

	public static int countRowsInTBodyInbox() {

		List<WebElement> rows = driver.findElements(By.xpath("//div[@class= 'Cp']/div/table/tbody/tr"));
		System.out.println("Found " + rows.size() + " rows in table.");
		return rows.size();
	}

	
	public static void clickEmailBySubject(String subject) {

		System.out.println("Inside Gmail Inbox...");
		// get rows in inbox
		int emailRows = countRowsInTBodyInbox();
		System.out.println("Found " + emailRows + " emails...");

		// iterate rows for subject
		for (int i = 1; i <= emailRows; i++) {
			try {
				if (getTextOfElement(By.xpath("//div[@class= 'Cp']/div/table/tbody/tr[" + i + "]/td[6]//span[1]")).equals(subject)) {
					//((JavascriptExecutor) driver).executeScript("arguments[0].click();", By.xpath("//div[@class= 'Cp']/div/table/tbody/tr[" + i + "]"));
					driver.findElement(By.xpath("//div[@class= 'Cp']/div/table/tbody/tr[" + i + "]")).click();
				}
			} catch (Exception e) {
				System.out.println("Could not find email with subject: " + subject);
				e.printStackTrace();
			}
		}

	}
	
	public static boolean verifyVirtruEmail(String email){
		
		String parentWindowHandler=driver.getWindowHandle();// Store your parent window
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles(); // get all window handles
		            Iterator<String> iterator = handles.iterator();
		            while (iterator.hasNext()){
		                subWindowHandler = iterator.next();
		            }
		driver.switchTo().window(subWindowHandler); // switch to popup window
		  
		System.out.println("Navigated to external virtru link...");
		explicitWaitForElement(By.xpath("//div[contains(@class, 'login-page')]/a[1]/div"), 20);
		clickButton(By.xpath("//div[contains(@class, 'login-page')]/a[1]"));
		explicitWaitForElement(By.xpath("//div[contains(@class, 'auth-choice-button-container')]/a[1]"), 15);
		clickButton(By.xpath("//div[contains(@class, 'auth-choice-button-container')]/a[1]"));
		
		//choose gmail account
		if(driver.findElement(By.xpath("//div/ul[1]/li[1]")).isDisplayed()) {
			explicitWaitForElement(By.xpath("//div/ul[1]/li[1]"), 10);
			clickButton(By.xpath("//div/ul[1]/li[1]"));			
		}
		//explicitWaitForElement(By.xpath("//div/ul[1]/li[1]"), 10);
		//clickButton(By.xpath("//div/ul[1]/li[1]"));
		
		// wait for email
		explicitWaitForElement(By.xpath("//span[@id='tdf-body']/div"), 30);
		if(!getTextOfElement(By.xpath("//span[@id='tdf-body']/div")).contains(email)) {
			return false;
			
		} else {
			return true;
		}
	}
	
	
	

}
