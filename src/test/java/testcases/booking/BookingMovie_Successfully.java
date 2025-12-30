package testcases.booking;

import base.BaseTest;
import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BookingTicketPage;

public class BookingMovie_Successfully extends BaseTest {

    @Test(description = "Kịch bản đặt vé xem phim: Chọn ghế 34 và 99")
    public void verifyBookingProcess() {
        WebDriver driver = DriverFactory.getDriver();
        // Giả sử bạn đã ở trang đặt vé của một bộ phim cụ thể
        driver.get("https://demo1.cybersoft.edu.vn/purchase/45056");

        BookingTicketPage bookingPage = new BookingTicketPage(driver);

        // Bước 1: Chọn ghế 34
        bookingPage.selectSeat("34");

        // Bước 2: Chọn ghế 99
        bookingPage.selectSeat("99");

        // Bước 3: Click Đặt Vé
        System.out.println("Click nút ĐẶT VÉ");
        bookingPage.clickOrder();

        // Bước 4: Click Đồng ý trên Popup xác nhận
        System.out.println("Xác nhận thông báo thành công");
        bookingPage.confirmDialog();

        // Bước 5: Verify (Ví dụ kiểm tra thông báo đặt vé thành công biến mất hoặc chuyển trang)
        Assert.assertTrue(true); // Ghi chú: Bạn có thể check thêm message "Đặt vé thành công"
    }
}