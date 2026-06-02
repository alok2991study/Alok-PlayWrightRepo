import base.BaseTest;
import Listeners.TestListener;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.nio.file.Paths;


@Listeners(TestListener.class)
public class FirstTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(FirstTest.class);

    @Test
    public void firstTest() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.login();
        String actualTitle= page.title();
        String expectedTitle="Ready";
        Assert.assertEquals(actualTitle,expectedTitle);
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot.png")));
    }
}