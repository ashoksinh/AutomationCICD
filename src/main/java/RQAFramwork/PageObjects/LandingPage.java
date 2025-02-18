package RQAFramwork.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RQAFramwork.AbstarctComponents.AbstarctComponents;

public class LandingPage extends AbstarctComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		// Initializations
		System.out.println("Driver is initialized: " + (driver != null));
		this.driver = driver;
		PageFactory.initElements(driver, this);// the constructor of this will be triggerd when you call this method.
	}

	// WebElement emailPlaceholder= driver.findElement(By.id("userEmail"));
	// ** You can use the PageFactory to reduce the code and not using
	// driver.findelement**
	// **Creating Page object Classes for Login Screen and migrate the test**//
	// Page Factory
	@FindBy(id = "userEmail")
	WebElement userEmail;
	@FindBy(id = "userPassword")
	WebElement userPassword;
	@FindBy(id = "login")
	WebElement submit;

	@FindBy(xpath = "//div[@class='toast-bottom-right toast-container']")
	// @FindBy(css = "[class*='flyInOut']");
	WebElement errorMessage;

	// **Implementing Action methods for Page factory web elements to implement
	// logic**//
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		System.out.println("Error Message: " + errorMessage.getText());
		return errorMessage.getText();

	}

	public void goTo() {

		driver.get("https://rahulshettyacademy.com/client/");
	}
}
