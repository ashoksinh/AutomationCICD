package RQAFramwork.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RQAFramwork.AbstarctComponents.AbstarctComponents;

public class CheckOutPage extends AbstarctComponents {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);// the constructor of this will be triggerd when you call this method.
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement selectCountry;
	@FindBy(css = ".action__submit")
	WebElement submit;
	By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {
		// 7. Wrapping up end to end automation Script on Purchasing Order in Ecommerce
		// App.
		Actions action = new Actions(driver);
		action.sendKeys(country, countryName).build().perform();
		// dropDown.sendKeys(email);
		waitForElementToAppear(results);
		selectCountry.click();
	}

	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}
}
