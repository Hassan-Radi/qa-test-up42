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

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.up42.automation.BaseTest;
import com.up42.component.CookieConsentComponent;
import com.up42.component.NotificationComponent;
import com.up42.data.TestData;
import com.up42.pages.HomePage;
import com.up42.pages.LandingPage;
import com.up42.pages.LoginPage;
import com.up42.pages.ProjectPage;
import com.up42.pages.WorkflowPage;
import com.up42.util.Helper;

public class WebTests extends BaseTest {

  private HomePage homePage;
  private ProjectPage projectPage;

  @Test
  public void loginToWebsite() {
    /**
     * TODO: Need to handle an edge case here where the test would fail if the account already have
     * a project initially (excluding the Demo one).
     */
    LOGGER.info("STEP 1 - Navigating to the website.");
    LandingPage landingPage = new LandingPage();

    LOGGER.info("STEP 2 - Navigating to the login page.");
    LoginPage loginPage = landingPage.navigateToLoginPage();

    LOGGER.info("STEP 3 - Login with valid credentials.");
    homePage = loginPage.login(TestData.EMAIL, TestData.PASSWORD);

    LOGGER.info("Verify that the user is logged in successfully to the website.");
    Assert.assertTrue(homePage.isWelcomeTextDisplayed(), "Welcome text is not displayed.");

    LOGGER.info("Accepting the cookie consent dialog before you move on to the next test...");
    new CookieConsentComponent().acceptCookies();
  }

  @Test(dependsOnMethods = "loginToWebsite")
  public void createNewProject() {
    LOGGER.info("STEP 4 - Creating a new project.");
    projectPage =
        homePage.startProject(TestData.NEW_PROJECT_NAME, TestData.NEW_PROJECT_DESCRIPTION);

    LOGGER.info(
        "Verify that the project have been created successfully with the correct project name & description.");
    Assert.assertEquals(
        projectPage.getProjectName(), TestData.NEW_PROJECT_NAME, "Project name is incorrect.");
    Assert.assertEquals(
        projectPage.getProjectDescription(),
        TestData.NEW_PROJECT_DESCRIPTION,
        "Project description is incorrect.");
  }

  @Test(dependsOnMethods = "createNewProject")
  public void createNewWorkflow() {
    LOGGER.info("STEP 4 - Creating a new workflow.");
    WorkflowPage workflowPage = projectPage.initiateNewWorkflow();
    workflowPage.createNewWorkflow(
        TestData.NEW_WORKFLOW_NAME,
        TestData.NEW_WORKFLOW_DESCRIPTION,
        TestData.DATA_BLOCKS,
        TestData.PROCESSING_BLOCKS);

    LOGGER.info("Verify that the data/processing blocks have been added successfully.");
    Arrays.stream(TestData.DATA_BLOCKS)
        .forEach(
            it ->
                Assert.assertEquals(
                    workflowPage.getDataBlocksFromPipeline().get(0).getPipelineName(),
                    it.getBlockName(),
                    "Wrong data block was found."));
    Arrays.stream(TestData.PROCESSING_BLOCKS)
        .forEach(
            it ->
                Assert.assertEquals(
                    workflowPage.getProcessingBlocksFromPipeline().get(0).getPipelineName(),
                    it.getBlockName(),
                    "Wrong processing block was found."));
  }

  @AfterClass
  public void teardown() {
    // Wait for a second before starting the teardown process for all actions to be done
    Helper.sleep(TestData.SECOND_IN_MILLI);

    /**
     * We need to do proper cleanup here and delete the project we just created to leave the system
     * as it was found before the tests.
     */
    LOGGER.info("Cleaning up after the tests by deleting the project that was created...");
    NotificationComponent notificationComponent = projectPage.deleteCurrentProject();
    Assert.assertEquals(
        notificationComponent.getNotificationText(),
        TestData.PROJECT_SUCCESSFULLY_DELETED_MESSAGE,
        "Incorrect message displated.");

    // Wait for a second before starting the main teardown process for all actions to be done
    Helper.sleep(TestData.SECOND_IN_MILLI);

    super.tearDown();
  }
}
