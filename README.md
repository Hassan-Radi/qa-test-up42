# Steps to run the code:
- To run locally, you need to download the correct driver GeckoDriver/ChromeDriver depending on the browser version you are using:
     1. **ChromeDriver:** can be downloaded from here: https://chromedriver.chromium.org/downloads
     2.  **GeckoDriver:** can be downloaded from here: https://github.com/mozilla/geckodriver/releases
- Then you need to provide the correct *system property*, depending on the browser you selected at step #1 above.
     1. **webdriver.chrome.driver:** the driver path for Chrome browser
     2. **webdriver.gecko.driver:** the driver path for Firefox browser
- To run on BrowserStack, you need to provide the following *environment variables*:
     1. **BROWSERSTACK_USER:** The username to access BrowserStack.
     2. **BROWSERSTACK_TOKEN:** The token to access BrowserStack.


# Run command:
compile test -DexecutionMode=LOCAL -Dbrowser=chrome -Dtest=WebTests -Dwebdriver.chrome.driver="PATH_TO_DRIVER_FILE"

# Assumptions:
- Assuming that the account always starts with 0 projects (excluding the Demo one).

# Run configurations:
- executionMode:
     - The execution mode for the run. Can be one of the following values: **LOCAL**, **BROWSERSTACK**
- browser:
     - The browser name to run on. Can be one of the following values: **chrome**, **firefox**

### Tested on:
- Locally:
     1. Firefox 71
     2. Firefox 72.0.1
     3. Chrome 79
- On BrowserStack:
     1. Firefox 69
     2. Chrome 77
