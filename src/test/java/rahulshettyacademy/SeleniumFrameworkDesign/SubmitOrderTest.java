package rahulshettyacademy.SeleniumFrameworkDesign;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{

	String productName = "IPHONE 13 PRO";
		
	    @Test(dataProvider="getData", groups={"Purchase"})
		public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		
		//LandingPage landingPage= launchApplication();
		
		// Use WebdriverManager
		/*WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
          */ //all the commented code is in BaseTest.java
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalogue.getProductList();

		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.gotoCheckOut();
		checkoutPage.selectCountry("India");
		//Thread.sleep(2000);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

		// or
		/*
		 * driver.findElement(By.cssSelector("input[placeholder*='Select Country']")).
		 * sendKeys("Ind"); List<WebElement> options = driver.findElements(By.
		 * xpath("//span[@class='ng-star-inserted'][text()=' India']"));
		 * 
		 * for (WebElement option : options) { if
		 * (option.getText().equalsIgnoreCase("India")) { option.click(); }
		 * 
		 * driver.findElement(By.cssSelector("a.ng-star-inserted")).click();
		 * 
		 * System.out.println(driver.findElement(By.cssSelector(
		 * "label[class='ng-star-inserted']")).getText());
		 */

		

	}
		//To verify if IPHONE 13 PRO is displaying in the orders page
		@Test(dependsOnMethods= {"submitOrder"})
		public void OrderHistoryTest() {
			ProductCatalogue productCatalogue = landingPage.loginApplication("rashmigowda@gmail.com", "Password@123");
			OrderPage orderPage= productCatalogue.goToOrdersPage();
			Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		}
		
		
		//Extent reports
		
		
		@DataProvider
		public Object[][] getData() throws IOException {
			/*
			HashMap<String, String> map= new HashMap<String,String>();
			map.put("email", "rashmigowda@gmail.com");
			map.put("password", "Password@123");
			map.put("product", "IPHONE 13 PRO");
			
			HashMap<String, String> map1= new HashMap<String,String>();
			map1.put("email", "shetty@gmail.com");
			map1.put("password", "Iamking@000");
			map1.put("product", "BANARSI SAREE");
			*/
			List<HashMap<String, String>> data= getJsonDataToMap(System.getProperty("user.dir")+ "//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
			return new Object[][] { {data.get(0)} , {data.get(1)} };
			
		}
		//@DataProvider
		//public Object[][] getData() {
		
			
		//	return new Object[][] { {"rashmigowda@gmail.com","Password@123","IPHONE 13 PRO"}, {"shetty@gmail.com","Iamking@000","BANARSI SAREE"}};
		
            // }
		
		
		
		
		
		
	}
