package testcases.viewCinemaCluster;

import base.BaseTest;
import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ViewCinemaCluster;

public class ViewCinemaCluster_Successfully extends BaseTest {

    @Test(description = "Kiểm tra click menu và trạng thái active")
    public void verifyNavigationToBHDStar() {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://demo1.cybersoft.edu.vn/");

        ViewCinemaCluster cinemaCluster = new ViewCinemaCluster(driver);

        // Bước 1: Click menu và cuộn
        cinemaCluster.clickCinemaCluster();

        // Bước 2: Chờ 2 giây để hiệu ứng cuộn mượt (smooth scroll) hoàn tất
        try { Thread.sleep(3000); } catch (InterruptedException e) {}

        // Bước 3: Kiểm tra trạng thái Active
        boolean isActive = cinemaCluster.isMenuCinemaActive();
        System.out.println("Kết quả kiểm tra Active: " + isActive);

        // Bước 4: Kiểm tra hiển thị nội dung
        boolean isContentDisplayed = cinemaCluster.isCinemaSectionDisplayed();

        Assert.assertTrue(isActive, "Lỗi: Menu Cụm Rạp chưa chuyển sang trạng thái Active!");
        Assert.assertTrue(isContentDisplayed, "Lỗi: Nội dung rạp phim chưa hiển thị!");
    }
}
