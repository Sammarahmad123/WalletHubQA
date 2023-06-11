import enums.URLS;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.facebook.HomePage;
import pages.facebook.LoginPage;
import utils.JsonReader;
import utils.LoggerUtil;
import utils.WebDriverUtils;

import java.util.HashMap;
import java.util.Map;

public class FacebookPostTest {
    private WebDriver driver;
    private WebDriverUtils driverUtils;
    private LoginPage loginPage;
    private HomePage homePage;
    private String username;
    private String password;
    private String statusMessage;


    @BeforeClass
    public void setUp() {
        // Set up the WebDriver using WebDriverManager
        LoggerUtil.logInfo("Before class started...");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.addArguments("--start-maximized"); // Maximize the browser window
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driverUtils = new WebDriverUtils(driver);
        LoggerUtil.logInfo("Web driver is setup!");
        // Initialize page objects
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        // Get Test Data
        username = JsonReader.getProperty("username","facebook");
        password = JsonReader.getProperty("password","facebook");
        statusMessage = JsonReader.getProperty("statusMessage","facebook");

        // Open the Facebook login page - as pretest condition is to Open Facebook URL
        driverUtils.navigateToURL(URLS.FACEBOOK.getUrl());
        LoggerUtil.logInfo("Navigated to URL:"+URLS.FACEBOOK.getUrl());
    }

    @Test
    public void testPostStatusMessage() {
        LoggerUtil.logInfo("Facebook post test started...");
        try{
            loginPage.acceptCookie();
        }catch (Exception e){
            LoggerUtil.logInfo("Cookies widget not opened!");
        }

        loginPage.login(username, password);
        homePage.clickHomeButton();
        homePage.clickCreatePostLabel();
        homePage.enterPostText(statusMessage);
        LoggerUtil.logInfo("Text of Status to be posted:"+statusMessage);
        homePage.clickPostButton();
    }

    @AfterClass
    public void tearDown() {
        // Quit the WebDriver
        if (driver != null) {
            driver.quit();
        }
    }
}
