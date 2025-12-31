package testcases.booking;

import base.BaseTest;
import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BookingTicketPage;
import pages.dialog.CommonDialog;
import testcases.login.Login01_Verify_Login_Successfully;

public class Booking_02_Negative_Validation extends BaseTest {

    @Test(description = "Kiểm tra ngăn chặn đặt vé khi chưa chọn ghế (TC_BOOK_VAL_04)")
    public void verifyBookingWithoutSeat() {
        // login trước khi mua vé (Gọi đến hàm autotest login)
        Login01_Verify_Login_Successfully login = new Login01_Verify_Login_Successfully();
        login.Login01_Login_Successfully();

        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://demo1.cybersoft.edu.vn/purchase/45056");

        BookingTicketPage bookingPage = new BookingTicketPage(driver);
        CommonDialog dialog = new CommonDialog(driver);

        System.out.println("Step: Nhấn ĐẶT VÉ khi chưa chọn bất kỳ ghế nào");
        bookingPage.clickOrder();

        System.out.println("Verify: Thông báo nhắc nhở chọn ghế (TC_BOOK_UX_08)");
        String msg = dialog.getTextMessage();
        Assert.assertEquals(msg, "Bạn chưa chọn ghế. Vui lòng chọn ghế ?", "Thông báo sai hoặc không xuất hiện!");
    }
}