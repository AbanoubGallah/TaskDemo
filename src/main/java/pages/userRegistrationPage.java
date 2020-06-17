package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class userRegistrationPage extends pageBase {

	public userRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(name="firstname")
	WebElement fnTxtBox; 

	@FindBy(name="lastname")
	WebElement lnTxtBox ; 
	
	@FindBy(name="phone")
	WebElement phTxtBox ; 

	@FindBy(name="email")
	WebElement emailTxtBox ; 

	@FindBy(name="password")
	WebElement passwordTxtBox ; 

	@FindBy(name="confirmpassword")
	WebElement confirmPasswordTxtBox ; 

	@FindBy(xpath="//*[@id=\"headersignupform\"]/div[8]/button")
	WebElement registerBtn ; 
	
	@FindBy(xpath="//div[@class=\"alert alert-danger\"]")
	WebElement alertMessage;
	
	@FindBy(className = "dropdown-item tr")
	public WebElement logoutLink; 

	@FindBy(linkText="https://www.phptravels.net/login")
	WebElement myAccountLink; 
	
	
	public void userRegistration(String firstName , String lastName , 
			String phone , String email , String password, String confirm) 
	{
		setTextElementText(fnTxtBox, firstName);
		setTextElementText(lnTxtBox, lastName);
		setTextElementText(phTxtBox, phone);
		setTextElementText(emailTxtBox, email);
		setTextElementText(passwordTxtBox, password);
		setTextElementText(confirmPasswordTxtBox, confirm);
		clickButton(registerBtn);
	}
	public String getAlertMessage()
	{
		return alertMessage.getText();
		
	}
	
	
	public void userLogout() 
	{
		clickButton(logoutLink);
	}
	
	public void openMyAccountPage() 
	{
		clickButton(myAccountLink);
	}
}


