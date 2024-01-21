package test;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

abstract class BaseTest {

    private final Playwright playwright = Playwright.create();
    private final Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
            .setHeadless(false)
            .setSlowMo(1500));
    private BrowserContext context;
    private Page page;

    @BeforeSuite
    void checkIfPlaywrightCreatedAndBrowserLaunched() {
        if (playwright != null) {
            System.out.println("Playwright created");
        } else {
            System.out.println("FATAL: Playwright is NOT created.");
            System.exit(1);
        }

        if (browser.isConnected()) {
            System.out.println("Browser " + browser.browserType().name() + " is connected.");
        } else {
            System.out.println("FATAL: Browser is NOT connected.");
            System.exit(1);
        }
    }

    @BeforeMethod
    void createContextAndPage() {
        context = browser.newContext();
        System.out.println("Context created");

        page = context.newPage();
        System.out.println("Page created");
    }

    @AfterMethod
    void closeContext() {
        if (page != null) {
            page.close();
            System.out.println("Page closed");
        }
        if (context != null) {
            context.close();
            System.out.println("Context closed");
        }
    }

    @AfterSuite
    void closeBrowserAndPlaywright() {
        if (browser != null) {
            browser.close();
            System.out.println("Browser closed");
        }
        if (playwright != null) {
            playwright.close();
            System.out.println("Playwright closed");
        }
    }

    Page getPage() {
        return page;
    }
}
