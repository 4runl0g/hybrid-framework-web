package utils;

import dataprovider.TestDataFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

public class JsonUtil {

    //JSON file reader to iterate using dataprovider
    public Iterator<Object[]> setTestData(String jsonFilePath, String testName) throws IOException {
        Collection<Object[]> provider = new ArrayList<Object[]>();
        File file = new File(jsonFilePath);
        String content = FileUtils.readFileToString(new File(file.getCanonicalPath()), "utf-8");
        JSONObject object = new JSONObject(content);
        JSONArray testSuite = new JSONArray(object.get("TestCases").toString());
        for (int i = 0; i < testSuite.length(); i++) {
            JSONObject testCase = (JSONObject) testSuite.get(i);
            try {
                if (testCase.getString("testCaseName").equalsIgnoreCase(testName) && testCase.getBoolean("execute")) {
                    TestDataFactory dataFactory = new TestDataFactory();
                    dataFactory.setTestcaseParameters(testCase.getJSONObject("testCaseParameters"));
                    if (dataFactory.getTestcaseParameters().has("firstname")) {
                        dataFactory.setFirstname(dataFactory.getTestcaseParameters().getString("firstname"));
                        dataFactory.setLastname(dataFactory.getTestcaseParameters().getString("lastname"));
                        dataFactory.setEmail(dataFactory.getTestcaseParameters().getString("email"));
                        dataFactory.setUsername(dataFactory.getTestcaseParameters().getString("username"));
                        dataFactory.setPassword(dataFactory.getTestcaseParameters().getString("password"));
                    }
                    if (dataFactory.getTestcaseParameters().has("language")) {
                        String langParam = testCase.getJSONObject("testCaseParameters").getString("language");
                        String continueBtnParam = testCase.getJSONObject("testCaseParameters").getString("continueBtn");
                        dataFactory.setLanguage(langParam);
                        dataFactory.setContinueBtn(continueBtnParam);
                    }
                    provider.add(new Object[]{dataFactory});
                }
            } catch (Exception e) {
                System.out.print(e);
            }
        }
        return provider.iterator();
    }
}
