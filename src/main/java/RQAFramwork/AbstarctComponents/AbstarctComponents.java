package RQAFramwork.AbstarctComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RQAFramwork.PageObjects.CartPage;
import RQAFramwork.PageObjects.OrderPage;

public class AbstarctComponents {
	// Creating Abstract Components to reuse the common methods/code in framework
	WebDriver driver;

	public AbstarctComponents(WebDriver driver) {
		// Initializations
		this.driver = driver;
		PageFactory.initElements(driver, this);// the constructor of this will be triggerd when you call this method.
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	// **Creating common methods to Abstract component and extending it in Page
	// classes
	public CartPage goToCartPage() throws InterruptedException {
		Thread.sleep(2000);
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

	public OrderPage goToOrdersPage() throws InterruptedException {
		OrderPage orderPage = new OrderPage(driver);
		Thread.sleep(2000);
		orderHeader.click();
		return orderPage;
	}

	public void waitForElementToDisAppear(WebElement element) throws InterruptedException {
		Thread.sleep(5000);
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			wait.until(ExpectedConditions.invisibilityOf(element));
	}
}
