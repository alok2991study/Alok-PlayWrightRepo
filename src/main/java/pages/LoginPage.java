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
        signInButton = page.locator("//button[@name='LoginButton']");
    }

    // Login method
    public void login(String username, String password) {
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