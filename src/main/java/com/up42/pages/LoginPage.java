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

public class LoginPage extends PageObject {

  protected static final Logger LOGGER = Logger.getLogger(LoginPage.class.getName());

  public LoginPage() {
    /** Make sure that the page loaded successfully before interacting with it */
    wait.until(ExpectedConditions.visibilityOf(emailTextField));
  }

  @FindBy(id = "email")
  private WebElement emailTextField;

  @FindBy(id = "password")
  private WebElement passwordTextField;

  @FindBy(xpath = "//button[@type='submit']")
  private WebElement loginButton;

  /**
   * Logs a valid user to the system.
   *
   * @param email The email address of the user to use.
   * @param password The password of the user to use.
   * @return An object of the HomePage
   */
  public HomePage login(String email, String password) {
    LOGGER.info(String.format("Typing email %s in the email field...", email));
    emailTextField.sendKeys(email);
    LOGGER.info(String.format("Typing password %s in the password field...", password));
    passwordTextField.sendKeys(password);

    LOGGER.info("Clicking on the login button...");
    loginButton.click();

    return new HomePage();
  }
}
