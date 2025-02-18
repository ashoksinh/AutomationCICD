package RQAFramwork.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RQAFramwork.AbstarctComponents.AbstarctComponents;

public class OrderPage extends AbstarctComponents {
	WebDriver driver;
	// Page Factory
	
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames; 
	// ** You can use the PageFactory to reduce the code and not using
	// driver.findelements**
	public OrderPage(WebDriver driver) {
		super(driver);
		// Initializations
		this.driver = driver;
		PageFactory.initElements(driver, this);// the constructor of this will be triggerd when you call this method.
	}
	// 6. Logic to verify items in the cart with Streams and Checkout.
	public Boolean verifyOrderDisplay(String productName) {
		Boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}

}
