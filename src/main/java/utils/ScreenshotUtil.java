package utils;

import com.microsoft.playwright.Page;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String takeScreenshot(Page page, String fileName) {

        // Create screenshots folder automatically
        File directory = new File("screenshots");

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());

        String path = "screenshots/" + fileName + "_" + timeStamp + ".png";

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(path))
                .setFullPage(true));

        System.out.println("Screenshot saved: " + path);
        return path;
    }
    // 👇 Overload — auto screenshot on failure
    public static String takeScreenshotOnFailure(Page page, String testName) {
        return takeScreenshot(page, testName + "_FAILED");
    }

    // 👇 Overload — screenshot as byte array for Allure inline embed
    public static byte[] takeScreenshotAsBytes(Page page) {
        return page.screenshot(new Page.ScreenshotOptions()
                .setFullPage(true));
    }
}