import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class FirstTest {
    Playwright playwright;
    @Test
    public void firstSampleTest() {


        playwright = Playwright.create();
        //new BrowserType.LaunchOptions().setHeadless(true)
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
        Page page = browser.newPage();
        page.navigate("https://the-internet.herokuapp.com/");
        System.out.println("Title ====" + page.title());
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));

    }

   // @Test
    public void secondTest(){
        try (Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false))) {
            BrowserContext context = browser.newContext();
        }
    }
}
