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
package com.up42.automation;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.up42.core.DriverManager;
import com.up42.core.PageObject;
import com.up42.data.TestData;
import com.up42.util.Helper;

/** Base test class for the common setup and driver creation for all the test cases. */
public class BaseTest {

  protected static final Logger LOGGER = Logger.getLogger(BaseTest.class.getName());
  protected static WebDriver driver = null;

  /**
   * Navigate to a website page given the URL and create an instance of a page object given its
   * class name
   *
   * @param pageURL
   * @param clazz
   * @return
   */
  public <T extends PageObject> T navigateToPage(String pageURL, Class<T> clazz) {
    LOGGER.info("Navigating to page with URL = " + pageURL);
    DriverManager.getDriver().get(pageURL);
    return PageFactory.initElements(driver, clazz);
  }

  @BeforeMethod
  public void beforeMethod() {
    LOGGER.info(
        "\n*************************************************************************************\n"
            + "*************************************NEW TEST****************************************\n"
            + "*************************************************************************************");
  }

  @BeforeClass
  public static void setUp() {
    driver = DriverManager.getDriver();
  }

  @AfterClass
  public static void tearDown() {
    /**
     * clear after the test is done executing by terminating the running browser instance to prevent
     * memory leaks.
     */
    if (DriverManager.getDriver() != null) {
      DriverManager.getDriver().quit();
      LOGGER.info("Terminating the driver session and killing the browser...");
    }
  }
}
