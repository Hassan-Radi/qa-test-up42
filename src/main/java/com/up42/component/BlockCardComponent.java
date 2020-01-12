package com.up42.component;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.up42.core.PageObject;

public class BlockCardComponent extends PageObject {

  protected static final Logger LOGGER = Logger.getLogger(BlockCardComponent.class.getName());
  private static String BASE_LOCATOR;
  private static String BLOCK_TITLE_TEXT_LOCATOR = "//div[contains(@class,'title_')]";
  private static String BLOCK_SUBTITLE_TEXT_LOCATOR = "//div[contains(@class,'subtitle_')]";

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
