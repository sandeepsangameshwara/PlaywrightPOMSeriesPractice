package com.qa.opencart.base;

import java.util.Properties;

import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
//import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;

//@Listeners(com.qa.opencart.listeners.ExtentReportListener.class)
public class BaseTest {
    PlaywrightFactory pf;
    Page page;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected Properties prop;

    @BeforeTest
    @Parameters({ "browser" })
    public void setup(@Optional("chrome") String browserName) {
        System.out.println("Inside setup method");
        pf = new PlaywrightFactory();
        prop = pf.initProp();
        if (browserName != null) {
            prop.setProperty("browser", browserName);
        }
        page = pf.initBrowser(prop);
        System.out.println("Page object" + page);
        homePage = new HomePage(page);
        System.out.println("Home page object" + homePage);
    }

    @AfterTest
    public void tearDown() {
        page.context().browser().close();
    }
}
