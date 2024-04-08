package framework;
import java.io.*;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.dom4j.DocumentException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest extends Browser {

    @BeforeTest
    public void start() {
        BaseTest.setUp();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    File inputFile = new File(System.getProperty("user.dir") + "/src/test/resources/data.xml");
    SAXReader saxReader = new SAXReader();
    Document document;

    {
        try {
            document = saxReader.read(inputFile);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
    public String filter(String node) {
        String s = document.selectSingleNode("//tvPages/filters/" + node).getText();
        return s;
    }
}