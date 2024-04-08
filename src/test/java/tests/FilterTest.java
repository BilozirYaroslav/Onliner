package tests;

import framework.BaseTest;
import onliner.CataloguePage;
import onliner.MainPage;
import onliner.TVpage;
import org.testng.annotations.Test;

public class FilterTest extends BaseTest {

    @Test
     public void testFilter() {
         MainPage.navigateSection("Каталог");
         CataloguePage.cataloguePageVerification();
         CataloguePage.navigateMenu("Электроника");
         CataloguePage.navigateSubMenu(" Телевидение");
         CataloguePage.navigateCategory("Телевизоры");
         TVpage.TVpageVerification();

         TVpage.brandCheckBox(filter("brand"));
         TVpage.resolutionCheckBox(filter("resolution"));
         TVpage.diagonalCheckBox(filter("diagonalFrom"));
         TVpage.diagonalCheckBox(filter("diagonalTo"));
         TVpage.filterInputValue(filter("priceTo"));
     }

}
