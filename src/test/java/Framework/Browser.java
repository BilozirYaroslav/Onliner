package Framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
public class Browser {
    public static WebDriver driver;
    public static Properties prop = new Properties();
    public static FileReader fr_config;

    @BeforeTest
    public void setUp() throws IOException{

        if (driver == null) {
            fr_config = new FileReader(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            prop.load(fr_config);
        }

        if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get(prop.getProperty("testUrl"));
            driver.manage().window().maximize();
        }
    }
    @AfterTest
    public void tearDown () {
        driver.quit();
    }
}
