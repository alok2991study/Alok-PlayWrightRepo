import com.microsoft.playwright.*;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ScreenshotUtil;

public class FirstTest {

    @Test
    public void firstTest() {

        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(true));

        Page page = browser.newPage();

        LoginPage loginPage = new LoginPage(page);

        // Create object of LoginPage
        try {

            loginPage.login("admin", "password123");

            ScreenshotUtil.takeScreenshot(page, "HomePage");

        } catch (Exception e) {

            ScreenshotUtil.takeScreenshot(page, "Failure");

            e.printStackTrace();
        }

        browser.close();
        playwright.close();
    }
}