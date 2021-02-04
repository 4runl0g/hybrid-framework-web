package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//span[@id='spanDone']")
    List<WebElement> popup;

    @FindBy(how = How.XPATH, using = "//div[@class='accountDetailsLangDropDown']")
    WebElement language;

    public LoginPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void selectLanguage() {
        language.click();
    }

    public void popup() {
        driver.findElements(By.className("accountDetailsLangDropDown")).get(0).click();
//        popup.get(0).click();
    }

}
