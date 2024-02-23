package Onliner;

import Framework.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.Duration;

import static org.testng.Assert.*;

public class TVpage extends Browser {


    public static final String parentLocator = "//div[@class='catalog-form__offers-unit catalog-form__offers-unit_primary']";
    public static final String childLocator = "//div/a[contains(text(),'Телевизор')]";
    public static final String filterItem = "//div[@class='catalog-form__tag-item'][4]";
    private static final String title = "//h1";
    private static final String price = "//div[@class='offers-description__price-group']//a";
    private static final String resolution = "//td[contains(text(), 'Разрешение')]/following-sibling::td//span";
    private static final String diagonal = "//td[contains(text(),'Диагональ экрана')]/following-sibling::td/span";


    private static WebElement locator(String locator) {
        WebElement locatedElement = (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(locator))));
        return locatedElement;
    }
    private static WebElement locatorByText(String locator, String text) {
        WebElement locatedElement = (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(locator, text))));
        return locatedElement;
    }
    public static void filterCheckBox(String locator, String value) {
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click()", locatorByText(locator, value));
    }
    public static void filterInputValue(String locator, String value) {
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].scrollIntoView();", locator(locator));
        locator(locator).sendKeys(value);
    }

    public static void checkResult(String parent, String child) throws ParseException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", locator(title));

        boolean filterCountCorrect = locator(filterItem).isDisplayed();
        assertTrue(filterCountCorrect);

        driver.navigate().refresh();

            int count = (driver.findElements(By.xpath(parent))).size();

            for (int i = 1; i <= count; i++) {

                locator(parent + "[" + i + "]" + child).click();

                assertTrue((locator(title)).getText().contains(FilterTest.brand));

                assertEquals((locator(resolution)).getText(), FilterTest.resolution);

                int diagonalFrom = DecimalFormat.getNumberInstance().parse(FilterTest.diagonalFrom).intValue();
                int diagonalTo = DecimalFormat.getNumberInstance().parse(FilterTest.diagonalTo).intValue();
                int diagonalResult = DecimalFormat.getNumberInstance().parse(locator(diagonal).getText()).intValue();

                assertTrue(diagonalFrom <= diagonalResult && diagonalResult <= diagonalTo);

                double doubleOfPrice = DecimalFormat.getNumberInstance().parse(locator(price).getText()).doubleValue();
                assertTrue(Double.valueOf(FilterTest.priceTo) >= doubleOfPrice);

                driver.navigate().back();

            }

        }
    }



