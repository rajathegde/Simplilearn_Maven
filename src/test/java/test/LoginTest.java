package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.LoginPage;

public class LoginTest extends Baseclass {
	
	XSSFWorkbook wbook;
	XSSFSheet sheet;

	@Parameters({"UserName","Password"})
	@Test
	public void negetivelogin(String UserNameval,String Passval) {
		test = report.startTest("Negetive Login Test");
		LoginPage Login = new LoginPage();
		Login.login(UserNameval,Passval);
		Login.ErrorCheck();
		report.endTest(test);

	}

	@Parameters({"UserName","CorrectPass"})
	@Test
	public void positivelogin(String UserNameval,String Passval) {
		test = report.startTest("Possitive Login Test");
		LoginPage Login = new LoginPage();
		Login.login(UserNameval,Passval);
		report.endTest(test);
	}
	
	@Test
	public void ExcelLogin() throws IOException {
		FileInputStream fis=new FileInputStream("exceldata.xlsx");
		wbook=new XSSFWorkbook(fis);
		sheet = wbook.getSheet("data");
		
		String UserNameval = sheet.getRow(1).getCell(0).getStringCellValue();
		String Passval = sheet.getRow(1).getCell(1).getStringCellValue();
		
		test = report.startTest("Negetive Login Test");
		LoginPage Login = new LoginPage();
		Login.login(UserNameval,Passval);
		Login.ErrorCheck();
		report.endTest(test);

	}



}
