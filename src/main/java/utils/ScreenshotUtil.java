package utils;

import com.microsoft.playwright.Page;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static void takeScreenshot(Page page, String fileName) {

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
    }
}