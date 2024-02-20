package Onliner;

import Framework.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.*;
import static org.testng.Assert.*;

public class TVpage extends Browser {


    private static final String brandLocator = "//ul[@class='catalog-form__checkbox-list']//div[text()='%s']";
    private static final String resolutionLocator = "//ul[@class='catalog-form__checkbox-list']//div[text()='%s']";
    private static final String priceInput = "//input[@placeholder='до']";
    private static final String diagonalFrom = "//ul//div[contains(text(),'%s')]";
    private static final String diagonalTo = "//ul//div[contains(text(),'%s')]";
    public static final String parentLocator = "//div[@class='catalog-form__offers-unit catalog-form__offers-unit_primary']";
    public static final String childLocator = "//div/a[contains(text(),'Телевизор')]";
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

    public static void brandCheckBox(String brand) {
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click()", locatorByText(brandLocator, brand));

    }
    public static void resolutionCheckBox(String resolution) {
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click()", locatorByText(resolutionLocator, resolution));
    }
    public static void setPriceInput(String value) {
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].scrollIntoView();", locator(priceInput));
        locator(priceInput).sendKeys(value);
    }
    public static void setDiagonalFrom(String value) {
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click()", locatorByText(diagonalFrom, value));
    }
    public static void setDiagonalTo(String value) {
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click()", locatorByText(diagonalTo, value));
    }

    public static void checkResult(String parent, String child) throws ParseException{

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", locator(title));

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        driver.navigate().refresh();

          int count = (driver.findElements(By.xpath(parent))).size();

         System.out.println(count);
            for (int i = 1; i <= count; i++) {

                locator(parent + "[" + i + "]" + child).click();

                assertTrue((locator(title)).getText().contains(FilterTest.brand));

                assertEquals((locator(resolution)).getText(), FilterTest.resolution);

                int diagonalFrom = DecimalFormat.getNumberInstance().parse(FilterTest.diagonalFrom).intValue();
                int diagonalTo = DecimalFormat.getNumberInstance().parse(FilterTest.diagonalTo).intValue();
                int diagonalResult = DecimalFormat.getNumberInstance().parse(locator(diagonal).getText()).intValue();

                assertTrue(diagonalFrom < diagonalResult && diagonalResult < diagonalTo);

                double doubleOfPrice = DecimalFormat.getNumberInstance().parse(locator(price).getText()).doubleValue();

                assertTrue(Double.valueOf(FilterTest.priceTo) >= doubleOfPrice);

                driver.navigate().back();

            }

        }
    }


