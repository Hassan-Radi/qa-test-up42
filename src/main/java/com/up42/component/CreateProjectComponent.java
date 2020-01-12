package com.up42.component;

import java.util.logging.Logger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.up42.core.PageObject;
import com.up42.pages.ProjectPage;

public class CreateProjectComponent extends PageObject {

  protected static final Logger LOGGER = Logger.getLogger(CreateProjectComponent.class.getName());

  public CreateProjectComponent() {
    /** Make sure that the page loaded successfully before interacting with it */
    LOGGER.info("Waiting for the create project panel to load...");
    wait.until(ExpectedConditions.visibilityOf(startProjectHeaderText));
  }

  @FindBy(xpath = "//h4[text()='Start Project']")
  private WebElement startProjectHeaderText;

  @FindBy(id = "name")
  private WebElement projectNameTextField;

  @FindBy(id = "description")
  private WebElement projectDescriptionTextField;

  @FindBy(xpath = "//button[@type='submit']")
  private WebElement saveProjectButton;

  /**
   * Creates a new project given the needed parameters.
   *
   * @param projectName The name to give to the new project.
   * @param projectDescription The project description (an optional field).
   * @return An instance of the ProjectPage with the new project selected in it.
   */
  public ProjectPage createNewProject(String projectName, String projectDescription) {
    LOGGER.info(
        String.format(
            "Creating a new project with the name [%s] and description [%s]...",
            projectName, projectDescription));
    projectNameTextField.sendKeys(projectName);
    projectDescriptionTextField.sendKeys(projectDescription);
    saveProjectButton.click();

    return new ProjectPage();
  }
}
