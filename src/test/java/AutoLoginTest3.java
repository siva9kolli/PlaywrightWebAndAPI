import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class AutoLoginTest3 {

    @Test
    public void storeCredentials() throws InterruptedException {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
        BrowserContext browserContext = browser.newContext();
        Page page = browserContext.newPage();
        page.navigate("https://demowebshop.tricentis.com/login");
        System.out.println("Title ====" + page.title());
        page.fill("#Email", "autotest@yopmail.com");
        page.fill("#Password", "Test@123");
        page.click("[value='Log in']");
        System.out.println("Title ====" + page.title());

        browserContext.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("test.json")));
        Thread.sleep(5000);
        playwright.close();
    }



    @Test
    public void autologin() throws InterruptedException {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("test.json")));
        Page page = browserContext.newPage();
        page.navigate("https://demowebshop.tricentis.com/");
        Thread.sleep(5000);
        Assert.assertTrue(page.isVisible(".title"));
        Thread.sleep(5000);
        playwright.close();
    }
}
