package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public WebDriverUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10); // Set the wait timeout as per your requirement
    }

    public void navigateToURL(String url) {
        driver.get(url);
    }
}
