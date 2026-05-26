import base.BaseTest;
import Listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;

@Listeners(TestListener.class)
public class FirstTest extends BaseTest {

    @Test
    public void firstTest() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.login();
    }
}