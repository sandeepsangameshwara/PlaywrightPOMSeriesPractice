package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
     private Page page;

    // 2. Page Constructor
    public LoginPage(Page page) {
        this.page = page;
    }

    // 1.Prepare String locator repository
    
    private String userNameField = "//input[@id='input-email']";
    private String passwordField = "//input[@id='input-password']";
    private String forgotPwdLink = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']";
    private String loginBtn = "//input[@value='Login']";
    private String logoutBtn="//a[@class='list-group-item'][normalize-space()='Logout']";

    // 3. page actions and methods
    public String getLoginPageTitle() {
        String pageTitle = page.title();
        System.out.println("Page title :" + pageTitle);
        return pageTitle;
    }

    public String getLoginPageURL() {
        String pageURL = page.url();
        System.out.println("Page URL :" + pageURL);
        return pageURL;
    }

    public boolean doLogin(String userName,String password) {
        page.fill(userNameField, userName);
        page.fill(passwordField,password);
        page.click(loginBtn);
        if(page.isVisible(logoutBtn)){
            return true;
        }
        return false;
    }

    public boolean isForgotPwdLinkExists(){
        return page.isVisible(forgotPwdLink);
    }
}
