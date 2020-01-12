/**
 * Copyright 2020 Hassan Radi
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * <p>See the License for the specific language governing permissions and limitations under the
 * License.
 */
package com.up42.automation.web;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.up42.automation.BaseTest;
import com.up42.data.TestData;
import com.up42.pages.HomePage;
import com.up42.pages.LandingPage;
import com.up42.pages.LoginPage;

public class WebTests extends BaseTest {

  @Test
  public void loginToCustomerPortal() {
    LOGGER.info("STEP 1 - Navigating to the website.");
    LandingPage landingPage = new LandingPage();

    LOGGER.info("STEP 2 - Navigating to the login page.");
    LoginPage loginPage = landingPage.navigateToLoginPage();

    LOGGER.info("STEP 3 - Login with valid credentials.");
    HomePage homePage = loginPage.login(TestData.EMAIL, TestData.PASSWORD);
    Assert.assertTrue(homePage.isWelcomeTextDisplayed(), "Welcome text is not displayed.");
  }
}
