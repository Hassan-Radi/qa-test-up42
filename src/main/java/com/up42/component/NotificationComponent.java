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

  /** @return A String representing the first notification text on the page */
  public String getNotificationText() {
    // TODO: Need to handle multiple notification messages here
    return notificationText.getText();
  }
}
