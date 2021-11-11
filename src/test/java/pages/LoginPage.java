package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import test.Baseclass;

public class LoginPage {
	WebDriver driver = Baseclass.driver;
	ExtentTest test = Baseclass.test;
	
	
	//*************************WebElements**********************
	@FindBy(linkText="Log in")
	WebElement LoginLink;
	
	@FindBy(name="user_login")
	WebElement UserName;
	
	@FindBy(id="password")
	WebElement Password;
	
	@FindBy(className="rememberMe")
	WebElement remember;
	
	@FindBy(name="btn_login")
	WebElement Login;
	
	@FindBy(id="msg_box")
	WebElement Error;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//***************************************Methods************************
	public void login(String UserNameval,String Passval) {

		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(LoginLink));

		LoginLink.click();
		test.log(LogStatus.PASS, "Click on Login Link", "Clicked on Login Link");
		UserName.sendKeys(UserNameval);
		test.log(LogStatus.PASS, "Enter UserName "+UserNameval, "Successfully Entered UserName");
		Password.sendKeys(Passval);
		test.log(LogStatus.PASS, "Enter Password "+Passval, "Successfully Entered Password");
		remember.click();
		test.log(LogStatus.PASS, "Click on Remember me", "Clicked on Remember me");
		Login.click();
		test.log(LogStatus.PASS, "Click on Login Button", "Clicked on Login Button");
	}

	public void ErrorCheck() {
		String ActualMsg = Error.getText();
		String ExpectedMsg = "The email or password you have entered is invalid.";
		//Assert.assertEquals(ActualMsg, ExpectedMsg);

		SoftAssert soft = new SoftAssert();
		soft.assertEquals(ActualMsg, ExpectedMsg);
		System.out.println("Soft assertion will help to execute this"); //if failure
		soft.assertAll(); //all failures throw here
	}

}
