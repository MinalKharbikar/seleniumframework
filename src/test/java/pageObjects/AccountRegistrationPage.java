package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage (WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath ="//input [@id='input-firstname']")
	WebElement txtfirsname;
	
	@FindBy(xpath ="//input [@id='input-lastname']")
	WebElement txtlastname;
	
	@FindBy(xpath ="//input [@id='input-email']")
	WebElement txtemail;
	
	@FindBy(xpath ="//input[@id='input-telephone']")
	WebElement txttelephone;
	
	@FindBy(xpath ="//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath ="//input[@id='input-confirm']")
	WebElement txtconfirmpassword;
	
	@FindBy(xpath ="//input[@name='agree']")
	WebElement checkdpolicy;
	
	@FindBy(xpath ="//input[@value='Continue']")
	WebElement btncontinue;
	
	@FindBy(xpath ="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement cofirmationmsg;
	
	
	public void setFirstName(String fname ) 
	{
		txtfirsname.sendKeys(fname);
	}
	
	
	public void setLastName(String lname ) 
	{
		txtlastname.sendKeys(lname);
	}
	
	public void setEmail(String mail ) 
	{
		txtemail.sendKeys(mail);
	}
	
	public void setTelephone(String txttel ) 
	{
		txttelephone.sendKeys(txttel);
	}
	
	
	
	public void setPassword(String pwd ) 
	{
		txtpassword.sendKeys(pwd);
	}
	
	public void setconfirmPassword(String confirmpwd ) 
	{
		txtconfirmpassword.sendKeys(confirmpwd);
	}
	
	public void setPrivacyPolicy( ) 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", checkdpolicy); 
		checkdpolicy.click();
	}
	
	public void clickContinue( ) 
	{
		btncontinue.click();;
	}
	
	public String getConfirmationMsg()
	{
	try {
		return(cofirmationmsg.getText());
		
	} catch (Exception e) {
		// TODO: handle exception
		return (e.getMessage());
	}	
		
}
	
	
}
