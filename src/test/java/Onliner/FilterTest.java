package Onliner;

import Framework.Browser;
import org.testng.annotations.Test;
import java.text.ParseException;
public class FilterTest extends Browser {


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

         TVpage.brandCheckBox(brand);
         TVpage.resolutionCheckBox(resolution);
         TVpage.setPriceInput(priceTo);
         TVpage.setDiagonalFrom(diagonalFrom);
         TVpage.setDiagonalTo(diagonalTo);

         TVpage.checkResult(TVpage.parentLocator, TVpage.childLocator);
     }

}
