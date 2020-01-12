# Steps to run the code:



# Run command:
compile test -DexecutionMode=LOCAL -Dbrowser=chrome -Dwebdriver.chrome.driver="PATH_TO_DRIVER_FILE"


# Run configurations:

## executionMode:
- The execution mode for the run. Can be one of the following values: **LOCAL**, **BROWSERSTACK**

- To run locally, you need to provide one of the following system property:
    1. **webdriver.chrome.driver:** the driver path for Chrome browser
    2. **webdriver.gecko.driver:** the driver path for Firefox browser

- To run on BrowserStack, you need to provide the following environment variables:
    1. **BROWSERSTACK_USER:** The username to access BrowserStack.
    2. **BROWSERSTACK_TOKEN:** The token to access BrowserStack.


## browser:
The browser name to run on. Can be one of the following values: chrome, firefox
