package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001AccountRegistrationPage extends BaseClass{
	
	@Test (groups = "Regression")
	public void verify_account_Registration()
	{
		logger.info("*****Starting TC_001AccountRegistrationPage Test***** ");
		try 
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount link");

		hp.clickRegister();
		logger.info("Clicked on Register link");

		
		AccountRegistrationPage  regpage = new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details.....");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelephone(randomNumber());
		
		//String password = RandomAlphanumeric()
		String  Password = randomAlphaNumeric();
		regpage.setPassword(Password);
		regpage.setconfirmPassword(Password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating expected message");
		String confmsg = regpage.getConfirmationMsg();
		
		if (confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test failed....");
			logger.debug("Debug logs....");
			Assert.assertTrue(false); 
		}
	//	Assert.assertEquals(confmsg, "Your Account Has Been Created!");
	}
	 catch (Exception e) 
	{
		 
		 Assert.fail();
	 }
		logger.info("****Finished TC_001AccountRegistrationPage Test*****");
	
	
	}
	


}
