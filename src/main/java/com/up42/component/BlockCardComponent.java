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
package com.up42.component;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.up42.core.PageObject;

public class BlockCardComponent extends PageObject {

  protected static final Logger LOGGER = Logger.getLogger(BlockCardComponent.class.getName());
  private static String BASE_LOCATOR;
  private static final String BLOCK_TITLE_TEXT_LOCATOR = "//div[contains(@class,'title_')]";
  private static final String BLOCK_SUBTITLE_TEXT_LOCATOR = "//div[contains(@class,'subtitle_')]";

  public BlockCardComponent(String baseLocator) {
    BASE_LOCATOR = baseLocator;
  }

  /** @return A String representing the block's title */
  public String getBlockTitle() {
    LOGGER.info("Getting block title...");
    return getTitleElement().getText();
  }

  /** @return A String representing the block's subtitle */
  public String getBlockSubtitle() {
    LOGGER.info("Getting block subtitle...");
    return getSubtitleElement().getText();
  }

  public WebElement getSubtitleElement() {
    return driver.findElement(By.xpath(BASE_LOCATOR + BLOCK_SUBTITLE_TEXT_LOCATOR));
  }

  public WebElement getTitleElement() {
    return driver.findElement(By.xpath(BASE_LOCATOR + BLOCK_TITLE_TEXT_LOCATOR));
  }
}
