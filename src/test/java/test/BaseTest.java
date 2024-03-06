package test;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.LoggerUtils;
import utils.runner.BrowserManager;

import static utils.TestData.BASE_URL;
import static utils.TestData.HOME_END_POINT;

abstract class BaseTest {

    private final Playwright playwright = Playwright.create();
    private final Browser browser = BrowserManager.createBrowser(playwright, "chromium", false, 1500);
    private BrowserContext context;
    private Page page;

    @BeforeSuite
    void checkIfPlaywrightCreatedAndBrowserLaunched() {
        if (playwright != null) {
            LoggerUtils.logInfo("Playwright created");
        } else {
            LoggerUtils.logFatal("FATAL: Playwright is NOT created.");
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

        System.out.println("Start test");

        getPage().navigate(BASE_URL);
        if (isOnHomePage()) {
            System.out.println("Base url opened and content is not empty.");
        } else {
            System.out.println("ERROR: Base url is NOT opened OR content is EMPTY.");
        }
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

        }
        if (playwright != null) {
            playwright.close();
            System.out.println("Playwright closed");
        }
    }

    private boolean isOnHomePage() {
        getPage().waitForLoadState();

        return getPage().url().equals(BASE_URL + HOME_END_POINT) && !page.content().isEmpty();
    }

    Page getPage() {

        return page;
    }

    protected boolean getIsOnHomePage() {

        return isOnHomePage();
    }
}
