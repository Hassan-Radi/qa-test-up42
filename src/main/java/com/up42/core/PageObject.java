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
package com.up42.core;

import java.lang.reflect.Field;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.up42.data.TestData;

/** Base page object to offer common functionality for page object creation. */
public class PageObject {

  protected static WebDriver driver;
  protected static WebDriverWait wait;
  protected static final Logger LOGGER = Logger.getLogger(PageObject.class.getName());

  public PageObject() {
    driver = DriverManager.getDriver();
    wait = new WebDriverWait(driver, TestData.TEN_SECONDS, TestData.HALF_SECOND);

    PageFactory.initElements(driver, this);
  }

  /**
   * Finds a WebElement in the page using the member variable name. Typically used in cases where
   * the locator contains one or more variables.
   *
   * @param clazz The parent class for this member variable.
   * @param memberVariableName The member variable name in String format
   * @param variables The variable values to replace in the original locator
   * @return
   */
  public WebElement findElement(Class clazz, String memberVariableName, Object... variables) {
    String locator = String.format(getXPathAnnotationValue(clazz, memberVariableName), variables);
    return driver.findElement(By.xpath(locator));
  }

  /**
   * Gets the number of elements that match a locator in a page
   *
   * @param clazz The parent class for this member variable.
   * @param memberVariableName The member variable name in String format
   * @return
   */
  public int getElementCount(Class clazz, String memberVariableName) {
    return driver.findElements(By.xpath(getXPathAnnotationValue(clazz, memberVariableName))).size();
  }

  /**
   * Gets an annotation value using Java Reflection API. Can be used with locators that contain
   * variables in them since the Selenium's Page object design doesn't support this.
   *
   * @param clazz The parent class for this member variable.
   * @param memberVariableName The member variable name in String format
   * @return
   */
  private String getXPathAnnotationValue(Class clazz, String memberVariableName) {
    String value = null;
    try {
      value = getFieldValue(clazz, memberVariableName).getDeclaredAnnotation(FindBy.class).xpath();
    } catch (Exception ex) {
      LOGGER.info("Failed to get the annotation value. Exception = " + ex);
    }
    return value;
  }

  /**
   * Gets a Field value from a class or one of its super classes
   *
   * @param clazz The parent class for this member variable.
   * @param memberVariableName The member variable name in String format
   * @return
   */
  private Field getFieldValue(Class clazz, String memberVariableName) {
    Field field = null;
    try {
      field = clazz.getDeclaredField(memberVariableName);
    } catch (NoSuchFieldException e) {
      if (clazz.getSuperclass() != null) {
        clazz = clazz.getSuperclass();
        // recursive call until you find the field value in a parent
        field = getFieldValue(clazz, memberVariableName);
      }
    }

    // check for null value
    if (field == null) {
      throw new RuntimeException("Failed to find the member variable in the class.");
    }

    return field;
  }

  /**
   * @return An instance of the WebDriverWait to use for waiting actions associated with the
   *     WebDriver
   */
  public static WebDriverWait getWait() {
    return wait;
  }
}
