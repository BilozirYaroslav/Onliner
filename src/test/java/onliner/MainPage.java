package onliner;

import framework.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPage extends BaseTest {
    private static final String sectionLocator = "//div[@class='b-top-menu']//li//span[text()='%s']";


    public static WebElement locator(String locator) {
        WebElement locatedElement = driver.findElement(By.xpath(String.format(locator)));
        return locatedElement;
    }

    public static WebElement locator(String locator, String text) {
        WebElement locatedElement = driver.findElement(By.xpath(String.format(locator, text)));
        return locatedElement;
    }

    public static void navigateSection(String sectionElement) {
        locator(sectionLocator, sectionElement).click();
    }


}
