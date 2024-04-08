package onliner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
public class TVpage extends CataloguePage {
    private static final String brandLocator = "//ul[@class='catalog-form__checkbox-list']//div[text()='%s']";
    private static final String resolutionLocator = "//ul[@class='catalog-form__checkbox-list']//div[text()='%s']";
    private static final String priceInputLocator = "//input[@placeholder='до']";
    private static final String diagonalLocator = "//ul//div[contains(text(),'%s')]";
    private static final String pageTV = "//h1[contains(text(),'Телевизоры')]";



    public static void TVpageVerification() {
        boolean TV_Displayed = driver.findElement(By.xpath(pageTV)).isDisplayed();
        if (!TV_Displayed) {
            throw new IllegalStateException("This is wrong page");
        }
    }


    public static void brandCheckBox(String value) {
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click()", locator(brandLocator, value));
    }

    public static void resolutionCheckBox(String value) {
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click()", locator(resolutionLocator, value));
    }

    public static void diagonalCheckBox(String value) {
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click()", locator(diagonalLocator, value));
    }
    public static void filterInputValue(String value) {
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].scrollIntoView()", locator(priceInputLocator));
        locator(priceInputLocator).sendKeys(value);
    }

}



