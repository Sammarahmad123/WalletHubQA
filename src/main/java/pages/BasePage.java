package pages;

import enums.SelectorType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }



    public WebElement findElement(SelectorType selectorType, String selector) {
        By locator = getLocator(selectorType, selector);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void click(SelectorType selectorType, String selector) {
        WebElement element = findElement(selectorType, selector);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void sendKeys(SelectorType selectorType, String selector, String text) {
        WebElement element = findElement(selectorType, selector);
        element.clear();
        element.sendKeys(text);
    }

    private By getLocator(SelectorType selectorType, String selector) {
        switch (selectorType) {
            case ID:
                return By.id(selector);
            case NAME:
                return By.name(selector);
            case CLASS_NAME:
                return By.className(selector);
            case TAG_NAME:
                return By.tagName(selector);
            case LINK_TEXT:
                return By.linkText(selector);
            case PARTIAL_LINK_TEXT:
                return By.partialLinkText(selector);
            case CSS_SELECTOR:
                return By.cssSelector(selector);
            case XPATH:
            default:
                return By.xpath(selector);
        }
    }
}

