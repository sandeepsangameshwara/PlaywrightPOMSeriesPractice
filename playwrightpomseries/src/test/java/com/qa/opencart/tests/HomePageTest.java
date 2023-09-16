package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.PlaywrightConstants;

public class HomePageTest extends BaseTest {

    @Test
    public void homePageTitleTest() {
        System.out.println("Let's see the home page obj :"+homePage);
        //System.out.println("Let's see the page obj :"+page);
        String actualHomePageTitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualHomePageTitle, PlaywrightConstants.HOME_PAGE_TITLE);
    }

    @Test
    public void homePageURLTest() {
        String actualHomePageURL = homePage.getHomePageURL();
        Assert.assertEquals(actualHomePageURL, prop.getProperty("url"));
    }

    @DataProvider
    public Object[][] getProductData() {
        return new Object[][] {
                { "Macbook" }, { "Iphone" }, { "Ipod" }
        };
    }

    @Test(dataProvider = "getProductData")
    public void searchTest(String productName) {
        String actualSearchTitle = homePage.doSearch(productName);
        Assert.assertEquals(actualSearchTitle, "Search - " + productName);
    }

}
