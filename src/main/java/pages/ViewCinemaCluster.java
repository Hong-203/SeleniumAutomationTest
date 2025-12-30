package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ViewCinemaCluster {
    private WebDriver driver;

    private By byLnkCinemaCluster = By.xpath("//a[h4[text()='Cụm Rạp']]");
    private By byCinemaSectionContent = By.xpath("//h4[contains(text(),'BHD Star Cineplex - Phạm Hùng')]");

    public ViewCinemaCluster(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCinemaCluster() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(byLnkCinemaCluster));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
        System.out.println("Đã click menu Cụm Rạp bằng JS.");

        try {
            WebElement target = wait.until(ExpectedConditions.presenceOfElementLocated(byCinemaSectionContent));
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", target);
            System.out.println("Đã thực hiện lệnh cuộn trang bằng JS.");
        } catch (Exception e) {
            System.err.println("Không tìm thấy vùng nội dung để cuộn xuống.");
        }
    }

    public boolean isMenuCinemaActive() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            boolean isActive = wait.until(ExpectedConditions.attributeContains(byLnkCinemaCluster, "class", "active"));
            String currentClass = driver.findElement(byLnkCinemaCluster).getAttribute("class");
            System.out.println("Class hiện tại sau khi đợi: " + currentClass);
            return isActive;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCinemaSectionDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(byCinemaSectionContent)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}