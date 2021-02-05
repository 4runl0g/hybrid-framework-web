import dataprovider.TestDataFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CreateAccountPage;
import pages.LoginPage;
import utils.JsonUtil;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class mypediaWebTest {

    private WebDriverManager driverManager;

    private String webURL = "https://www.mypedia.pearson.com/";
    public static final String LanguageValidationTestcase_JSON_FILE_PATH = System.getProperty("user.dir")
            + "/src/main/resources/MypediaWebTestcase.json";
    public static final String AcctCreationTestcase_JSON_FILE_PATH = System.getProperty("user.dir")
            + "/src/main/resources/AcctCreationTestcase.json";

    //Dataprovider to read testcase parameters and iterate for test suite
    @DataProvider(name = "languageVerifyData", parallel = true)
    public Iterator<Object[]> getTestcaseData(Method m) throws IOException {
        System.out.println(m.getName());
        return new JsonUtil().setTestData(LanguageValidationTestcase_JSON_FILE_PATH, m.getName());
    }

    //Dataprovider to read testcase parameters for account creation
    @DataProvider(name = "userAccountData", parallel = true)
    public Iterator<Object[]> getUserData(Method m) throws IOException {
        System.out.println(m.getName());
        return new JsonUtil().setTestData(AcctCreationTestcase_JSON_FILE_PATH, m.getName());
    }

    @BeforeTest
    public void launch() {
        driverManager.chromedriver().setup();
    }

    //Verify expected languages are available in drop down menu
    @Test(dataProvider = "languageVerifyData")
    public void verifyLanguage(TestDataFactory dataFactory) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get(webURL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        new LoginPage(driver).popup();
        String dropdownmenu = new LoginPage(driver).languageDropdownMenu();
        Assert.assertEquals(dataFactory.getLanguage(),dropdownmenu);
        driver.close();
    }

    //Validate Continue button for all different languages
    @Test(dataProvider = "languageVerifyData")
    public void continueBtnValidation(TestDataFactory dataFactory) throws InterruptedException {
        System.out.println(dataFactory.getLanguage()+"==>"+dataFactory.getContinueBtn());
        String expParam = dataFactory.getContinueBtn();
        WebDriver driver = new ChromeDriver();
        driver.get(webURL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        new LoginPage(driver).popup();
        new LoginPage(driver).languageDropdownMenu();
        String actualParam = new LoginPage(driver).selectLanguage(dataFactory.getLanguage());
        System.out.println(actualParam);
        Assert.assertEquals(actualParam,expParam);
        driver.close();
    }

    //Parent account creation using userData dataprovider
    @Test(dataProvider = "userAccountData")
    public void parentAcctCreation(TestDataFactory dataFactory) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get(webURL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        new LoginPage(driver).popup();
        String actualPopupText = new LoginPage(driver).setupParentSupportLink();
        System.out.println(actualPopupText);
        Assert.assertEquals("Your parent account",actualPopupText);
        new LoginPage(driver).clickCreateNewAcct();
        //chk Create Account btn is disabled
        Assert.assertEquals(new CreateAccountPage(driver).createAccountBtnisEnabled(),false);

        //enter firstname and chk Create Account btn is disabled
        new CreateAccountPage(driver).enterFirstName(dataFactory.getFirstname());
        Assert.assertEquals(new CreateAccountPage(driver).createAccountBtnisEnabled(),false);

        //enter lastname and chk Create Account btn is disabled
        new CreateAccountPage(driver).enterLastName(dataFactory.getLastname());
        Assert.assertEquals(new CreateAccountPage(driver).createAccountBtnisEnabled(),false);

        //enter email and chk Create Account btn is disabled
        new CreateAccountPage(driver).enterEmail(dataFactory.getEmail());
        Assert.assertEquals(new CreateAccountPage(driver).createAccountBtnisEnabled(),false);

        //enter username and chk Create Account btn is disabled
        new CreateAccountPage(driver).enterParentUsrField(dataFactory.getUsername());
        Assert.assertEquals(new CreateAccountPage(driver).createAccountBtnisEnabled(),false);

        //enter password and chk Create Account btn is disabled
        new CreateAccountPage(driver).enterParentPwdField(dataFactory.getPassword());
        Assert.assertEquals(new CreateAccountPage(driver).createAccountBtnisEnabled(),false);

        //enter confirm pwd and chk Create Account btn is disabled
        new CreateAccountPage(driver).enterConfirmPwdField(dataFactory.getPassword());
        Assert.assertEquals(new CreateAccountPage(driver).createAccountBtnisEnabled(),false);

        driver.close();
    }
}
