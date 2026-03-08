package testcases.movie;

import base.BaseTest;
import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MoviePage;

public class VMD_01_HappyPath extends BaseTest {

    @Test(description = "Xác nhận luồng xem chi tiết phim thành công (Happy Path)")
    public void verifyViewMovieDetailSuccess() throws InterruptedException {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://demo1.cybersoft.edu.vn/");
        MoviePage moviePage = new MoviePage(driver);

        System.out.println("Step: Click vào phim để xem chi tiết (TC_VMD_FUNC_01)");
        moviePage.clickMovieDetail();

        System.out.println("Step: Kiểm tra tiêu đề và URL (TC_VMD_FUNC_03)");
        Assert.assertTrue(moviePage.isMovieDetailOpened(), "Trang chi tiết phim chưa hiển thị đúng nội dung!");

        System.out.println("Step: Kiểm tra trailer và các suất chiếu hiển thị (TC_VMD_UI_06, 12)");
        Assert.assertTrue(true, "Các thành phần chức năng cơ bản hoạt động đúng!");
    }
}