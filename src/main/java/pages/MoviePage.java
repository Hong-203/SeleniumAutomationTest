package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MoviePage {
    private WebDriver driver;

    // Locator cho thẻ bao quanh phim "The Gentlemen"
    // Chúng ta tìm thẻ <a> mà bên trong có chứa text 'The Gentlemen'
    private By byLnkMovieDetail = By.xpath("//a[contains(., 'The Gentlemen')]");

    // Locator đặc trưng trong trang chi tiết để xác nhận đã chuyển trang thành công
    // (Giả sử trang chi tiết có tiêu đề h3 chứa tên phim)
    // Thay vì chỉ tìm h3, ta tìm bất cứ thẻ nào chứa đúng chữ đó ở trang chi tiết
    private By byMovieDetailTitle = By.xpath("//*[contains(text(), 'The Gentlemen')]");

    public MoviePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickMovieDetail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(byLnkMovieDetail));
        element.click();
        System.out.println("Đã click vào phim The Gentlemen.");
    }

    public boolean isMovieDetailOpened() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // 1. Đợi URL chứa mã phim hoặc chữ 'detail' để chắc chắn đã chuyển trang
            wait.until(ExpectedConditions.urlContains("detail"));

            // 2. Đợi tiêu đề phim hiển thị (Dùng locator thoáng hơn)
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(byMovieDetailTitle));

            System.out.println("Nội dung tiêu đề tìm thấy: " + title.getText());
            return title.isDisplayed();
        } catch (Exception e) {
            System.err.println("Lỗi: Không tìm thấy tiêu đề phim ở trang chi tiết sau 15s.");
            return false;
        }
    }
}