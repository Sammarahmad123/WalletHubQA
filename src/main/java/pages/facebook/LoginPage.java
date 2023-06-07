package pages.facebook;

import enums.SelectorType;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import utils.LoggerUtil;

public class LoginPage extends BasePage {

    // Locators
    private final String emailFieldSelector = "email";
    private final String passwordFieldSelector = "pass";
    private final String loginButtonSelector = "login";
    private final String acceptCookieBtnSelector = "button[title='Allow all cookies']";


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void acceptCookie() {
        click(SelectorType.CSS_SELECTOR, acceptCookieBtnSelector);
    }

    public void login(String username, String password) {
        sendKeys(SelectorType.ID, emailFieldSelector, username);
        sendKeys(SelectorType.ID, passwordFieldSelector, password);
        click(SelectorType.NAME, loginButtonSelector);
        LoggerUtil.logInfo("Login Done!");
    }
}
