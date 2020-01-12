package com.up42.component;

import java.util.logging.Logger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.up42.core.PageObject;
import com.up42.data.TestData;
import com.up42.util.Helper;

public class DeleteProjectComponent extends PageObject {

  protected static final Logger LOGGER = Logger.getLogger(DeleteProjectComponent.class.getName());

  public DeleteProjectComponent() {
    /** Make sure that the page loaded successfully before interacting with it */
    LOGGER.info("Waiting for the delete project panel to load...");
    wait.until(ExpectedConditions.visibilityOf(dialogHeaderText));
  }

  @FindBy(xpath = "//div[contains(@class,'vueModal_')]//h4[text()='Delete Project']")
  private WebElement dialogHeaderText;

  @FindBy(id = "confirm-delete-project-button")
  private WebElement deleteProjectButton;

  public NotificationComponent confirmProjectDeletion() {
    LOGGER.info("Clicking on the 'Delete' button...");
    deleteProjectButton.click();

    // wait for the action to complete
    Helper.sleep(TestData.SECOND_IN_MILLI);

    return new NotificationComponent();
  }
}
