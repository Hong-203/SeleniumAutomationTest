package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BookingTicketPage {
    private WebDriver driver;

    private String xpathSeat = "//button[span[text()='%s']]";
    private By byBtnSubmitBooking = By.xpath("//button[span[text()='ĐẶT VÉ']]");
    private By byBtnConfirmSuccess = By.xpath("//button[text()='Đồng ý']");

    public BookingTicketPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectSeat(String seatNumber) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Tăng lên 20s
        By seatLocator = By.xpath(String.format(xpathSeat, seatNumber));

        // Đợi ghế xuất hiện trong DOM
        WebElement seatElement = wait.until(ExpectedConditions.presenceOfElementLocated(seatLocator));

        // Cuộn màn hình đến chỗ cái ghế để mình nhìn thấy cho đẹp
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", seatElement);

        // Đợi ghế có thể click được (không bị che bởi loading)
        wait.until(ExpectedConditions.elementToBeClickable(seatElement));

        // Click chọn ghế
        seatElement.click();
        System.out.println("Đã click chọn ghế số: " + seatNumber);

        // Đợi cho đến khi style của ghế thay đổi (chuyển sang màu xanh)
        wait.until(d -> seatElement.getAttribute("style").contains("background-color: green"));
    }

    public void clickOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btnOrder = wait.until(ExpectedConditions.elementToBeClickable(byBtnSubmitBooking));
        btnOrder.click();
    }

    public void confirmDialog() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Popup thường hiện chậm nên đợi 15s
        WebElement btnConfirm = wait.until(ExpectedConditions.visibilityOfElementLocated(byBtnConfirmSuccess));
        btnConfirm.click();
    }
}