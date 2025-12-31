package testcases.movie;

import base.BaseTest;
import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MoviePage;

public class VMD_02_UI_UX_Bug_Verification extends BaseTest {

    @Test(description = "Xác nhận các lỗi giao diện và điều hướng (MBS-40, MBS-41)")
    public void verifyMovieDetailUIBugs() throws InterruptedException {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://demo1.cybersoft.edu.vn/");
        MoviePage moviePage = new MoviePage(driver);

        moviePage.clickMovieDetail();
        Thread.sleep(5000);

        // Kiểm tra TC_VMD_UI_03: Thiếu Header điều hướng
        System.out.println("Checking TC_VMD_UI_03: Navigation Header display");
        boolean hasHeader = moviePage.isHeaderDisplayed();
        if(!hasHeader) {
            System.err.println("BUG FOUND: [TC_VMD_UI_03] Trang chi tiết phim thiếu Header điều hướng!");
        }

        // Kiểm tra TC_VMD_UI_04: Thiếu Footer thông tin
        System.out.println("Checking TC_VMD_UI_04: Footer display");
        boolean hasFooter = moviePage.isFooterDisplayed();
        if(!hasFooter) {
            System.err.println("BUG FOUND: [TC_VMD_UI_04] Trang chi tiết phim thiếu Footer thông tin!");
        }

        Thread.sleep(5000);
        // Assert để sinh Screenshot lỗi cho báo cáo
        Assert.assertTrue(hasHeader && hasFooter, "Lỗi UI: Thiếu các thành phần điều hướng cơ bản (Header/Footer)!");
    }
}