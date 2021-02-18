
import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v89.network.Network;
import org.openqa.selenium.devtools.v89.network.model.Headers;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.apache.commons.codec.binary.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class BasiAuth {
    private WebDriver driver;


    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown(){
        this.driver.quit();
    }

    @Test
    public void testAuth(){
        DevTools devTools = ((ChromeDriver)this.driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("Authorization","Basic "+ new String( new Base64().encode("admin:admin".getBytes())));
        devTools.send(Network.setExtraHTTPHeaders(new Headers(hashMap)));
        this.driver.navigate().to("https://the-internet.herokuapp.com/basic_auth");
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
    }



}
