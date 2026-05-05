package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002LoginTest extends BaseClass {
	
	
	@Test (groups={"Sanity", "Master"})
	public void verify_login()
	{
		logger.info("****Starting TC_002LoginTest*****");
		
		try {
			
		
		//Home Page
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login Page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setpassword(p.getProperty("password"));
		lp.clickLogin();
		
		//My account Page
		MyAccountPage macc =new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		//Assert.assertEquals(targetPage, true,"Login failed");
		Assert.assertTrue(targetPage);
		} catch (Exception e) {
			// TODO: handle exception
			Assert.fail();	
	}
		logger.info("****Finished TC_002LoginTest*****");
	}
	

}
