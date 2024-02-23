package Onliner;

import Framework.Browser;
import org.testng.annotations.Test;
import java.text.ParseException;
public class FilterTest extends Browser {


    private static final String brandLocator = "//ul[@class='catalog-form__checkbox-list']//div[text()='%s']";
    private static final String resolutionLocator = "//ul[@class='catalog-form__checkbox-list']//div[text()='%s']";
    private static final String priceInputLocator = "//input[@placeholder='до']";
    private static final String diagonalFromLocator = "//ul//div[contains(text(),'%s')]";
    private static final String diagonalToLocator = "//ul//div[contains(text(),'%s')]";
    static String brand = "Samsung";
    static String resolution = "1920x1080 (Full HD)";
    static String priceTo = "1500";
    static String diagonalFrom = "40\"";
    static String diagonalTo = "50\"";


     @Test
     public void testFilter() throws ParseException {
         MainPage.navigateSection("Каталог");
         CataloguePage.navigateMenu("Электроника");
         CataloguePage.navigateSubMenu(" Телевидение");
         CataloguePage.navigateCategory("Телевизоры");

         TVpage.filterCheckBox(brandLocator, brand);
         TVpage.filterCheckBox(resolutionLocator, resolution);
         TVpage.filterCheckBox(diagonalFromLocator, diagonalFrom);
         TVpage.filterCheckBox(diagonalToLocator, diagonalTo);
         TVpage.filterInputValue(priceInputLocator, priceTo);

         TVpage.checkResult(TVpage.parentLocator, TVpage.childLocator);
     }

}
