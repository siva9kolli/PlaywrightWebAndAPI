import com.google.gson.JsonObject;
import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.net.URLEncoder;

public class PlayWrightTest {
    @Test
    public void sampleTest() {
        try (Playwright playwright = Playwright.create()) {
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
            Browser browser = chromium.connect(cdpUrl);
            Page page = browser.newPage();
            try {
                page.navigate("https://www.saucedemo.com/");
                Locator locator = page.locator("#login-button");
                locator.click();


            } catch (Exception err) {
                setTestStatus("failed", err.getMessage(), page);
                err.printStackTrace();
            }
            browser.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public static void setTestStatus(String status, String remark, Page page) {
        Object result;
        result = page.evaluate("_ => {}", "lambdatest_action: { \"action\": \"setTestStatus\", \"arguments\": { \"status\": \"" + status + "\", \"remark\": \"" + remark + "\"}}");
    }
}

