package RQAFramwork;

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

import RQAFramwork.PageObjects.LandingPage;


// This is the test for NGRocks Code Update??

public class StandAloneTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "IPHONE 13 PRO";
		// WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// 3. Selenium Program on WebDriverManager - Login- Get Products List.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		LandingPage landingPage = new LandingPage(driver);

		driver.findElement(By.id("userEmail")).sendKeys("rajeshsharma@packiu.com");
		driver.findElement(By.id("userPassword")).sendKeys("India@123");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		// 4. Selenium Program to retrieve product and Add to Cart based on Java Streams
		WebElement sortProduct = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		sortProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		// 5. Implementation of explicit wait to handle application synchronously on
		// loading.
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// Wait untill toast message is not visible.
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		// Wait till loader message is disappers.
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		// 6. Logic to verify items in the cart with Streams and Checkout.
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(true);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		// 7. Wrapping up end to end automation Script on Purchasing Order in Ecommerce
		// App.
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build()
				.perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group-item")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		// (//button[contains(@class,'ta-item')])[2] Xpath
		// .ta-item:nth-of-type(2) // Css Selector
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println(confirmMessage);
		driver.quit();

	}
}
