package utils.runner;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

import java.util.Map;

public final class BrowserManager {

    public static Browser createBrowser(Playwright playwright, Map<String, String> environment) {
        String browserName = environment.get("browser");
        boolean isHeadless = Boolean.parseBoolean(environment.get("isHeadless"));
        int slowMo = Integer.parseInt(environment.get("slowMo"));

        switch (browserName) {
            case "chromium" -> {
                return playwright.chromium()
                        .launch(new BrowserType.LaunchOptions()
                                .setHeadless(isHeadless)
                                .setSlowMo(slowMo));
            }
            case "firefox" -> {
                return playwright.firefox()
                        .launch(new BrowserType.LaunchOptions()
                                .setHeadless(isHeadless)
                                .setSlowMo(slowMo));

            }
            case "webkit" -> {
                return playwright.webkit()
                        .launch(new BrowserType.LaunchOptions()
                                .setHeadless(isHeadless)
                                .setSlowMo(slowMo));
            }
            default -> {
//                LoggerUtils.logWarning("WARNING: " + browserName + " is NOT match any options. chromium() launched.");
                return playwright.chromium()
                        .launch(new BrowserType.LaunchOptions()
                                .setHeadless(true)
                                .setSlowMo(0));
            }
        }
    }

    public static Browser createBrowser(Playwright playwright, String browser, String isHeadlessString, String slowMoString) {
        boolean isHeadless;
        int slowMo;

        if (isHeadlessString != null && !isHeadlessString.isEmpty() && slowMoString != null && !slowMoString.isEmpty()) {
            isHeadless = Boolean.parseBoolean(isHeadlessString);
            slowMo = Integer.parseInt(slowMoString);
        } else {
            isHeadless = true;
            slowMo = 0;
        }

        switch (browser) {
            case "firefox" -> {
                return playwright.firefox()
                        .launch(new BrowserType.LaunchOptions()
                                .setHeadless(isHeadless)
                                .setSlowMo(slowMo));
            }
            case "webkit" -> {
                return playwright.webkit()
                        .launch(new BrowserType.LaunchOptions()
                                .setHeadless(isHeadless)
                                .setSlowMo(slowMo));
            }
            default -> {
                return playwright.chromium()
                        .launch(new BrowserType.LaunchOptions()
                                .setHeadless(isHeadless)
                                .setSlowMo(slowMo));
            }
        }
    }
}
