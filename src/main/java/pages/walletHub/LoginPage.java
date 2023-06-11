package pages.walletHub;

import enums.SelectorType;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import utils.LoggerUtil;

public class LoginPage extends BasePage {

    private final String EMAIL_ADDRESS_FIELD = "[name='em']";
    private final String PASSWORD_FIELD = "[name='pw']";
    private final String LOGIN_BUTTON = "//span[text()='Login']";

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public void login(String email, String password) {
        sendKeys(SelectorType.CSS_SELECTOR, EMAIL_ADDRESS_FIELD, email);
        sendKeys(SelectorType.CSS_SELECTOR, PASSWORD_FIELD, password);
        click(SelectorType.XPATH, LOGIN_BUTTON);
        LoggerUtil.logInfo("Login Done!");
    }

}
