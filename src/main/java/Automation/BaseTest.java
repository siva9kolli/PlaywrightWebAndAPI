package Automation;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.BeforeSuite;

import java.nio.file.Paths;

public class BaseTest {
    public Playwright playwright;
    public Page page;
    @BeforeSuite
    public void firstSampleTest() {
        playwright = Playwright.create();
        //new BrowserType.LaunchOptions().setHeadless(true)
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(5));
        page = browser.newPage();
        page.navigate("https://www.saucedemo.com/");
        System.out.println("Title ====" + page.title());
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));

    }
}
