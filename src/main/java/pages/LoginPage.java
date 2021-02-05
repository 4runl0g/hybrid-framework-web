package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LoginPage {

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//span[@id='spanDone']")
    List<WebElement> popup;

    @FindBy(how = How.XPATH, using = "//div[@class='accountDetailsLangDropDown']")
    List<WebElement> language;

    @FindBy(how = How.XPATH, using = "//*[@role='menuitem' and contains(string(), 'English')]")
    List<WebElement> englishMenu;

    @FindBy(how = How.XPATH, using = "//*[@role='menuitem' and contains(string(), 'हिंदी')]")
    List<WebElement> hindiMenu;

    @FindBy(how = How.XPATH, using = "//*[@role='menuitem' and contains(string(), 'Español')]")
    List<WebElement> spanishMenu;

    @FindBy(how = How.XPATH, using = "//div[@role='menu' and contains(string(), 'English')]")
    List<WebElement> dropdownmenu;

    @FindBy(how = How.XPATH, using = "//button[@id='SI_0005']")
    List<WebElement> continueBtn;

    @FindBy(how = How.XPATH, using = "//div[@class='childSupportLink']")
    List<WebElement> parentSupportLink;

    @FindBy(how = How.XPATH, using = "//div[text() ='Your parent account']")
    List<WebElement> parentaccountText;

    @FindBy(how = How.XPATH, using = "//div[@class = 'acrCheckCodeButton']")
    List<WebElement> createNewAcctBtn;

    //login page identifier
    public LoginPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    //Language drop down menu
    public String languageDropdownMenu() throws InterruptedException {
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Thread.sleep(1000);
        language.get(0).click();
        return dropdownmenu.get(0).getText();
    }

    //Select language from drop down menu by passing language
    public String selectLanguage(String lang) throws InterruptedException {
        switch (lang) {
            case "English": default:
                englishMenu.get(0).click();
                break;
            case "हिंदी":
                hindiMenu.get(0).click();
                break;
            case "Español":
                spanishMenu.get(0).click();
                break;
        }
        Thread.sleep(1000);
//        WebDriverWait wait = new WebDriverWait(driver,30);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(continueBtn.get(0).getText())));
        String param = continueBtn.get(0).getText();
        System.out.println(param);
        return param;
    }

    //pop-up on initial home screen loading
    public void popup() {
        driver.switchTo().frame("contentIframe");
        popup.get(0).click();
    }

    //Setup parent support link on login page
    public String setupParentSupportLink() throws InterruptedException {
        Thread.sleep(500);
        parentSupportLink.get(0).click();
        Thread.sleep(500);
        String popupText = parentaccountText.get(0).getText();
        return popupText;
    }

    //create new acct btn on login screen
    public void clickCreateNewAcct() {
        createNewAcctBtn.get(0).click();
    }

}
