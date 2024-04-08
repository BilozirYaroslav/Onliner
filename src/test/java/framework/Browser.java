package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Browser {
    public static WebDriver driver;
    public static Properties prop = new Properties();
    public static FileReader fr_config;

    public static void setUp() {


        try {
            fr_config = new FileReader(System.getProperty("user.dir") + "/src/test/resources/config.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            prop.load(fr_config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String driverProperty = prop.getProperty("browser");

        switch (driverProperty) {

            case "chrome":
                WebDriverManager.chromedriver().clearDriverCache().setup();
                driver = new ChromeDriver();
                break;

        }
        switch (driverProperty){

            case "firefox":
                WebDriverManager.firefoxdriver().clearDriverCache().setup();
                driver = new FirefoxDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(prop.getProperty("testUrl"));
            driver.manage().window().maximize();

    }

}
