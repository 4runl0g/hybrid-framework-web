import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.JsonUtil;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

public class mypediaWebTest {

    private WebDriverManager driverManager;
    private WebDriver driver;

    public static final String Testcase_JSON_FILE_PATH = System.getProperty("user.dir")
            + "/src/main/java/dataprovider/MypediaWebTestcase.json";

    //Dataprovider to read testcase parameters and iterate for test suite
    @DataProvider(name = "getData", parallel = true)
    public Iterator<Object[]> getTestcaseData(Method m) throws IOException {
        return new JsonUtil().setTestcaseData(Testcase_JSON_FILE_PATH);
    }

    @BeforeTest
    public void launch() {
        driverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test()
    public void mypediaWebTest() throws InterruptedException {
        driver.get("https://www.mypedia.pearson.com/");
//        Thread.sleep(10000);
        WebDriverWait wait=new WebDriverWait(driver, 20);
        new LoginPage(driver).popup();
//        new LoginPage(driver).selectLanguage();
        driver.close();
    }
}
