package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.PlaywrightConstants;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test(priority = 1)
    public void testForLoginScrNavigation() {
        loginPage = homePage.navigateToLoginScreen();
        String actualLoginTitle = loginPage.getLoginPageTitle();
        System.out.println("Login screen title :" + actualLoginTitle);
        Assert.assertEquals(actualLoginTitle, PlaywrightConstants.LOGIN_PAGE_TITLE);
    }

    @Test(priority = 2)
    public void checkIfForgotPwdLinkExists() {
        Assert.assertTrue(loginPage.isForgotPwdLinkExists());
    }

    @Test(priority = 3)
    public void doLoginToMyAccount() {
        Assert.assertTrue(loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim()));
    }
}
