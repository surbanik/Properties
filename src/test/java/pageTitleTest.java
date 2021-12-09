import BaseTest.BaseTest;
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
public class pageTitleTest extends BaseTest {

    String expectedTitle;

    @Test
    public void kotuszkowoMainPageTitleTest(){
        expectedTitle = System.getProperty("title");
        assertThat(driver.getTitle(), equalTo(expectedTitle));
    }

}
