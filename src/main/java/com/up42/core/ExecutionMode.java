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

import java.net.URL;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.up42.data.TestData;

/** An enum that defines all the execution modes supported by the framework */
public enum ExecutionMode implements IExecutionMode {
  LOCAL() {

    @Override
    public WebDriver getFirefoxDriver() {
      // To stop seeing the GeckoDriver annoying logs
      System.setProperty(
          FirefoxDriver.SystemProperty.BROWSER_LOGFILE, TestData.BROWSER_LOG_FILE_PATH);

      LOGGER.info("Initializing Firefox browser...");
      return new FirefoxDriver();
    }

    @Override
    public WebDriver getChromeDriver() {
      LOGGER.info("Initializing Chrome browser...");
      return new ChromeDriver();
    }
  },

  BROWSERSTACK() {

    @Override
    public WebDriver getFirefoxDriver() {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      /**
       * TODO: Move this to an external file to allow better visibility and make it easier to change
       * the values or add new ones without re-compiling the code.
       */
      capabilities.setCapability(TestData.BROWSER_NAME_W3C_CAPABILITY, TestData.FIREFOX_BROWSER);
      capabilities.setCapability(
          TestData.BROWSER_VERSION_W3C_CAPABILITY, TestData.FIREFOX_BROWSER_VERSION);
      capabilities.setCapability(TestData.OS_CAPABILITY, TestData.WINDOWS_OS_NAME);
      capabilities.setCapability(TestData.OS_VERSION_CAPABILITY, TestData.WINDOWS_10_NAME);
      capabilities.setCapability(TestData.RESOLUTION_CAPABILITY, TestData.RESOLUTION_VALUE);
      capabilities.setCapability(TestData.ACCEPT_SSL_ALERTS_CAPABILITY, TestData.TRUE_VALUE);
      capabilities.setCapability(
          TestData.SELENIUM_VERSION_CAPABILITY, TestData.SELENIUM_VERSION_VALUE);
      capabilities.setCapability(
          TestData.GECKO_DRIVER_VERSION_CAPABILITY, TestData.GECKO_DRIVER_VERSION_VALUE);
      capabilities.setCapability(TestData.USE_W3C_MODE_CAPABILITY, TestData.TRUE_VALUE);
      capabilities.setCapability(TestData.PROJECT_CAPABILITY, TestData.PROJECT_VALUE);

      LOGGER.info("Initializing Firefox browser...");
      return createBrowserStackRemoteDriver(capabilities);
    }

    @Override
    public WebDriver getChromeDriver() {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      /**
       * TODO: Move this to an external file to allow better visibility and make it easier to change
       * the values or add new ones without re-compiling the code.
       */
      capabilities.setCapability(TestData.BROWSER_CAPABILITY, TestData.CHROME_BROWSER);
      capabilities.setCapability(
          TestData.BROWSER_VERSION_CAPABILITY, TestData.CHROME_BROWSER_VERSION);
      capabilities.setCapability(TestData.OS_CAPABILITY, TestData.WINDOWS_OS_NAME);
      capabilities.setCapability(TestData.OS_VERSION_CAPABILITY, TestData.WINDOWS_10_NAME);
      capabilities.setCapability(TestData.RESOLUTION_CAPABILITY, TestData.RESOLUTION_VALUE);
      capabilities.setCapability(TestData.ACCEPT_SSL_ALERTS_CAPABILITY, TestData.TRUE_VALUE);
      capabilities.setCapability(
          TestData.SELENIUM_VERSION_CAPABILITY, TestData.SELENIUM_VERSION_VALUE);
      capabilities.setCapability(
          TestData.CHROME_DRIVER_VERSION_CAPABILITY, TestData.CHROME_DRIVER_VERSION_VALUE);
      capabilities.setCapability(TestData.PROJECT_CAPABILITY, TestData.PROJECT_VALUE);

      LOGGER.info("Initializing Chrome browser...");
      return createBrowserStackRemoteDriver(capabilities);
    }

    /**
     * Create a remote WebDriver instance on BrowserStack.
     *
     * @param capabilities The capabilities to use when creating the remote WebDriver instance.
     * @return The created WebDriver instance.
     */
    private WebDriver createBrowserStackRemoteDriver(DesiredCapabilities capabilities) {
      try {
        return new RemoteWebDriver(
            new URL(
                String.format(
                    TestData.BROWSERSTACK_HUB_URL,
                    TestData.BROWSERSTACK_USER_ENVIRONEMNT_VARIABLE,
                    TestData.BROWSERSTACK_TOKEN_ENVIRONEMNT_VARIABLE)),
            capabilities);
      } catch (Exception e) {
        throw new RuntimeException(
            "Failed to initialise the remote driver instance. Exception = " + e);
      }
    }
  };

  protected static final Logger LOGGER = Logger.getLogger(ExecutionMode.class.getName());
}
