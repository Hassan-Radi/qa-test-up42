package com.up42.component;

import java.util.logging.Logger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.up42.core.PageObject;

public class NotificationComponent extends PageObject {

  protected static final Logger LOGGER = Logger.getLogger(NotificationComponent.class.getName());

  public NotificationComponent() {
    /** Make sure that the page loaded successfully before interacting with it */
    LOGGER.info("Waiting for the notification to load...");
    wait.until(ExpectedConditions.visibilityOf(notificationText));
  }

  @FindBy(xpath = "//div[contains(@class,'text')]")
  private WebElement notificationText;

  /** @return A String representing the notification text */
  public String getNotificationText() {
    return notificationText.getText();
  }
}
