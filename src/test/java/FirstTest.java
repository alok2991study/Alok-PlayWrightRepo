

                         // 👈 add package

import base.BaseTest;
import Listeners.TestListener;           // 👈 note lowercase 'l' — fix your folder name
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;

@Listeners(TestListener.class)
public class FirstTest extends BaseTest {  // 👈 extend BaseTest

    @Test
    public void firstTest() {

        LoginPage loginPage = new LoginPage(page);  // page comes from BaseTest

        loginPage.login();   // URL + credentials from config.properties
    }
}