package rahulshettyacademy.SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

	
		@Test(groups= {"ErrorHandling"},retryAnalyzer= Retry.class)
		public void LoginErrorValidation() throws IOException, InterruptedException {
		
			
		
		String productName = "IPHONE 13 PRO";
		LandingPage landingPage= launchApplication();
		
			
		ProductCatalogue productCatalogue = landingPage.loginApplication("rashmigowa@gmail.com", "Pasword@123");
		landingPage.geterrorMessage();
		Assert.assertEquals("Incorrect email or password.", landingPage.geterrorMessage());
		

	}
		@Test
		public void ProductErrorValidation() throws IOException, InterruptedException {
		
		
		String productName = "IPHONE 13 PRO";
		LandingPage landingPage= launchApplication();
		
		
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("rashmigowda@gmail.com", "Password@123");

		List<WebElement> products = productCatalogue.getProductList();

		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay("IPHONE 16 PRO");
		Assert.assertFalse(match);
		
	}

}
