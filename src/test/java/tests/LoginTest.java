package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExelUtils;
import utils.ExtentReportManager;
import utils.Log;

public class LoginTest extends BaseTest {

	@DataProvider(name = "LoginData")
	public Object[][] getLoginData() throws IOException {

		String filePath = System.getProperty("user.dir") + "/testdata/TestData.xlsx";
		ExelUtils.loadExcel(filePath, "TestData");
		int rowCount = ExelUtils.getRowCount();
		Object[][] data = new Object[rowCount - 1][2];

		for (int i = 1; i < rowCount; i++) {

			data[i - 1][0] = ExelUtils.getCellData(i, 0); // Username
			data[i - 1][1] = ExelUtils.getCellData(i, 1); // Password
		}
		ExelUtils.closeExcel();
		return data;
	}
	
	@DataProvider(name="LoginData2")
	public Object[][] getData() {

		return new Object[][] { 
			{ "user1", "pass1" }, 
			{ "user2", "pass2" }, 
			{ "user3", "pass3" } };
	}

//	@Test(dataProvider = "LoginData2")
	
//	@Test
//	@Parameters({"username","password"})
	@Test
	public void testVAlidLogin() {

		Log.info("Starting login test...");
		test = ExtentReportManager.createTest("Login Test");

		test.info("navigating to URL");

		LoginPage loginPage = new LoginPage(driver);

		Log.info("Adding credentials");
		test.info("Adding Credentials");
		loginPage.enterUserName("admin@yourstore.com");
		loginPage.enterPassword("admin");
//		loginPage.enterUserName(username);
//		loginPage.enterPassword(password);
		test.info("Clicking on Login button");
		loginPage.clickLogin();

		System.out.println("Title of this page is : " + driver.getTitle());
		Log.info("Verifying page title");
		test.info("Verifying page title");
		Assert.assertEquals(driver.getTitle(), "Just a moment...");

		test.pass("Login Successful");

	}

//	@Test
//	public void testLoginWithInvalidCredentials() {
//
//		Log.info("Starting login test...");
//		test = ExtentReportManager.createTest("Login Test with Invalid Credentials");
//
//		test.info("Navigating to URL");
//		LoginPage loginPage = new LoginPage(driver);
//
//		Log.info("Adding credentials");
//		test.info("Adding Credentails");
//		loginPage.enterUserName("admin1234@yourstore.com");
//		loginPage.enterPassword("admin123");
//		test.info("Clicking on Login button");
//		loginPage.clickLogin();
//
//		System.out.println("Title of the page is : " + driver.getTitle());
//		Log.info("Verifying page title");
//		test.info("Verifying page title");
//		Assert.assertEquals(driver.getTitle(), "Just a moment...123");
//
//		test.pass("Login Successful");
//
//	}

}
