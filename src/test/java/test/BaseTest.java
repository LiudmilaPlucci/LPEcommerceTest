package test;

import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.LoggerUtils;
import utils.ReportUtils;
import utils.runner.BrowserManager;

import java.lang.reflect.Method;

import static utils.TestData.BASE_URL;
import static utils.TestData.HOME_END_POINT;

public abstract class BaseTest {

    private final Playwright playwright = Playwright.create();
    private Browser browser;

    private BrowserContext context;
    private Page page;

    @BeforeSuite
    void checkIfPlaywrightCreated() {
        ReportUtils.logReportHeader();

        if (playwright != null) {
            LoggerUtils.logInfo("Playwright created.");
        } else {

            LoggerUtils.logFatal("FATAL: Playwright is NOT created.");
            System.exit(1);
        }

        LoggerUtils.logInfo(ReportUtils.printLine());
    }

//    @BeforeClass
//    protected void launchBrowser() {
//        browser = BrowserManager.createBrowser(playwright, ConfigProperties.ENVIRONMENT_CHROMIUM);
//
//        if (browser.isConnected()) {
//            LoggerUtils.logInfo("Browser " + browser.browserType().name() + " is connected.");
//        } else {
//            LoggerUtils.logFatal("FATAL: Browser is NOT connected.");
//            System.exit(1); // выходим из системы с кодом ошибки 1
//        }
//    }
    @BeforeClass
    @Parameters({"browserOption", "isHeadless", "slowMo"})
    void launchBrowser(String browserOption, String isHeadless, String slowMo) {
        browser = BrowserManager.createBrowser(playwright, browserOption, isHeadless, slowMo);

        if (browser.isConnected()) {
            LoggerUtils.logInfo(ReportUtils.printLine());
            LoggerUtils.logInfo("Browser " + browser.browserType().name() + " is connected.\n\n");
            LoggerUtils.logInfo(ReportUtils.printLine());
        } else {
            LoggerUtils.logFatal("FATAL: Browser is NOT connected.");
            System.exit(1);
        }
    }

    @BeforeMethod
    void createContextAndPage(Method method) {
        ReportUtils.logTestName(method);

        context = browser.newContext();
        LoggerUtils.logInfo("Context created.");

        page = context.newPage();
        LoggerUtils.logInfo("Page created.");

        getPage().navigate(BASE_URL);

        if (isOnHomePage()) {
            LoggerUtils.logInfo("Base URL is opened and content is not empty.");
        } else {
            LoggerUtils.logError("ERROR: Base URL is NOT opened OR content is EMPTY.");
        }
    }

    @AfterMethod
    void closeContext(Method method, ITestResult result) {

        if (page != null) {
            page.close();
            LoggerUtils.logInfo("Page closed.");
        }
        if (context != null) {
            context.close();
            LoggerUtils.logInfo("Context closed.");
        }

        ReportUtils.logTestResult(method, result);

    }

    @AfterClass
    void closeBrowser() {

        if (browser != null && browser.isConnected()) {
            browser.close();
            if (!browser.isConnected()) {
                LoggerUtils.logInfo(ReportUtils.printLine());
                LoggerUtils.logInfo("Browser " + browser.browserType().name() + " is closed.");
                LoggerUtils.logInfo(ReportUtils.printLine());
            }
        }
    }

    private boolean isOnHomePage() {
        getPage().waitForLoadState();

        return getPage().url().equals(BASE_URL + HOME_END_POINT) && !page.content().isEmpty();

    }

    protected Page getPage() {
        return page;
    }

    protected boolean getIsOnHomePage() {

        return isOnHomePage();
    }

    @AfterSuite
    void closeBrowserAndPlaywright() {
        if (playwright != null) {
            playwright.close();
            LoggerUtils.logInfo("Playwright is closed.");
        }
    }
}

