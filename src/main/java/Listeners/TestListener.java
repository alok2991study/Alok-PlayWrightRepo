package Listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.microsoft.playwright.Page;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

        // Create test entry in report
        ExtentTest test = ExtentReportManager
                .getInstance()
                .createTest(result.getMethod().getMethodName());

        ExtentReportManager.setTest(test);

        System.out.println("Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        // Auto screenshot on PASS
        Page page = getPageFromTest(result);

        if (page != null) {

            String screenshotPath = ScreenshotUtil.takeScreenshot(
                    page,
                    result.getMethod().getMethodName() + "_PASSED"
            );

            try {
                // 👇 Attach screenshot inline in report
                ExtentReportManager.getTest()
                        .pass("Test Passed",
                                MediaEntityBuilder
                                        .createScreenCaptureFromPath(screenshotPath)
                                        .build()
                        );
            } catch (Exception e) {
                ExtentReportManager.getTest().pass("Test Passed");
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {

        // Auto screenshot on FAIL
        Page page = getPageFromTest(result);

        if (page != null) {

            String screenshotPath = ScreenshotUtil.takeScreenshot(
                    page,
                    result.getMethod().getMethodName() + "_FAILED"
            );

            try {
                // 👇 Attach screenshot + error in report
                ExtentReportManager.getTest()
                        .fail("Test Failed: " + result.getThrowable().getMessage(),
                                MediaEntityBuilder
                                        .createScreenCaptureFromPath(screenshotPath)
                                        .build()
                        );
            } catch (Exception e) {
                ExtentReportManager.getTest()
                        .fail("Test Failed: " + result.getThrowable());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().skip("Test Skipped: "
                + result.getThrowable());
    }

    // Helper — get Page object from test instance
    private Page getPageFromTest(ITestResult result) {
        try {
            return (Page) result.getInstance()
                    .getClass()
                    .getDeclaredField("page")
                    .get(result.getInstance());
        } catch (Exception e) {
            System.out.println("Could not get page: " + e.getMessage());
            return null;
        }
    }
}