package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {
    private Page page;

    // 2. Page Constructor
    public HomePage(Page page) {
        System.out.println("Inside Homepage constructor");
        this.page = page;
    }

    // 1.Prepare String locator repository
    private String searchBox = "input[name='search']";
    private String searchBtn = "div#search button";
    private String searchPageHeader = "div#content h1";
    private String myAccountLink = "//span[normalize-space()='My Account']";
    private String loginLink = "//a[normalize-space()='Login']";

    // 3. page actions and methods
    public String getHomePageTitle() {
        String pageTitle = page.title();
        System.out.println("Page title :" + pageTitle);
        return pageTitle;
    }

    public String getHomePageURL() {
        String pageURL = page.url();
        System.out.println("Page URL :" + pageURL);
        return pageURL;
    }

    public String doSearch(String productName) {
        page.fill(searchBox, productName);
        page.click(searchBtn);
        String searchName = page.locator(searchPageHeader).textContent();
        System.out.println("Keyword Search :"+searchName);
        return searchName;
    }

    public LoginPage navigateToLoginScreen() {
        page.click(myAccountLink);
        page.click(loginLink);
        return new LoginPage(page);
    }

}
