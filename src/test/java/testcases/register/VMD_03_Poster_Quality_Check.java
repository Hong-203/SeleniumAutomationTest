package testcases.movie;

import base.BaseTest;
import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MoviePage;

public class VMD_03_Poster_Quality_Check extends BaseTest {

    @Test(description = "Kiểm tra chất lượng hiển thị Poster phim (TC_VMD_UI_05)")
    public void verifyPosterQuality() throws InterruptedException {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://demo1.cybersoft.edu.vn/");
        MoviePage moviePage = new MoviePage(driver);

        moviePage.clickMovieDetail();
        Thread.sleep(5000);

        System.out.println("Step: Lấy link ảnh poster để kiểm tra chất lượng (TC_VMD_UI_05)");
        String posterSrc = moviePage.getPosterSource();
        System.out.println("Poster URL: " + posterSrc);

        // Logic verify: Nếu poster chứa 'default' hoặc 'placeholder' hoặc link lỗi
        boolean isQualityBad = posterSrc.contains("default-film.webp") || posterSrc.isEmpty();

        if(isQualityBad) {
            System.err.println("BUG FOUND: [TC_VMD_UI_05] Poster phim hiển thị ảnh mặc định hoặc chất lượng thấp!");
        }

        Thread.sleep(5000);
        Assert.assertFalse(isQualityBad, "Lỗi UI: Hình poster bị mờ hoặc không đúng chất lượng (MBS-42)!");
    }
}