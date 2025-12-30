package testcases.movie;

import base.BaseTest;
import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MoviePage;

public class ViewMovieDetail_Successfully extends BaseTest {

    @Test(description = "Kiểm tra xem chi tiết phim The Gentlemen")
    public void verifyViewMovieDetail() {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://demo1.cybersoft.edu.vn/");

        MoviePage moviePage = new MoviePage(driver);

        // Bước 1: Click vào phim
        moviePage.clickMovieDetail();

        // Bước 2: Kiểm tra URL và nội dung trang chi tiết
        System.out.println("Đang kiểm tra chuyển trang...");

        // Kiểm tra xem URL có chứa mã phim (8898) như trong HTML bạn gửi không
        Assert.assertTrue(driver.getCurrentUrl().contains("8898"), "URL không chứa mã phim chính xác!");

        // Kiểm tra nội dung trang chi tiết đã hiển thị chưa
        Assert.assertTrue(moviePage.isMovieDetailOpened(), "Trang chi tiết phim chưa hiển thị đúng nội dung!");
    }
}