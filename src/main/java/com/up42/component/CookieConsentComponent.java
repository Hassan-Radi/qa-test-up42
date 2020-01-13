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
import com.up42.data.TestData;
import com.up42.util.Helper;

public class CookieConsentComponent extends PageObject {

  protected static final Logger LOGGER = Logger.getLogger(CookieConsentComponent.class.getName());
  private static final String BASE_LOCATOR =
      "//div[@role='dialog' and not(contains(@style,'display: none;'))]";

  public CookieConsentComponent() {
    /** Make sure that the page loaded successfully before interacting with it */
    LOGGER.info("Waiting for the Cookies consent dialog to load...");
    wait.until(ExpectedConditions.visibilityOf(acceptCookiesButton));
  }

  @FindBy(xpath = BASE_LOCATOR + "/div[@class='cc-compliance']/a")
  private WebElement acceptCookiesButton;

  @FindBy(xpath = BASE_LOCATOR + "/span[@class='cc-message']")
  private WebElement cookieConsentText;

  /** @return A String representation to the cookie consent dialog text. */
  public String getCookieConsentDialogText() {
    return cookieConsentText.getText();
  }

  /** Accepts the website cookies */
  public void acceptCookies() {
    LOGGER.info("Clicking on the 'Accept Cookies' button...");
    acceptCookiesButton.click();

    // Wait for the dialog to disappear
    Helper.sleep(TestData.SECOND_IN_MILLI);
  }
}
