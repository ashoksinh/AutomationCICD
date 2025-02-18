package RQAFramwork;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import RQAFramwork.Components.BaseTest;
import RQAFramwork.Components.Retry;
import RQAFramwork.PageObjects.CartPage;
import RQAFramwork.PageObjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

		landingPage.loginApplication("rajat@packiu.com", "Ina@123");
		Assert.assertEquals("Incorrect emaisl or password.", landingPage.getErrorMessage());
	}

	@Test(retryAnalyzer = Retry.class)
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "IPHONE 13 PRO";
		ProductCatalogue productCatalogue = landingPage.loginApplication("rajeshsharma@packiu.com", "India@123");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean matchP = cartPage.verifyProductDisplay("IPHONE 13 PRO");
		Assert.assertTrue(matchP);

	}

}
