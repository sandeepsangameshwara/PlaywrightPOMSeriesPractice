package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserConetxt;
    private Page page;
    private Properties prop;

    private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage = new ThreadLocal<>();

    public static Playwright getPlaywright() {
        return tlPlaywright.get();
    }

    public static Browser getBrowser() {
        return tlBrowser.get();
    }

    public static BrowserContext getBrowserContext() {
        return tlBrowserContext.get();
    }

    public static Page getPage() {
        return tlPage.get();
    }

    public Page initBrowser(Properties prop) {
        String browserName = prop.getProperty("browser");
        Boolean headLess = Boolean.parseBoolean(prop.getProperty("headless"));
        // playwright = Playwright.create();
        tlPlaywright.set(Playwright.create());

        switch (browserName.toLowerCase()) {
            case "chromium":
                // browser = playwright.chromium().launch(new
                // BrowserType.LaunchOptions().setHeadless(headLess));
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(headLess)));
                break;
            case "firefox":
                // browser = playwright.firefox().launch(new
                // BrowserType.LaunchOptions().setHeadless(headLess));
                tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(headLess)));
                break;
            case "chrome":
                // browser = playwright.chromium().launch(new
                // BrowserType.LaunchOptions().setChannel(browserName).setHeadless(headLess));
                tlBrowser.set(getPlaywright().chromium()
                        .launch(new BrowserType.LaunchOptions().setChannel(browserName).setHeadless(headLess)));

                break;
            case "safari":
                // browser = playwright.webkit().launch(new
                // BrowserType.LaunchOptions().setHeadless(headLess));
                tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(headLess)));

                break;
            default:
                break;
        }
        // browserConetxt = browser.newContext();
        tlBrowserContext.set(getBrowser().newContext());

        // page = browserConetxt.newPage();
        tlPage.set(getBrowserContext().newPage());
        // page.setDefaultTimeout(Double.parseDouble(prop.getProperty("defaultTimeOut")));
        getPage().setDefaultTimeout(Double.parseDouble(prop.getProperty("defaultTimeOut")));
        getPage().navigate(prop.getProperty("url"));
        return getPage();
    }

    public Properties initProp() {
        try {
            FileInputStream fis = new FileInputStream("./src/test/resources/properties/config.properties");
            prop = new Properties();
            prop.load(fis);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return prop;
    }

    public static String takeScreenshot() {
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        // getPage().screenshot(new
        // Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

        byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        String base64Path = Base64.getEncoder().encodeToString(buffer);

        return base64Path;
    }
}
