	package testCases;
	
	import static org.testng.Assert.assertTrue;
	
	import org.testng.Assert;
	import org.testng.annotations.Test;
	
	import pageObjects.HomePage;
	import pageObjects.LoginPage;
	import pageObjects.MyAccountPage;
	import testBase.BaseClass;
	import utilities.DataProviders;
	
	
	
	public class TC_003LoginDDT extends BaseClass{
	
		@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DataDriven")  //getting data provider from different class
		public void verify_loginDDT(String email, String pwd, String exp) {
			try {
				
			
			
			//Home Page
					HomePage hp = new HomePage(driver);
					hp.clickMyAccount();
					hp.clickLogin();
					
					//Login Page
					LoginPage lp=new LoginPage(driver);
					lp.setEmail(email);
					lp.setpassword(pwd);
					lp.clickLogin();
					
					//My account Page
					MyAccountPage macc =new MyAccountPage(driver);
					boolean targetPage = macc.isMyAccountPageExists();
					
					
					/*Data is valid - login success - test pass - logout (Positive Scenario)
					Data is valid -- login failed - test fail (negative Scenario)
	
					Data is invalid - login success - test fail - logout (Positive Scenario)
					Data is invalid -- login failed - test pass (negative Scenario)
					*/
					
					if(exp.equalsIgnoreCase("Valid"))
					{
						if(targetPage == true) 
						{
							hp.clickMyAccount();
							Assert.assertTrue(true);
							macc.clickLogout();
							
						}
						else 
						{
						    assertTrue(false);
						}
					}	
					if(exp.equalsIgnoreCase("Invalid"))
					{
						if(targetPage == true) 
						{
							macc.clickMyaccount();
							macc.clickLogout();
							Assert.assertTrue(false);
							
						}
						else 
						{
						    Assert.assertTrue(true);
						}
					}
					
			} catch (Exception e)
			{
				 e.printStackTrace();
				Assert.fail();
			}
					logger.info("*****Finished TC_003LoginDDT*****");
		}
	}
