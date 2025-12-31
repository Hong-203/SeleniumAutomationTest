package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MoviePage {
    private WebDriver driver;

    private By byLnkMovieDetail = By.xpath("//a[contains(., 'The Gentlemen')]");
    private By byMovieDetailTitle = By.xpath("//*[contains(text(), 'The Gentlemen')]");

    // Locators cho các Case UI/UX lỗi
    private By byPoster = By.xpath("//div[contains(@class, 'poster')]//img | //img[contains(@alt, 'The Gentlemen')]");
    private By byFooter = By.tagName("footer");
    private By byHeader = By.tagName("header");
    private By byBtnBuyTicket = By.xpath("//button[contains(text(), 'Mua vé')] | //button[span[contains(text(), 'MUA VÉ')]]");

    public MoviePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickMovieDetail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(byLnkMovieDetail));
        element.click();
    }

    public boolean isMovieDetailOpened() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("detail"));
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(byMovieDetailTitle));
            return title.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFooterDisplayed() {
        try { return driver.findElement(byFooter).isDisplayed(); } catch (Exception e) { return false; }
    }

    public boolean isHeaderDisplayed() {
        try { return driver.findElement(byHeader).isDisplayed(); } catch (Exception e) { return false; }
    }

    public String getPosterSource() {
        return driver.findElement(byPoster).getAttribute("src");
    }
}