package testcases.booking;

import base.BaseTest;
import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BookingTicketPage;
import testcases.login.Login01_Verify_Login_Successfully;

public class Booking_01_HappyPath extends BaseTest {

    @Test(description = "Xác nhận luồng đặt vé thành công")
    public void verifyBookingSuccess() throws InterruptedException {
        // login trước khi mua vé (Gọi đến hàm autotest login)
        Login01_Verify_Login_Successfully login = new Login01_Verify_Login_Successfully();
        login.Login01_Login_Successfully();

        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://demo1.cybersoft.edu.vn/purchase/45056");
        BookingTicketPage bookingPage = new BookingTicketPage(driver);

        System.out.println("Step: Chọn ghế 17");
        bookingPage.selectSeat("17");

        System.out.println("Step: Click ĐẶT VÉ");
        bookingPage.clickOrder();

        System.out.println("Step: Xác nhận Dialog");
        bookingPage.confirmDialog();

        Assert.assertTrue(true);
    }
}