package pages.walletHub;

import enums.SelectorType;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import utils.LoggerUtil;

public class ProfilePage extends BasePage {
    private final String REVIEWS_SECTION_LABEL = "[class=\"btn blue-brds\"]";
    private final String DROP_DOWN_lABEL = "//span[text()=\"Select...\"]";
    private final String REVIEW_FIELD = "textarea[placeholder=\"Write your review...\"]";
    private final String HOVER_STAR_HEX_CSS = "[stroke=\"#4ae0e1\"]";
    private final String AWESOME_LABEL_AFTER_REVIEW = "//h2[text()=\"Awesome!\"]";
    private final String POSTED_LABEL_AFTER_REVIEW = "//h4[text()=\"Your review has been posted.\"]";
    private final String SUBMIT_BTN = "//div[text()=\" Submit \"]";
    private final String RATING_BAR = "//div[@class='rating-box-wrapper']";


    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void clickReviewLabel() throws InterruptedException {
        waitForElementToBeClickable(SelectorType.CSS_SELECTOR, REVIEWS_SECTION_LABEL);
        Thread.sleep(2500);  //this is needed as this button has some staleness.
        click(SelectorType.CSS_SELECTOR, REVIEWS_SECTION_LABEL);
        LoggerUtil.logInfo("Review Section Clicked!");
    }

    public int getTotalRatingBars() {  //this is silly as on UI just one rating bar is visible but in DOM there are more than and is super dynamic and to handle that this method is used to return total number of silly
        return getNumberOfElements(SelectorType.XPATH, RATING_BAR);
    }

    public void hoverOnRatingWithCount(int starsToBeHovered, int reviewSize) {
        waitForElementVisibility(SelectorType.XPATH, DROP_DOWN_lABEL);
        LoggerUtil.logInfo("(//div[@class='rating-box-wrapper'])[" + reviewSize + "]//*[@aria-label='" + starsToBeHovered + " star rating']");
        hoverOverElement(SelectorType.XPATH, "(//div[@class='rating-box-wrapper'])[" + reviewSize + "]//*[@aria-label='" + starsToBeHovered + " star rating']");
    }

    public int getHoveredStarsCount() {
        int count = getNumberOfElements(SelectorType.CSS_SELECTOR, HOVER_STAR_HEX_CSS);
        LoggerUtil.logInfo("Stars Selected Count:" + count);
        return count;
    }

    public void giveRating(int starsToBeGiven) {
        click(SelectorType.XPATH, "(//div[@class='rating-box-wrapper'])[3]//*[@aria-label='" + starsToBeGiven + " star rating']");
        LoggerUtil.logInfo(starsToBeGiven + " Stars Rating Given!");
    }

    public void selectValueFromDropDown(String valueToBeSelected) {
        click(SelectorType.XPATH, DROP_DOWN_lABEL);
        waitForElementToBeClickable(SelectorType.XPATH, "//LI[text()='" + valueToBeSelected + "']");
        click(SelectorType.XPATH, "//LI[text()='" + valueToBeSelected + "']");
        waitForElementVisibility(SelectorType.XPATH, "//span[text()='" + valueToBeSelected + "']");
        LoggerUtil.logInfo(valueToBeSelected + " Selected from dropdown!");
    }

    public void submitReview(String reviewText) {
        sendKeys(SelectorType.CSS_SELECTOR, REVIEW_FIELD, reviewText);
        click(SelectorType.XPATH, SUBMIT_BTN);
        LoggerUtil.logInfo(reviewText + " is written as a review!");
    }


    public boolean isReviewPosted() {
        if (getNumberOfElements(SelectorType.XPATH, AWESOME_LABEL_AFTER_REVIEW) > 0 && getNumberOfElements(SelectorType.XPATH, POSTED_LABEL_AFTER_REVIEW) > 0) {
            return true;
        } else {
            return false;
        }
    }

}
