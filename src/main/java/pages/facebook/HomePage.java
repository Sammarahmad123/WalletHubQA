package pages.facebook;

import enums.SelectorType;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class HomePage extends BasePage {

    // Locators
    private final String HOME_BUTTON_SELECTOR = "[aria-label='Home']";
    private final String CREATE_POST_LABEL_SELECTOR = "//span[contains(text(), \"What's on your mind\")]";
    private final String CREATE_POST_FIELD_SELECTOR = "//div[contains(@aria-label, \"What's on your mind\")]";
    private final String CREATE_POST_BUTTON_SELECTOR = "//span[contains(text(), 'Post')]";


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickHomeButton() {
        click(SelectorType.CSS_SELECTOR, HOME_BUTTON_SELECTOR);
    }

    public void clickCreatePostLabel() {
        click(SelectorType.XPATH, CREATE_POST_LABEL_SELECTOR);
    }

    public void enterPostText(String postText) {
        sendKeys(SelectorType.XPATH, CREATE_POST_FIELD_SELECTOR, postText);
    }

    public void clickPostButton() {
        click(SelectorType.XPATH, CREATE_POST_BUTTON_SELECTOR);
    }


}
