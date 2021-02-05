package dataprovider;

import org.json.JSONObject;

public class TestDataFactory {

    public String language;
    public String continueBtn;
    public String firstname;
    public String lastname;
    public String email;
    public String username;
    public String password;

    public JSONObject getTestcaseParameters() {
        return testcaseParameters;
    }

    public void setTestcaseParameters(JSONObject testcaseParameters) {
        this.testcaseParameters = testcaseParameters;
    }

    public JSONObject testcaseParameters;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getContinueBtn() {
        return continueBtn;
    }

    public void setContinueBtn(String continueBtn) {
        this.continueBtn = continueBtn;
    }


}
