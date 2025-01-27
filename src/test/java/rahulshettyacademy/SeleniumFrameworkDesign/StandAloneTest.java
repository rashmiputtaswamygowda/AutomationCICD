package rahulshettyacademy.SeleniumFrameworkDesign;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new comments are added

		String productName="IPHONE 13 PRO";
		//Use WebdriverManager
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage= new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("rashmigowda@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List <WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		
		//Using Java Streams
		WebElement prod= products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		//ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		
		List <WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean match=	cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		/*
		driver.findElement(By.cssSelector("input[placeholder*='Select Country']")).sendKeys("Ind");
		List<WebElement> options = driver.findElements(By.xpath("//span[@class='ng-star-inserted'][text()=' India']"));

		for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase("India")) {
				option.click();
			}
		      
		      driver.findElement(By.cssSelector("a.ng-star-inserted")).click();
		      
		     System.out.println(driver.findElement(By.cssSelector("label[class='ng-star-inserted']")).getText());
		*/
		     //or
		     Actions a = new Actions(driver);
		     a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"India").build().perform();
		     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		     
		     driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		     
		     driver.findElement(By.cssSelector(".action__submit")).click();
		     
		   String confirmMessage=  driver.findElement(By.cssSelector(".hero-primary")).getText();
		  Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	
           driver.close();
           
}
}
