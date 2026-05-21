import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class FirstTest {

    @Test
    public void firstTest() {

        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        Page page = browser.newPage();

        page.navigate("https://google.com");

        System.out.println(page.title());

        browser.close();
        playwright.close();
    }
}