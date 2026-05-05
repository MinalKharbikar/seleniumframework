package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmailAddress;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnLogin;
	
	public void setEmail (String email) {
		txtEmailAddress.sendKeys(email);
	}
	
	public void setpassword (String pawd) {
		txtPassword.sendKeys(pawd);
	}
	
	public void clickLogin () {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
	}
	
	
	
	
	

}
