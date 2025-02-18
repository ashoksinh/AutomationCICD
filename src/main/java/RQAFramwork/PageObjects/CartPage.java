package RQAFramwork.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RQAFramwork.AbstarctComponents.AbstarctComponents;

public class CartPage extends AbstarctComponents {
	WebDriver driver;
	// Page Factory
	@FindBy(css = ".totalRow button")
	WebElement checkOutEle;
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts; 
	// ** You can use the PageFactory to reduce the code and not using
	// driver.findelements**
	public CartPage(WebDriver driver) {
		super(driver);
		// Initializations
		this.driver = driver;
		PageFactory.initElements(driver, this);// the constructor of this will be triggerd when you call this method.
	}
	// 6. Logic to verify items in the cart with Streams and Checkout.
	public Boolean verifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}
	public CheckOutPage goToCheckout() throws InterruptedException {
		
		Thread.sleep(3000);
		checkOutEle.click();
		return new CheckOutPage(driver);
	}
}
