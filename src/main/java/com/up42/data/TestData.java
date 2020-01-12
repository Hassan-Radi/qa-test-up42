/**
 * Copyright 2018 Hassan Radi
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
package com.up42.data;

import org.openqa.selenium.Dimension;

import com.up42.core.ExecutionMode;

/**
 * Class to act as a container for all the test data in the project. When you need to change any of
 * these values, you change it in one location and it reflects everywhere else in the whole project.
 */
public class TestData {
  // configs
  public static final String BROWSER_PROPERTY = "browser";
  public static final String FIREFOX_BROWSER = "firefox";
  public static final String CHROME_BROWSER = "chrome";
  public static final ExecutionMode EXECUTION_MODE =
      ExecutionMode.valueOf(System.getProperty("executionMode"));
  public static final String BROWSER = System.getProperty(BROWSER_PROPERTY);

  // Environment variables
  public static final String BROWSERSTACK_USER_ENVIRONEMNT_VARIABLE =
      System.getenv("BROWSERSTACK_USER");
  public static final String BROWSERSTACK_TOKEN_ENVIRONEMNT_VARIABLE =
      System.getenv("BROWSERSTACK_TOKEN");

  // Timeouts
  public static final int TEN_SECONDS = 10;
  public static final int HALF_SECOND = 500;
  public static final int SECOND_IN_MILLI = 1000;
  public static final int TWENTY_SECOND_WAIT_MILLI = 20000;

  // Selenium
  public static final String BROWSERSTACK_HUB_URL =
      "https://%s:%s@hub-cloud.browserstack.com/wd/hub";
  public static final String BROWSER_CAPABILITY = "browser";
  public static final String BROWSER_VERSION_CAPABILITY = "browser_version";
  public static final String BROWSER_NAME_W3C_CAPABILITY = "browserName";
  public static final String BROWSER_VERSION_W3C_CAPABILITY = "browserVersion";
  public static final String OS_CAPABILITY = "os";
  public static final String OS_VERSION_CAPABILITY = "os_version";
  public static final String RESOLUTION_CAPABILITY = "resolution";
  public static final String RESOLUTION_VALUE = "1920x1080";
  public static final Dimension RESOLUTION_DIMENSION_VALUE = new Dimension(1920, 1080);
  public static final String ACCEPT_SSL_ALERTS_CAPABILITY = "acceptSSLAlerts";
  public static final String SELENIUM_VERSION_CAPABILITY = "browserstack.selenium_version";
  public static final String SELENIUM_VERSION_VALUE = "3.141.59";
  public static final String USE_W3C_MODE_CAPABILITY = "browserstack.use_w3c";
  public static final String PROJECT_CAPABILITY = "project";
  public static final String BROWSER_LOG_FILE_PATH = "/dev/null";
  public static final String CHROME_BROWSER_VERSION = "77.0";
  public static final String FIREFOX_BROWSER_VERSION = "69.0";
  public static final String WINDOWS_OS_NAME = "Windows";
  public static final String WINDOWS_10_NAME = "10";
  public static final String TRUE_VALUE = "true";

  // GeckoDriver
  public static final String GECKO_DRIVER_VERSION_CAPABILITY = "browserstack.geckoversion";
  public static final String GECKO_DRIVER_VERSION_VALUE = "0.25.0";
  public static final String GECKO_DRIVER_LOCAL_PATH_PROPERTY = "webdriver.gecko.driver";

  // ChromeDriver
  public static final String CHROME_DRIVER_VERSION_CAPABILITY = "browserstack.chrome.driver";
  public static final String CHROME_DRIVER_VERSION_VALUE = "77.0.3865.40";
  public static final String CHROME_DRIVER_DRIVER_LOCAL_PATH_PROPERTY = "webdriver.chrome.driver";

  // JavaScript
  public static final String WAIT_FOR_PAGE_LOADING = "return document.readyState";
  public static final String COMPLETE_STATE = "complete";

  // Misc
  public static final String PROJECT_VALUE = "UP42";
  // URLs
  public static final String LANDING_PAGE = "https://up42.com/";
  public static final String EMAIL = "hassan.muhammad1990@gmail.com";
  public static final String PASSWORD = "aWQJ5fGxuEjq9kq";
  public static final String NEW_PROJECT_NAME = "Project " + System.currentTimeMillis();
  public static final String NEW_PROJECT_DESCRIPTION =
      "Project Description " + System.currentTimeMillis();

  public static final String PROJECT_SUCCESSFULLY_DELETED_MESSAGE =
      "Your project was successfully deleted.";
}
