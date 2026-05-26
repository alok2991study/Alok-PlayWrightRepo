package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.ConfigReader;

public class LoginPage {

    Page page;
    Locator usernameInput;
    Locator passwordInput;
    Locator signInButton;

    // Constructor
    public LoginPage(Page page) {

        this.page = page;

        // Initialize locators
        // Locators
        usernameInput = page.locator("//input[@name='Username']");
        passwordInput = page.locator(".viewablePasswordField input");
        signInButton = page.locator("text=Sign In");
    }

    // Login method
    public void login(String username, String password) {

        page.navigate("https://secure2.us.rdy.ukg.dev/ta/admin.login");

        usernameInput.fill(username);
        passwordInput.click();
        passwordInput.fill(password);
        signInButton.click();

        System.out.println(page.title());
    }
    public void login() {
        login(ConfigReader.getUsername(), ConfigReader.getPassword());
    }
}