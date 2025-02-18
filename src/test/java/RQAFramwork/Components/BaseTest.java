package RQAFramwork.Components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import RQAFramwork.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage; // Making object of this class public so other classes can access this.

	public WebDriver intializeDriver() throws IOException {
		// Properties Class
		 Properties prop = new Properties();
		FileInputStream files = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\RQAFramwork\\Resources\\GlobalData.properties");
		prop.load(files);
		String browserName = prop.getProperty("browser");
		
		if (browserName.equalsIgnoreCase("chrome")) {
			// 1. Creating Base Test which holds Common Test configuration methods.
			ChromeOptions options = new ChromeOptions();

			WebDriverManager.chromedriver().setup();
			options.addArguments("--remote-allow-origins=*");
			// Allow WebSocket connections from all origins
			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// System.out.println("Driver is initialized: " + (driver != null));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// Read json to String

		String jsonCotent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// Convert String HashMap Jackson Databind
		// Add Dependency to the project (Jackson Databind)

		ObjectMapper mapper = new ObjectMapper();

		// Convert JSON string to a List of HashMaps
		List<HashMap<String, String>> dataList = mapper.readValue(jsonCotent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return dataList;

	}

	// Tacking screenshot //

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
	} // Extent Reports

	@BeforeMethod(alwaysRun = true)
	// 2. Initialize Driver and create utility to launch App with BeforeMethod
	// annotation
	public LandingPage launchApplication() throws IOException {
		driver = intializeDriver();
		landingPage = new LandingPage(driver);
		// System.out.println("Driver is initialized: " + (driver != null));
		landingPage.goTo();
		return landingPage;
	}
	

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
		// driver.quit();
	}
}
