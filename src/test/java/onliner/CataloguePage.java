package onliner;

import org.openqa.selenium.By;

public class CataloguePage extends MainPage {

    private static final String catalogue = "//h1[@class='catalog-navigation__title' and text()='Каталог']";
    private static final String menuLocator = "//ul[@class='catalog-navigation-classifier']/li//span[contains(text(), '%s')]";
    private static final String subMenuLocator = "//div[contains(text(), '%s')]";
    private static final String categoryLocator = "//div[contains(text(),'Телевидение')]//..//span[contains(text(),'%s')]";



    public static void cataloguePageVerification() {
        boolean CatalogueDisplayed = driver.findElement(By.xpath(catalogue)).isDisplayed();
        if (!CatalogueDisplayed) {
            throw new IllegalStateException("This is not Catalog page");
        }
    }
    public static void navigateMenu(String menuElement) {
        locator(menuLocator, menuElement).click();
    }
    public static void navigateSubMenu(String subMenuElement) {
        locator(subMenuLocator, subMenuElement).click();
    }
    public static void navigateCategory(String categoryElement) {
        locator(categoryLocator, categoryElement).click();
    }

}
