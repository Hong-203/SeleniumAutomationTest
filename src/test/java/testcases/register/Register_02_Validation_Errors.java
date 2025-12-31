package testcases.register;

import base.BaseTest;
import drivers.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegisterPage;
import pages.dialog.CommonDialog;

public class Register_02_Validation_Errors extends BaseTest {

    @Test(description = "Kiểm tra các lỗi Validate dữ liệu đăng ký")
    public void verifyRegisterValidation() throws InterruptedException {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://demo1.cybersoft.edu.vn/");
        driver.findElement(By.xpath("//a[h3[text()='Đăng Ký']]")).click();

        RegisterPage registerPage = new RegisterPage(driver);
        CommonDialog dialog = new CommonDialog(driver);

        // --- CASE 1: Để trống tất cả (TC_REG_VAL_01) ---
        System.out.println("Check TC_REG_VAL_01: Empty fields");
        registerPage.clickRegister();
        Thread.sleep(5000);
        // Kiểm tra thông báo dưới trường tài khoản
        Assert.assertTrue(registerPage.getFieldError("taiKhoan").contains("bắt buộc"), "Thiếu validate trống trường!");

        // --- CASE 2: Mật khẩu không khớp (TC_REG_VAL_06) ---
        System.out.println("Check TC_REG_VAL_06: Password mismatch");
        registerPage.enterAccount("test_val_06");
        registerPage.enterPassword("123456");
        registerPage.enterConfirmPassword("654321");
        registerPage.clickRegister();
        Thread.sleep(5000);
        Assert.assertTrue(registerPage.getFieldError("confirmPassWord").contains("không khớp"), "Lỗi không khớp!");

        // --- CASE 3: Email đã tồn tại (TC_REG_VAL_08) ---
        System.out.println("Check TC_REG_VAL_08: Email already exists");
        registerPage.enterAccount("user_new_999");
        registerPage.enterPassword("Pass123!");
        registerPage.enterConfirmPassword("Pass123!");
        registerPage.enterFullName("User Test");
        registerPage.enterEmail("Soi05@gmail.com"); // Email đã có trong hệ thống
        registerPage.clickRegister();

        Thread.sleep(5000);
        Assert.assertEquals(dialog.getTextMessage(), "Email đã tồn tại!", "Hệ thống không chặn email trùng!");
    }
}