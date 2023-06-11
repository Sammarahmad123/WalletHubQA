import enums.URLS;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.walletHub.LoginPage;
import pages.walletHub.ProfilePage;
import utils.JsonReader;
import utils.LoggerUtil;
import utils.WebDriverUtils;

import java.util.HashMap;
import java.util.Map;

public class WalletHubTest {
    private WebDriver driver;
    private WebDriverUtils driverUtils;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private String email;
    private String password;
    private String reviewText;
    private int starsToBeGiven;
    private String profilePageUrl;

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

        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

        email = JsonReader.getProperty("email", "walletHub");
        password = JsonReader.getProperty("password", "walletHub");
        reviewText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam erat volutpat. Vivamus condimentum scelerisque mi, " +
                "at tempus elit euismod nec.";
        starsToBeGiven = 4;
        profilePageUrl = "https://wallethub.com/profile/all-write-insurance-agency-13316484i";

        driverUtils.navigateToURL(URLS.WALLET_HUB.getUrl());
        LoggerUtil.logInfo("Navigated to URL:" + URLS.WALLET_HUB.getUrl());
    }

    @Test
    public void testPostStatusMessage() throws InterruptedException {
        loginPage.login(email, password);
        driverUtils.navigateToURL(profilePageUrl);
        profilePage.clickReviewLabel();
        int total_review_bars = profilePage.getTotalRatingBars();
        LoggerUtil.logInfo("Total Review Bars in DOM:" + total_review_bars);
        profilePage.hoverOnRatingWithCount(starsToBeGiven, total_review_bars);
        int hoveredStars = profilePage.getHoveredStarsCount();
        Assert.assertEquals(hoveredStars, starsToBeGiven * total_review_bars);
        profilePage.giveRating(starsToBeGiven);
        int starsCountAfterGivingRating = profilePage.getHoveredStarsCount();
        Assert.assertEquals(starsCountAfterGivingRating, starsToBeGiven * total_review_bars);
        profilePage.selectValueFromDropDown("Health Insurance");
        profilePage.submitReview(reviewText);
        Assert.assertTrue(profilePage.isReviewPosted());
    }

    @AfterClass
    public void tearDown() {
        // Quit the WebDriver
        if (driver != null) {
            driver.quit();
        }
    }
}
