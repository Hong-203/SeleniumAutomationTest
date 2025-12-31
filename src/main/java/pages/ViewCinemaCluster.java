package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ViewCinemaCluster {
    private WebDriver driver;

    private By byLnkCinemaCluster = By.xpath("//a[h4[text()='Cụm Rạp']]");
    private By byCinemaSection = By.xpath("//h4[contains(text(),'BHD Star Cineplex')]");
    // Locator cho danh sách logo rạp (TC_VCC_UI_07)
    private By byListLogoRap = By.xpath("//div[contains(@class, 'MuiTabs-root')]//img");
    // Locator cho tiêu đề lớn (TC_VCC_UI_04 - Mong đợi có tiêu đề "Cụm Rạp" lớn ở session)
    private By byMainTitle = By.xpath("//h2[text()='Cụm Rạp'] | //h3[text()='Cụm Rạp']");
    // Locator cho phần chú thích C18 (TC_VCC_UI_21)
    private By byC18Note = By.xpath("//p[contains(text(),'Phim dành cho người trên 18 tuổi')]");

    public ViewCinemaCluster(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCinemaCluster() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(byLnkCinemaCluster));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public boolean isLogoRapDisplayed() {
        List<WebElement> logos = driver.findElements(byListLogoRap);
        return logos.size() > 0 && logos.get(0).isDisplayed();
    }

    public boolean isMainTitleDisplayed() {
        try {
            return driver.findElement(byMainTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isC18NoteDisplayed() {
        try {
            return driver.findElement(byC18Note).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}