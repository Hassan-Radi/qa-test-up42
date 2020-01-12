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

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.up42.data.TestData;

/**
 * Responsible for creating driver instances according to the requested configs and allowing other
 * classes to access the created driver
 */
public class DriverManager {

  private static WebDriver driver;

  /** Private constructor, so no one can instantiate an object of this class. */
  private DriverManager() {}

  /**
   * Create a driver instance according to the currently requested browser. The way the driver is
   * created differs according to the execution mode.
   *
   * @return A WebDriver instance representing the browser.
   */
  public static WebDriver getDriver() {
    if (driver == null) {
      /** Browser selection is done using the command line configs. */
      if (TestData.BROWSER == null || TestData.BROWSER.isEmpty()) {
        throw new RuntimeException(
            "Failed to find a value for the config \"" + TestData.BROWSER_PROPERTY + "\".");
      }

      if (TestData.BROWSER.equalsIgnoreCase(TestData.FIREFOX_BROWSER)) {
        driver = TestData.EXECUTION_MODE.getFirefoxDriver();
      } else if (TestData.BROWSER.equalsIgnoreCase(TestData.CHROME_BROWSER)) {
        driver = TestData.EXECUTION_MODE.getChromeDriver();
      } else {
        throw new RuntimeException("This browser is not supported yet!");
      }

      // Wait 10 seconds before you declare an element as not found
      driver.manage().timeouts().implicitlyWait(TestData.TEN_SECONDS, TimeUnit.SECONDS);

      /**
       * BrowserStack is unable to set the resolution of the browser, so we have to use Selenium to
       * force the window resolution after the driver is initialised.
       */
      driver.manage().window().setSize(TestData.RESOLUTION_DIMENSION_VALUE);
    }

    return driver;
  }
}
