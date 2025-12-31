package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    private By byTxtAccountLogin = By.id("taiKhoan");
    private By byTxtPasswordLogin = By.id("matKhau");
    private By byBtnLogin = By.xpath("//button[span[text()='Đăng nhập']]");
    private By byChkRemember = By.name("remember");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // --- Các hàm kiểm tra thuộc tính (Bổ sung cho Test Case UI/SEC) ---

    public boolean isAccountInputDisplayed() {
        return driver.findElement(byTxtAccountLogin).isDisplayed();
    }

    public boolean isPasswordInputDisplayed() {
        return driver.findElement(byTxtPasswordLogin).isDisplayed();
    }

    public boolean isRememberCheckboxDisplayed() {
        return driver.findElement(byChkRemember).isDisplayed();
    }

    public String getPasswordInputType() {
        // Trả về "password" nếu đang ẩn dấu chấm (TC_LOGIN_SEC_01)
        return driver.findElement(byTxtPasswordLogin).getAttribute("type");
    }

    // --- Các hàm hành động ---

    public void enterAcount(String account) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement txtAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtAccountLogin));
        txtAccount.clear(); // Xóa trước khi nhập để đảm bảo UX
        txtAccount.sendKeys(account);
    }

    public void enterPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtPasswordLogin));
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }

    public void clickLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement btnLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(byBtnLogin));
        btnLogin.click();
    }

    public void clickRemember() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement chkRemember = wait.until(ExpectedConditions.presenceOfElementLocated(byChkRemember));
        chkRemember.click();
    }
}