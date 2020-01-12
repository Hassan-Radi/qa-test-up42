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
package com.up42.pages;

import java.util.logging.Logger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.up42.component.CookieConsentComponent;
import com.up42.component.DeleteProjectComponent;
import com.up42.component.NotificationComponent;
import com.up42.core.PageObject;

public class ProjectPage extends PageObject {

  protected static final Logger LOGGER = Logger.getLogger(ProjectPage.class.getName());

  public ProjectPage() {
    /** Make sure that the page loaded successfully before interacting with it */
    LOGGER.info("Waiting for the project page to load...");
    wait.until(ExpectedConditions.visibilityOf(projectDashboardLink));
  }

  @FindBy(xpath = "//span[text()='Dashboard']")
  private WebElement projectDashboardLink;

  @FindBy(xpath = "//main//h1")
  private WebElement projectNameText;

  @FindBy(xpath = "//main//h1/following-sibling::p")
  private WebElement projectDescriptionText;

  @FindBy(xpath = "//span[text()='Settings']")
  private WebElement projectSettingsLink;

  @FindBy(id = "delete-project-button")
  private WebElement deleteProjectButton;

  /** @return A String representing the project name */
  public String getProjectName() {
    return projectNameText.getText();
  }

  /** @return A String representing the project description */
  public String getProjectDescription() {
    return projectDescriptionText.getText();
  }

  /**
   * Deletes the currently selected project from the projects list.
   *
   * @return An instance to the notification message that appears.
   */
  public NotificationComponent deleteCurrentProject() {
    LOGGER.info("Clicking on the 'Settings' link...");
    projectSettingsLink.click();

    LOGGER.info("Accepting the cookies first...");
    new CookieConsentComponent().acceptCookies();

    LOGGER.info("Clicking on the 'Delete Project' button...");
    deleteProjectButton.click();

    LOGGER.info("Confirm the deletion by clicking on the 'Delete' button...");
    return new DeleteProjectComponent().confirmProjectDeletion();
  }
}
