package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

		WebDriver driver;
		
		public  LandingPage(WebDriver driver) {      //constructor
		 super(driver);
			//initialization	driver
			this.driver= driver;
			
			PageFactory.initElements(driver, this);
			
		}
		
		
		//WebElement userEmails=driver.findElement(By.id("userEmail"));
		//PageFactory
		
		@FindBy(id="userEmail")
		WebElement userEmail;

		//WebElement passwords=driver.findElement(By.id("userPassword"));
		
		@FindBy(id="userPassword")
		WebElement passwordEle;
		
		//WebElement logins=driver.findElement(By.id("login"));
		
		@FindBy(id="login")
		WebElement submit;
		
		//div[@class='ng-tns-c4-2 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']
		@FindBy(css="[class*='flyInOut']")
		WebElement errorMessage;
		
		//Action method
		
			public ProductCatalogue loginApplication(String email, String password) {
			// TODO Auto-generated method stub
			userEmail.sendKeys(email);
			passwordEle.sendKeys(password);
			submit.click();
			ProductCatalogue productCatalogue= new ProductCatalogue(driver);
			return productCatalogue;
			

		}
			public String geterrorMessage() {
				waitForWebElementToAppear(errorMessage);
				return errorMessage.getText();
			}
		
			
			public void goTo() {
				
				driver.get("https://rahulshettyacademy.com/client");

			}
}
