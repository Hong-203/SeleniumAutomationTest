package helpers;

import drivers.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

public class LoginHelper {

    public static void login(String account, String password) {

        WebDriver driver = DriverFactory.getDriver();

        driver.get("https://demo1.cybersoft.edu.vn/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // mở form login
        wait.until(ExpectedConditions
                        .elementToBeClickable(By.xpath("//a[h3[text()='Đăng Nhập']]")))
                .click();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterAcount(account);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }
}