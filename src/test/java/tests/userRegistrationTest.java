package tests;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.internal.annotations.ITest;
import org.testng.annotations.AfterMethod;
import static org.testng.Assert.assertTrue;
import org.apache.commons.io.FileUtils;

import data.ExcelReader;
import pages.homePage;
import pages.userRegistrationPage;

public class userRegistrationTest extends testBase{

	homePage homeObject ; 
	userRegistrationPage registerObject ; 
	String URL;

	@DataProvider(name="ExcelData")
	public Object[][] userRegisterData() throws IOException
	{
		// get data from Excel Reader class 
		ExcelReader ER = new ExcelReader();
		return ER.getExcelData();
	}

	@Test(priority=1,alwaysRun=true,dataProvider="ExcelData")
	public void UserCanRegisterSuccssfully(String firstname , String lastname , String phone , 
			String email , String password, String confirm) 
	{
		registerObject = new userRegistrationPage(driver); 
		registerObject.userRegistration(firstname,lastname,phone,email,password,confirm);
		String actual = driver.getCurrentUrl();
		Assert.assertEquals(actual, "https://www.phptravels.net/account/");
	}

	@Test(priority=2,alwaysRun=true,dataProvider="ExcelData")
	public void CheckThatCantLoginWhenEnteringInvalidPassword(String firstname , String lastname , String phone , 
			String email , String invalidPassword, String confirm) 
	{
		registerObject = new userRegistrationPage(driver); 
		registerObject.userRegistration(firstname,lastname,phone,email,invalidPassword,confirm);
		String expected="The Password field must be at least 6 characters in length";
		Assert.assertEquals(registerObject .getAlertMessage(), expected);

	}

	@Test(priority=3,alwaysRun=true,dataProvider="ExcelData")
	public void CheckThatCantLoginWhenEnteringInvalidPhoneNumber(String firstname , String lastname , String invalidphone , 
			String email , String Password, String confirm) 
	{
		registerObject = new userRegistrationPage(driver); 
		registerObject.userRegistration(firstname,lastname,invalidphone,email,Password,confirm);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.phptravels.net/register"); 

	}

	@Test(priority=3,alwaysRun=true,dataProvider="ExcelData")
	public void CheckThatCantLoginWhenEnteringInvalidFirstName(String Invalidfirstname , String lastname , String phone , 
			String email , String Password, String confirm) 
	{
		registerObject = new userRegistrationPage(driver); 
		registerObject.userRegistration(Invalidfirstname,lastname,phone,email,Password,confirm);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.phptravels.net/register"); 

	}

	@Test(priority=3,alwaysRun=true,dataProvider="ExcelData")
	public void CheckThatCantLoginWhenEnteringInvalidLastName(String firstname , String Invalidlastname , String phone , 
			String email , String Password, String confirm) 
	{
		registerObject = new userRegistrationPage(driver); 
		registerObject.userRegistration(firstname,Invalidlastname,phone,email,Password,confirm);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.phptravels.net/register"); 

	}

	@Test
	public void testScreenshotOnFailure() throws InterruptedException 
	{
		WebElement searchTxt =driver.findElement(By.id("twotabsearchtextbox"));
		searchTxt.sendKeys("Selenium WebDriver Book");
		searchTxt.submit();
		assertTrue(driver.getTitle().contains("Selenium WebDriver"));
	}

	@AfterMethod
	public void takeScreenShot(ITestResult result) throws IOException 
	{
		if (ITestResult.FAILURE == result.getStatus()) 
		{
			// create reference of TakesScreenShots
			TakesScreenshot ts = (TakesScreenshot)driver; 
			File source = ts.getScreenshotAs(OutputType.FILE); 
			FileUtils.copyFile(source, new File("./Screenshots/"+ result.getName()+".png"));
		}
	}

	@AfterTest
	public void tearDown() 
	{
		driver.quit();
	}
}
