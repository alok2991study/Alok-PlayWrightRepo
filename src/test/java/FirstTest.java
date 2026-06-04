import base.BaseTest;
import Listeners.TestListener;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtil;

import java.nio.file.Paths;


@Listeners(TestListener.class)
public class FirstTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(FirstTest.class);

    @Test
    public void firstTest() {

        LoginPage loginPage = new LoginPage(page);
        ExcelUtil excel =
                new ExcelUtil(
                        "src/test/resources/testData.xlsx",
                        "Login");

        String username =
                excel.getCellData(1,0);

        String password =
                excel.getCellData(1,1);

        loginPage.login();
        String actualTitle= page.title();
        String expectedTitle="Ready";
        Assert.assertTrue(page.title().contains(expectedTitle));
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot.png")));
    }
}