import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Frameshandling {

    static WebDriver driver;

    @BeforeClass
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/tinymce");
        System.out.println(driver.getCurrentUrl());
    }

    @Test
    public void testFrames() {
        // switching to the editor's frame
        driver.switchTo().frame("mce_0_ifr");
        WebElement defaultTextElement = driver.findElement(By.id("tinymce"));
        String defaultText = defaultTextElement.getText();
        // Clearing the text from editor
        defaultTextElement.clear();
        // Entering the text in editor
        defaultTextElement.sendKeys("I am in frame !!!!!");
        // Asserting the text
        Assert.assertNotEquals(defaultText, defaultTextElement.getText());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}