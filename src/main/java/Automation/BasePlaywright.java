package Automation;

import com.google.gson.JsonObject;
import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Paths;

public class BasePlaywright {
    public Playwright playwright;
    public Page page;
    public Browser browser;

    @Parameters("url")
    @BeforeSuite
    public void firstSampleTest(String url) throws UnsupportedEncodingException {
        Playwright playwright = Playwright.create();
        JsonObject capabilities = new JsonObject();
        JsonObject ltOptions = new JsonObject();

        String user = "siva9kolli";
        String accessKey = "";

        capabilities.addProperty("browsername", "pw-chromium"); // Browsers allowed: `Chrome`, `MicrosoftEdge`, `pw-chromium`, `pw-firefox` and `pw-webkit`
        capabilities.addProperty("browserVersion", "latest");
        ltOptions.addProperty("platform", "Windows 10");
        ltOptions.addProperty("name", "Playwright Test");
        ltOptions.addProperty("build", "Playwrite Testing in Java");
        ltOptions.addProperty("user", user);
        ltOptions.addProperty("accessKey", accessKey);
        capabilities.add("LT:Options", ltOptions);

        BrowserType chromium = playwright.chromium();
        String caps = URLEncoder.encode(capabilities.toString(), "utf-8");
        String cdpUrl = "wss://cdp.lambdatest.com/playwright?capabilities=" + capabilities;
        browser = chromium.connect(cdpUrl);
        Page page = browser.newPage();

        page.navigate(url);
//        Locator locator = page.locator("#login-button");
//        locator.click();
    }

    @AfterSuite
    public void killServer(){
        browser.close();
    }
}
