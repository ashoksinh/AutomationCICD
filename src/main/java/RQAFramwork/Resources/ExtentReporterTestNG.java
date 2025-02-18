package RQAFramwork.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
public class ExtentReporterTestNG {

	
	
	public static ExtentReports getReportObjet() {

		String path = System.getProperty("user.dir") + "\\reports\\index.html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Extent Report: Web Automation Example: ");
		reporter.config().setDocumentTitle("Test Results: ");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("QA", "Ashoksinh Zala");	
		return extent;
		}
	
}
