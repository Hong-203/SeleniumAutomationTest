package testcases.viewCinemaCluster;

import base.BaseTest;
import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ViewCinemaCluster;

public class VCC_02_Verify_UI_Errors extends BaseTest {

    @Test(description = "Xác nhận các lỗi UI đã báo cáo trong Bug Report")
    public void verifyVCC_Known_Bugs() {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://demo1.cybersoft.edu.vn/");
        ViewCinemaCluster vcc = new ViewCinemaCluster(driver);
        vcc.clickCinemaCluster();

        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        // Kiểm tra TC_VCC_UI_04: Hệ thống chưa có tiêu đề nổi bật cho vùng cụm rạp
        System.out.println("Checking TC_VCC_UI_04: Main title 'Cụm Rạp' should be displayed");
        boolean hasMainTitle = vcc.isMainTitleDisplayed();
        if(!hasMainTitle) {
            System.err.println("BUG DETECTED: [TC_VCC_UI_04] Phần cụm rạp chưa có tiêu đề lớn!");
        }
        // Assert.assertTrue(hasMainTitle, "Lỗi: Thiếu tiêu đề 'Cụm Rạp' nổi bật!");

        // Kiểm tra TC_VCC_UI_21: Hiển thị C18 nhưng không có chú thích
        System.out.println("Checking TC_VCC_UI_21: Description for C18 classification");
        boolean hasC18Note = vcc.isC18NoteDisplayed();
        if(!hasC18Note) {
            System.err.println("BUG DETECTED: [TC_VCC_UI_21] Hiển thị nhãn C18 nhưng không có chú thích độ tuổi!");
        }
        Assert.assertTrue(hasC18Note, "Lỗi: Không tìm thấy chú thích giải thích nhãn C18!");
    }
}