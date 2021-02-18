import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class DemoOne {
    private FirefoxDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.firefoxdriver().setup();
        this.driver = new FirefoxDriver();
        this.driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown(){
        this.driver.quit();
    }

    @Test
    public void test() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://omayo.blogspot.com/");
        driver.findElement(By.name("q")).sendKeys("QAFox.com");;
        driver.findElement(By.cssSelector("input[value='Search']")).click();
        Thread.sleep(5000);
        driver.quit();
    }


    @Test
    public void screenshotTest() throws IOException {
        driver.navigate().to("https://www.swtestacademy.com");
        WebElement logo = driver.findElement(By.className("fusion-logo-link"));
        File file = logo.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("logo.png"));
    }

    @Test
    public void openNewTab(){
        driver.navigate().to("https://www.google.com");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://www.swtestacademy.com");
    }

    @Test
    public void openNewWindow(){
        driver.navigate().to("https://www.google.com");
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.navigate().to("https://www.swtestacademy.com");
    }
    @Test
    public void location(){
        driver.navigate().to("https://www.swtestacademy.com");
        WebElement logo = driver.findElement(By.className("fusion-logo-link"));
        System.out.println("Height is "+logo.getRect().getDimension().getHeight());
        System.out.println("Width is "+logo.getRect().getDimension().getWidth());
        System.out.println("Location X is "+logo.getRect().getX());
        System.out.println("Location Y is "+logo.getRect().getY());
    }

    @Test
    public void sampleTC(){
        driver.get("https://demoqa.com/login");
        //Maximizing window
        driver.manage().window().maximize();
        //Retrieving web page title
        String title = driver.getTitle();
        System.out.println("The page title is : " +title);

        //Locating web element
        WebElement uName = driver.findElement(By.xpath("//*[@id='userName']"));
        WebElement pswd = driver.findElement(By.xpath("//*[@id='password']"));
        WebElement loginBtn = driver.findElement(By.xpath("//*[@id='login']"));

        //Peforming actions on web elements
        uName.sendKeys("testuser");
        pswd.sendKeys("Password@123");
        loginBtn.click();
        try {
            //Locating web element
            WebElement logoutBtn = driver.findElement(By.xpath("//div[@class='text-right col-md-5 col-sm-12']//button[@id='submit']"));
            //Validating presence of element
            if(logoutBtn.isDisplayed()){
                //Performing action on web element
                logoutBtn.click();
                System.out.println("LogOut Successful!");
            }
        }
        catch (Exception e) {
            System.out.println("Incorrect login....");
        }
    }
}
