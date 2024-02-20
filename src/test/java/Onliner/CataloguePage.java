package Onliner;

import Framework.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.*;
public class CataloguePage extends Browser {

    private static final String menuLocator = "//ul[@class='catalog-navigation-classifier ']/li//span[text()='%s']";
    private static final String subMenuLocator = "//div[contains(text(), '%s')]";
    private static final String categoryLocator = "//div[contains(text(),'Телевидение')]//..//span[contains(text(),'%s')]";
    private static final String pageTV = "//h1[contains(text(),'Телевизоры')]";

    private static WebElement locatorByText(String locator, String text) {
        WebElement locatedElement = (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(locator, text))));
        return locatedElement;
    }

    public static void pageTvVerification() {
        boolean TV_Displayed = driver.findElement(By.xpath(pageTV)).isDisplayed();
        if (!TV_Displayed) {
            throw new IllegalStateException("This is not the TV page");
        }
    }
    public static void navigateMenu(String menuElement) {
        locatorByText(menuLocator, menuElement).click();
    }
    public static void navigateSubMenu(String subMenuElement) {
        locatorByText(subMenuLocator, subMenuElement).click();
    }
    public static void navigateCategory(String categoryElement) {
        locatorByText(categoryLocator, categoryElement).click();

        CataloguePage.pageTvVerification();
    }



}
