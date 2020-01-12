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

import com.up42.core.PageObject;

public class HomePage extends PageObject {

  protected static final Logger LOGGER = Logger.getLogger(HomePage.class.getName());

  public HomePage() {
    /** Make sure that the page loaded successfully before interacting with it */
    LOGGER.info("Waiting for the home page to load...");
    wait.until(ExpectedConditions.visibilityOf(welcomeHeaderText));
  }

  @FindBy(xpath = "//h1[text()='Welcome']")
  private WebElement welcomeHeaderText;

  /** @return True if the welcome text is displayed on the HomePage, false otherwise. */
  public boolean isWelcomeTextDisplayed() {
    return welcomeHeaderText.isDisplayed();
  }
}
