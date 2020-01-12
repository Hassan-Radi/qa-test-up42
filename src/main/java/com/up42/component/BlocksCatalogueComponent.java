package com.up42.component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.up42.core.PageObject;
import com.up42.data.TestData;
import com.up42.util.Helper;

public class BlocksCatalogueComponent extends PageObject {

  protected static final Logger LOGGER = Logger.getLogger(BlocksCatalogueComponent.class.getName());
  private static final String BLOCK_CARD_BASE_LOCATOR =
      "(//div[contains(@class,'vueGridRow')]//div[contains(@class,'vueCard_')])";

  public BlocksCatalogueComponent() {
    /** Make sure that the page loaded successfully before interacting with it */
    LOGGER.info("Waiting for the blocks catalogue to load...");
    wait.until(ExpectedConditions.visibilityOf(headerText));
  }

  @FindBy(xpath = "//h4[text()='Blocks Catalogue']")
  private WebElement headerText;

  @FindBy(xpath = "//input[@placeholder='Search']")
  private WebElement blockNameTextField;

  @FindBy(xpath = BLOCK_CARD_BASE_LOCATOR)
  private List<WebElement> blocksList;

  @FindBy(xpath = "//button[contains(text(),'View Catalog')]//following-sibling::button")
  private WebElement addBlockButton;

  public void selectBlock(String blockName, String blockByCompany) {
    LOGGER.info(String.format("Typing block name [%s]...", blockName));
    blockNameTextField.sendKeys(blockName);

    // wait for the results to show for a minute
    Helper.sleep(TestData.SECOND_IN_MILLI);

    // initialize the list with the elements that appeared
    getListOfBlockCardComponents()
        .stream()
        .filter(
            it ->
                it.getBlockTitle().equalsIgnoreCase(blockName)
                    && it.getBlockSubtitle().equalsIgnoreCase(blockByCompany))
        .collect(Collectors.toList())
        .get(0)
        .getTitleElement()
        .click();

    // confirm adding the block by clicking on the 'Add Block' button
    wait.until(ExpectedConditions.visibilityOf(addBlockButton)).click();
  }

  /** @return A list of block card component objects to use in the filtering process */
  private List<BlockCardComponent> getListOfBlockCardComponents() {
    List<BlockCardComponent> blockCards = new ArrayList<>();

    // initialize the list of block cards
    for (int i = 0; i < blocksList.size(); i++) {
      blockCards.add(
          new BlockCardComponent(String.format("%s[%s]", BLOCK_CARD_BASE_LOCATOR, i + 1)));
    }
    return blockCards;
  }
}
