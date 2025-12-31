package testcases.register;

import base.BaseTest;
import drivers.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegisterPage;
import pages.dialog.CommonDialog;
import java.time.Duration;
import java.util.UUID;

public class Register_01_HappyPath extends BaseTest {

    @Test(description = "TC_REG_FUNC_01: Đăng ký thành công và kiểm tra Login")
    public void verifyRegisterSuccess() throws InterruptedException {
        WebDriver driver = DriverFactory.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        String account = "user_" + UUID.randomUUID().toString().substring(0, 8);
        String email = account + "@gmail.com";
        String pass = "Test123456@";

        driver.get("https://demo1.cybersoft.edu.vn/");
        Thread.sleep(5000);

        // Mở Form Đăng ký
        driver.findElement(By.xpath("//a[h3[text()='Đăng Ký']]")).click();
        RegisterPage registerPage = new RegisterPage(driver);
        Thread.sleep(5000);

        // Verify Password Masking (TC_REG_SEC_01)
        Assert.assertEquals(registerPage.getPasswordInputType(), "password", "Mật khẩu không được ẩn!");

        registerPage.enterAccount(account);
        registerPage.enterPassword(pass);
        registerPage.enterConfirmPassword(pass);
        registerPage.enterFullName("Automation Test");
        registerPage.enterEmail(email);

        Thread.sleep(5000); // Demo quan sát dữ liệu đã nhập
        registerPage.clickRegister();

        CommonDialog dialog = new CommonDialog(driver);
        Assert.assertEquals(dialog.getTextMessage(), "Đăng ký thành công", "Lỗi thông báo đăng ký!");
        dialog.waitDialogDisappear();
        Thread.sleep(5000);

        // Verify đăng nhập với tài khoản vừa tạo
        driver.findElement(By.xpath("//a[h3[text()='Đăng Nhập']]")).click();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterAcount(account);
        loginPage.enterPassword(pass);
        loginPage.clickLogin();

        Assert.assertEquals(dialog.getTextMessage(), "Đăng nhập thành công", "Không thể login sau đăng ký!");
        Thread.sleep(5000);
    }
}