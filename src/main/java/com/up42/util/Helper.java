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
package com.up42.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.up42.core.DriverManager;
import com.up42.data.TestData;

/** provides some helper functions that can be used across the framework. */
public class Helper {

  /** Returns true if the currently used browser is Firefox */
  public static boolean isFirefox() {
    if (DriverManager.getDriver() == null) {
      return false;
    } else if (DriverManager.getDriver() instanceof FirefoxDriver) {
      return true;
    }

    return false;
  }

  /** Returns true if the currently used browser is Chrome */
  public static boolean isChrome() {
    if (DriverManager.getDriver() == null) {
      return false;
    } else if (DriverManager.getDriver() instanceof ChromeDriver) {
      return true;
    }

    return false;
  }

  /**
   * Sometimes we need to click with JavaScript instead of relying on the Selenium native click
   * method.
   *
   * @param element The element to click on
   */
  public static void clickWithJavaScript(WebElement element) {
    JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
    executor.executeScript("arguments[0].click();", element);
  }

  /** Wait for page loading to complete */
  public static void waitForPageLoadingToComplete() {
    new WebDriverWait(DriverManager.getDriver(), TestData.TWENTY_SECOND_WAIT_MILLI)
        .until(
            driver ->
                ((JavascriptExecutor) driver)
                    .executeScript(TestData.WAIT_FOR_PAGE_LOADING)
                    .equals(TestData.COMPLETE_STATE));
  }

  /** Wait for all Ajax requests on the page to finish loading */
  public static void waitForAjaxToComplete() {
    ExpectedCondition<Boolean> jQueryLoad =
        new ExpectedCondition<Boolean>() {
          @Override
          public Boolean apply(WebDriver driver) {
            try {
              return ((Long)
                      ((JavascriptExecutor) DriverManager.getDriver())
                          .executeScript("return jQuery.active")
                  == 0);
            } catch (Exception e) {
              // no jQuery present
              return true;
            }
          }
        };

    new WebDriverWait(DriverManager.getDriver(), TestData.TWENTY_SECOND_WAIT_MILLI)
        .until(jQueryLoad);
  }

  /**
   * Executes a JavaScript script to calculate the amount of time it took an Ajax request to
   * complete
   *
   * @param urlToCheck The URL to check its loading time
   * @return A long representing the amount of time in milliseconds that it took to complete the
   *     request
   */
  public static long getAjaxRequestLoadingTime(String urlToCheck) {
    String script =
        String.format(
            "$.ajax({url: '%s',method: 'GET',start_time: new Date().getTime(),"
                + "complete: function(data) {alert(new Date().getTime() - this.start_time);}});",
            urlToCheck);

    // Execute the script which would show an alert
    ((JavascriptExecutor) DriverManager.getDriver()).executeAsyncScript(script);

    // Wait for the alert to appear
    Alert alertWithValue =
        new WebDriverWait(DriverManager.getDriver(), TestData.TWENTY_SECOND_WAIT_MILLI)
            .until(ExpectedConditions.alertIsPresent());

    // Get the alert text and accept it
    String alertText = alertWithValue.getText();
    alertWithValue.accept();

    return Long.valueOf(alertText);
  }

  /**
   * Stops the current thread's execution for a few milliseconds. This is to be used with caution as
   * static waits are not a good technique for waiting for element. ONLY use this to wait for
   * animations to finish
   *
   * @param milliseconds The amount of milliseconds to wait for.
   */
  public static void sleep(long milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Parses the regular expression and extracts the first group from it or returns null if it fails.
   *
   * @param regex The regular expression to parse.
   * @param data The string value to use with the regex.
   * @return A String representing the extracted part of the data according to the regex
   */
  public static String extractGroupFromRegex(String regex, String data) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(data);

    return matcher.find() ? matcher.group(1) : null;
  }
}
