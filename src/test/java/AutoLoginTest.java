import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class AutoLoginTest {

    Playwright playwright;
    Browser browser;
    Page page;
    BrowserContext browserContext;

    @BeforeTest
    public void setup(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));

    }

    //@Test
    public void storeCredentials() throws InterruptedException {
        browserContext = browser.newContext();
         page = browserContext.newPage();
        page.navigate("https://www.saucedemo.com/");
        System.out.println("Title ====" + page.title());
        page.fill("#user-name", "standard_user");
        page.fill("#password", "secret_sauce");
        page.click("#login-button");
        System.out.println("Title ====" + page.title());

        browserContext.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("creds.json")));
        Thread.sleep(5000);
        playwright.close();
    }

    @Test
    public void autoLogin(){
        browserContext = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("creds.json")));
        page = browserContext.newPage();
        page.navigate("https://www.saucedemo.com/");
        Assert.assertTrue(page.isVisible(".title"));
    }
}
