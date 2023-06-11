package pages;

import enums.SelectorType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LoggerUtil;

import java.util.List;

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
    public List<WebElement> findElements(SelectorType selectorType, String selector) {
        By locator = getLocator(selectorType, selector);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
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
    public void hoverOverElement(SelectorType selectorType, String selectorValue) {
        WebElement element = findElement(selectorType,selectorValue);
        if (element != null) {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
        } else {
            LoggerUtil.logInfo("Element not found");
        }
    }
    public void waitForElementVisibility(SelectorType selectorType,String elementToBeSearched) {
        WebElement element = findElement(selectorType, elementToBeSearched);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForElementToBeClickable(SelectorType selectorType,String elementToBeSearched) {
        WebElement element = findElement(selectorType, elementToBeSearched);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public int getNumberOfElements(SelectorType selectorType, String elementToBeSearched) {
        List<WebElement> elements = findElements(selectorType, elementToBeSearched);
        return elements.size();
    }

}

