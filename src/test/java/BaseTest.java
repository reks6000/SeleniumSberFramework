import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
    WebDriver driver;
    String website;
    String xpath;
    Wait<WebDriver> wait;
    Framework fw;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5, 1000);
        driver.manage().window().maximize();
        fw = new Framework(driver, wait);
    }

    @After
    public void tearDown() throws Exception {
//        sleep(1000);
        driver.quit();
    }
}
