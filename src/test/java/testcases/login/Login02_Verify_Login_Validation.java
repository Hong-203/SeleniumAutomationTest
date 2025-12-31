package testcases.login;

import base.BaseTest;
import drivers.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.dialog.CommonDialog;
import java.time.Duration;

public class Login02_Verify_Login_Validation extends BaseTest {

    @Test(description = "Kiểm tra các trường hợp lỗi đăng nhập (TC_LOGIN_VAL)")
    public void verifyLoginValidation() {
        WebDriver driver = DriverFactory.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Mở trang và vào form đăng nhập
        driver.get("https://demo1.cybersoft.edu.vn/");
        By byLnkLogin = By.xpath("//a[h3[text()='Đăng Nhập']]");
        wait.until(ExpectedConditions.elementToBeClickable(byLnkLogin)).click();

        LoginPage loginPage = new LoginPage(driver);
        CommonDialog dialog = new CommonDialog(driver);

        // --- CASE 1: Để trống tất cả các trường (TC_LOGIN_VAL_01) ---
        System.out.println("Running: TC_LOGIN_VAL_01 - Leave all fields empty");
        loginPage.enterAcount("");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        // Giả sử hệ thống báo lỗi ngay tại field hoặc dialog, ở đây check dialog theo chuẩn chung
        // Lưu ý: Nếu web dùng HTML5 validation (báo 'Please fill out this field'),
        // bạn có thể check thông qua thuộc tính 'validationMessage' của element.

        // --- CASE 2: Sai tài khoản hoặc mật khẩu (TC_LOGIN_VAL_04) ---
        System.out.println("Running: TC_LOGIN_VAL_04 - Invalid Account/Password");
        loginPage.enterAcount("tk_khong_ton_tai");
        loginPage.enterPassword("Pass123!");
        loginPage.clickLogin();

        String errorMsg = dialog.getTextMessage();
        Assert.assertEquals(errorMsg, "Tài khoản hoặc mật khẩu không đúng!", "Thông báo lỗi sai khi nhập thông tin không tồn tại!");

        // --- CASE 3: Nhập mật khẩu sai cho tài khoản đúng (TC_LOGIN_VAL_05) ---
        System.out.println("Running: TC_LOGIN_VAL_05 - Valid Account but Wrong Password");
        loginPage.enterAcount("soi01"); // Tài khoản đúng
        loginPage.enterPassword("SaiPass123");
        loginPage.clickLogin();

        String errorMsgWrongPass = dialog.getTextMessage();
        Assert.assertEquals(errorMsgWrongPass, "Tài khoản hoặc mật khẩu không đúng!", "Thông báo lỗi sai khi nhập sai mật khẩu!");

        // --- CASE 4: Kiểm tra độ dài/Ký tự đặc biệt (Nếu có trong TC_LOGIN_VAL_06) ---
        System.out.println("Running: TC_LOGIN_VAL_06 - Special characters in Account");
        loginPage.enterAcount("admin' OR '1'='1"); // SQL Injection test sơ bộ
        loginPage.enterPassword("123456");
        loginPage.clickLogin();

        String errorMsgSql = dialog.getTextMessage();
        Assert.assertEquals(errorMsgSql, "Tài khoản hoặc mật khẩu không đúng!", "Hệ thống không xử lý đúng ký tự đặc biệt!");
    }
}