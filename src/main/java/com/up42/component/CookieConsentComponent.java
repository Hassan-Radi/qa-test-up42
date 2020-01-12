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
