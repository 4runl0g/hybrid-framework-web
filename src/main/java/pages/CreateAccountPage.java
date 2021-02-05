package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CreateAccountPage {

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//div[text() ='Set up parent support']")
    List<WebElement> createAcctLandingScreen;

    @FindBy(how = How.XPATH, using = "//*[text() = 'CREATE ACCOUNT']")
    List<WebElement> createAcctBtn;

    @FindBy(how = How.XPATH, using = "//input[contains(@id,'Firstname')]")
    List<WebElement> firstNameField;

    @FindBy(how = How.XPATH, using = "//input[contains(@id,'Lastname')]")
    List<WebElement> lastNameField;

    @FindBy(how = How.XPATH, using = "//input[contains(@id,'Emailaddress')]")
    List<WebElement> emailField;

    @FindBy(how = How.XPATH, using = "//input[contains(@id,'Createparentusername')]")
    List<WebElement> parentUsrField;

    @FindBy(how = How.XPATH, using = "//input[contains(@id,'Createparentpassword')]")
    List<WebElement> parentPwdField;

    @FindBy(how = How.XPATH, using = "//input[contains(@id,'Confirmpassword')]")
    List<WebElement> confirmPwdField;

    //Create acct page identifier
    public CreateAccountPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
        createAcctLandingScreen.get(0).isDisplayed();
    }

    //return boolean value to chk create acct btn is enabled
    public boolean createAccountBtnisEnabled() {
        return createAcctBtn.get(0).isSelected();
    }

    //firstname field in create acct screen
    public void enterFirstName(String firstname) {
        firstNameField.get(0).sendKeys(firstname);
    }

    //lastname field in create acct screen
    public void enterLastName(String lastname) {
        lastNameField.get(0).sendKeys(lastname);
    }

    //email field in create acct screen
    public void enterEmail(String email) {
        emailField.get(0).sendKeys(email);
    }

    //parent uesrname field in create acct screen
    public void enterParentUsrField(String username) {
        parentUsrField.get(0).sendKeys(username);
    }

    //parents pwd field in create acct screen
    public void enterParentPwdField(String password) {
        parentPwdField.get(0).sendKeys(password);
    }

    //confirm pwd field in create acct screen
    public void enterConfirmPwdField(String confirmPwd) {
        confirmPwdField.get(0).sendKeys(confirmPwd);
    }

}
