package Onliner;

import Framework.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.*;

public class MainPage extends Browser {

    private static final String sectionLocator = "//div[@class='b-top-menu']//li//span[text()='%s']";
    public static final String cookies = "//button//p[contains(text(), 'Соглашаюсь')]";
    private static final String catalog = "//div[@class='catalog-navigation__title' and text()='Каталог']";

    private static WebElement locatorByText(String locator, String text) {
        WebElement locatedElement = (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(locator, text))));
        return locatedElement;
    }

    public static void catalogPageVerification(){
        boolean catalogDisplayed = driver.findElement(By.xpath(catalog)).isDisplayed();
        if (!catalogDisplayed){
            throw new IllegalStateException("This is not the Catalogue page");
        }
    }

    public static void acceptCookies(String cookies){
        WebElement buttonLocator = (new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(cookies))));
        buttonLocator.click();
    }

    public static void navigateSection(String sectionElement) {

        locatorByText(sectionLocator, sectionElement).click();
        boolean cookiesIs = driver.findElement(By.xpath(cookies)).isDisplayed();
        if (cookiesIs){
            acceptCookies(cookies);
        }
        MainPage.catalogPageVerification();

    }


}
