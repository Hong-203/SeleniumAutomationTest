package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;

    private By byTxtAccount = By.id("taiKhoan");
    private By byTxtPassword = By.id("matKhau");
    private By byTxtConfirmPassword = By.id("confirmPassWord");
    private By byTxtFullName = By.id("hoTen");
    private By byTxtEmail = By.id("email");
    private By byBtnRegister = By.xpath("//button[span[text()='Đăng ký']]");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterAccount(String account) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement txtAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtAccount));
        txtAccount.clear();
        txtAccount.sendKeys(account);
    }

    public void enterPassword(String password) {
        driver.findElement(byTxtPassword).clear();
        driver.findElement(byTxtPassword).sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        driver.findElement(byTxtConfirmPassword).clear();
        driver.findElement(byTxtConfirmPassword).sendKeys(password);
    }

    public void enterFullName(String fullName) {
        driver.findElement(byTxtFullName).clear();
        driver.findElement(byTxtFullName).sendKeys(fullName);
    }

    public void enterEmail(String email) {
        driver.findElement(byTxtEmail).clear();
        driver.findElement(byTxtEmail).sendKeys(email);
    }

    public void clickRegister() {
        driver.findElement(byBtnRegister).click();
    }

    // Kiểm tra Password Masking (TC_REG_SEC_01)
    public String getPasswordInputType() {
        return driver.findElement(byTxtPassword).getAttribute("type");
    }

    // Lấy thông báo lỗi dưới các trường (TC_REG_VAL_02)
    public String getFieldError(String fieldId) {
        try {
            // Tìm thẻ p hoặc span báo lỗi ngay sau input
            return driver.findElement(By.xpath("//div[input[@id='" + fieldId + "']]/following-sibling::p")).getText();
        } catch (Exception e) {
            return "";
        }
    }
}