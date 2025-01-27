package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionsImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		// code
		landingPage = launchApplication();
		throw new io.cucumber.java.PendingException();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {

		productCatalogue = landingPage.loginApplication(username, password);
		throw new io.cucumber.java.PendingException();
	}

	@When("^I add product (.+) to Cart$") // Regex-regular expression starts with ^ and ends with $
	public void I_add_product_to_Cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.gotoCheckOut();
		checkoutPage.selectCountry("India");
		// Thread.sleep(2000);
		confirmationPage = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on the Confirmation Page")
	public void message_displayed_confirmationPage(String string) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		throw new io.cucumber.java.PendingException();
		//driver.close();
	}
	
	@When("^Logged in with wrong username (.+) and password (.+)$")
	public void logged_in_with_wrong_username_and_password(String string) {
	    // Write code here that turns the phrase above into concrete actions
		landingPage.geterrorMessage();

	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("^\" ([^\"]*)\"message is displayed$")
	public void something_message_is_displayed(String strArg1)throws Throwable{
		Assert.assertEquals(strArg1, landingPage.geterrorMessage());
		throw new io.cucumber.java.PendingException();
		//driver.close();
	}
}
