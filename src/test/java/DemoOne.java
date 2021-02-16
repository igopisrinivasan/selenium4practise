import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DemoOne {

    @Test
    public void test() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://omayo.blogspot.com/");
        driver.findElement(By.name("q")).sendKeys("QAFox.com");;
        driver.findElement(By.cssSelector("input[value='Search']")).click();
        Thread.sleep(5000);
        driver.quit();
    }
}
