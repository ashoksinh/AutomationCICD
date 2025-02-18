package RQAFramwork;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RQAFramwork.Components.BaseTest;
import RQAFramwork.PageObjects.CartPage;
import RQAFramwork.PageObjects.CheckOutPage;
import RQAFramwork.PageObjects.ConfirmationPage;
import RQAFramwork.PageObjects.OrderPage;
import RQAFramwork.PageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "IPHONE 13 PRO";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// String productName = "IPHONE 13 PRO";
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean matchP = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(matchP);
		CheckOutPage checkoutpage = cartPage.goToCheckout();
		checkoutpage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutpage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println(confirmMessage);
	}

	// Dependency for particular method
	@Test(dependsOnMethods = { "SubmitOrder" })
	public void OrderHistory() throws InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication("rajeshsharma@packiu.com", "India@123");

		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}

	// Data Providers
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				(System.getProperty("user.dir") + "\\src\\test\\java\\RQAFramwork\\data\\PurchaseOrder.json"));

		return new Object[][] { { data.get(0) }, { data.get(1) } };

//		HashMap<String, String> map= new HashMap<String, String>();
//		map.put("email", "rajeshsharma@packiu.com");
//		map.put("password","India@123");
//		map.put("product","IPHONE 13 PRO");	
//		HashMap<String, String> map1= new HashMap<String, String>();
//		map1.put("email", "rsharma@packiu.com");
//		map1.put("password","India@123");
//		map1.put("product","BANARSI SAREE");

	}
}
