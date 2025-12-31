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

    private By byLogo = By.xpath("//a[@class='logo-branding']//img | //img[contains(@src, 'logo')]");
    private By byHeader = By.tagName("header");

    public BookingTicketPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectSeat(String seatNumber) {
        // Tăng WebDriverWait lên 15 giây
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By seatLocator = By.xpath(String.format(xpathSeat, seatNumber));

        WebElement seatElement = wait.until(ExpectedConditions.presenceOfElementLocated(seatLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", seatElement);

        wait.until(ExpectedConditions.elementToBeClickable(seatElement)).click();
        System.out.println("Đã click chọn ghế số: " + seatNumber);

        // Đợi background chuyển sang xanh
        wait.until(d -> seatElement.getAttribute("style").contains("background-color: green"));
    }

    public void clickOrder() {
        // Đợi nút Đặt vé trong 15 giây
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        WebElement btnOrder = wait.until(ExpectedConditions.elementToBeClickable(byBtnSubmitBooking));
        btnOrder.click();
    }

    public void confirmDialog() {
        // Đợi Popup trong 20 giây
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btnConfirm = wait.until(ExpectedConditions.visibilityOfElementLocated(byBtnConfirmSuccess));
        btnConfirm.click();
    }

    public boolean isLogoDisplayed() {
        try { return driver.findElement(byLogo).isDisplayed(); } catch (Exception e) { return false; }
    }
}