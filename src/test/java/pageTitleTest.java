import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Execution(ExecutionMode.CONCURRENT)
public class pageTitleTest {

    private WebDriver driver;
    String expectedTitle;

    @BeforeAll
    static void setDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://www.onet.pl"})
    @Tag ("Regression")
    @Tag ("onet")
    public void onetMainPageTitleTest(String pageAdress){
        expectedTitle = "Onet – Jesteś na bieżąco";
        driver.get(pageAdress);
        assertThat(driver.getTitle(), equalTo(expectedTitle));
    }

    @ParameterizedTest
    @ValueSource(strings = {"http://kotuszkowo.pl"})
    @Tag ("Regression")
    @Tag ("kotuszkowo")
    public void kotuszkowoMainPageTitleTest(String pageAdress){
        expectedTitle = "Kotuszkowo- blog o kotach";
        driver.get(pageAdress);
        assertThat(driver.getTitle(), equalTo(expectedTitle));
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://www.filmweb.pl"})
    @Tag ("Regression")
    @Tag ("filmweb")
    public void filmwebMainPageTitleTest(String pageAdress){
        expectedTitle = "Filmweb - filmy takie jak Ty!";
        driver.get(pageAdress);
        assertThat(driver.getTitle(), equalTo(expectedTitle));
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://www.selenium.dev/documentation/en/webdriver/"})
    @Tag ("Regression")
    @Tag ("selenium")
    public void seleniumMainPageTitleTest(String pageAdress){
        expectedTitle = "WebDriver | Selenium";
        driver.get(pageAdress);
        assertThat(driver.getTitle(), equalTo(expectedTitle));
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://siiportal.sii.pl/"})
    @Tag ("Regression")
    @Tag ("siiportal")
    public void siiPortalMainPageTitleTest(String pageAdress){
        expectedTitle = "Logowanie na koncie";
        driver.get(pageAdress);
        assertThat(driver.getTitle(), equalTo(expectedTitle));
    }
}
