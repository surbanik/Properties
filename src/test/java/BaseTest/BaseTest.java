package BaseTest;

import configuration.BrowserEnvirement;
import configuration.EnvirementProperty;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    private static BrowserEnvirement browserEnvirement;
    private static EnvirementProperty envirementProperty;

    protected WebDriver driver;


    @BeforeAll
    static void setDriver(){
        envirementProperty = EnvirementProperty.getInstance();
        browserEnvirement = new BrowserEnvirement();
    }

    @BeforeEach
    void setup(){
        driver = browserEnvirement.getDriver();
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

}
