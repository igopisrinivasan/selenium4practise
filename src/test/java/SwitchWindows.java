import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class SwitchWindows {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(60));
        this.driver.manage().window().maximize();
    }

    @Test
    public void testSwitchingTab(){
        this.driver.navigate().to("https://the-internet.herokuapp.com/windows");
        WebElement lnkClickHere = this.driver.findElement(By.cssSelector("div.example a"));
        this.wait.until((d)-> lnkClickHere.isEnabled() && lnkClickHere.isDisplayed());
        System.out.println("Parent widnow title is :" + this.driver.getTitle());
        JavascriptExecutor jse = (JavascriptExecutor)this.driver;
        jse.executeScript("arguments[0].setAttribute('target','_self');",lnkClickHere);
        lnkClickHere.click();
        System.out.println("Title after switching window :"+this.driver.getTitle());
        jse.executeScript("history.go(-1)");
        System.out.println("Parent Window title is:"+ this.driver.getTitle());
    }

    @AfterClass
    public void tearDown(){
        this.driver.quit();
    }
}
