package RQAFramwork.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super();
		// Initializations
		this.driver = driver;
		PageFactory.initElements(driver, this);// the constructor of this will be triggerd when you call this method.
	}

	@FindBy(css = ".hero-primary")
	WebElement ConfirmationMessage;

	// String confirmMessage =
	// driver.findElement(By.cssSelector(".hero-primary")).getText();
	public String getConfirmationMessage() {
		return ConfirmationMessage.getText();
	}
}
