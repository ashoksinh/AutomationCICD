package RQAFramwork.Components;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import RQAFramwork.Resources.ExtentReporterTestNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal();

	ExtentReports extent = ExtentReporterTestNG.getReportObjet();

	@Override
	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // Assigning one Unique Thread ID(ErrorValdationTest->test
		// System.out.println("Test Started: " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
		System.out.println("Test Passed: " + result.getName());

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// Screenshots// Attach to report
		extentTest.get().log(Status.FAIL, "Test Failed");

		extentTest.get().fail(result.getThrowable());

		// test.fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Test Failed: " + result.getName());
		System.out.println("Failure Reason: " + result.getThrowable());
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped: " + result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// This method is used when a test fails but is within the success percentage
		System.out.println("Test Failed but within Success Percentage: " + result.getName());
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Test Suite Started: " + context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		// System.out.println("Test Suite Finished: " + context.getName());
		extent.flush();
	}

}
