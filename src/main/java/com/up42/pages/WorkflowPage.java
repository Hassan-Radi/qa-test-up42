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
package com.up42.pages;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.up42.component.BlocksCatalogueComponent;
import com.up42.component.PipelineComponent;
import com.up42.core.PageObject;
import com.up42.data.Block;
import com.up42.data.TestData;
import com.up42.util.Helper;

public class WorkflowPage extends PageObject {

  protected static final Logger LOGGER = Logger.getLogger(WorkflowPage.class.getName());
  private static final String DATA_BLOCKS_IN_PIPELINE_BASE_LOCATOR =
      "(//h4[contains(text(),'Processing')]//preceding::div[contains(@class,'block_')])";
  private static final String PROCESSING_BLOCKS_IN_PIPELINE_BASE_LOCATOR =
      "(//h4[contains(text(),'Processing')]//following::div[contains(@class,'block_')])";

  public WorkflowPage() {
    /** Make sure that the page loaded successfully before interacting with it */
    LOGGER.info("Waiting for the workflow page to load...");
    wait.until(ExpectedConditions.visibilityOf(workflowHeaderText));
  }

  @FindBy(xpath = "//h1[text()='Create a Workflow']")
  private WebElement workflowHeaderText;

  @FindBy(id = "name")
  private WebElement workflowNameTextField;

  @FindBy(id = "description")
  private WebElement workflowDescriptionTextField;

  @FindBy(xpath = "//button[@type='submit']")
  private WebElement nextButton;

  @FindBy(
      xpath = "(//h4[contains(text(),'Data')]//following::button[contains(text(),'Add Block')])[1]")
  private WebElement addDataBlockButton;

  @FindBy(
      xpath =
          "(//h4[contains(text(),'Processing')]//following::button[contains(text(),'Add Block')])[1]")
  private WebElement addProcessingBlockButton;

  @FindBy(xpath = "//h4//following::button[contains(text(),'Next')]")
  private WebElement secondNextButton;

  @FindBy(xpath = "//button[contains(text(),'Configure Job')]")
  private WebElement configureJobButton;

  @FindBy(xpath = DATA_BLOCKS_IN_PIPELINE_BASE_LOCATOR)
  private List<WebElement> dataBlocksInPipelineList;

  @FindBy(xpath = PROCESSING_BLOCKS_IN_PIPELINE_BASE_LOCATOR)
  private List<WebElement> processingBlocksInPipelineList;

  /**
   * Create a new workflow using the provided data.
   *
   * @param workflowName The name to give to the workflow.
   * @param workflowDescription The description to give to the workflow.
   * @param dataBlocks A string array representing all the data blocks to add.
   * @param processingBlocks A string array representing all the processing blocks to add.
   */
  public void createNewWorkflow(
      String workflowName,
      String workflowDescription,
      Block[] dataBlocks,
      Block[] processingBlocks) {
    LOGGER.info(
        String.format(
            "Creating a new workflow with name [%s] and description [%s]...",
            workflowName, workflowDescription));
    workflowNameTextField.sendKeys(workflowName);
    workflowDescriptionTextField.sendKeys(workflowDescription);

    LOGGER.info("Clicking on the 'Next' button to move to the next page...");
    nextButton.click();

    // wait for the animation to complete
    Helper.sleep(TestData.SECOND_IN_MILLI);

    Arrays.stream(dataBlocks)
        .forEach(
            it -> {
              LOGGER.info(String.format("Adding the following 'data' block [%s]...", it));
              openDataCatalogue().selectBlock(it.getBlockName(), it.getBlockCompanyName());
            });

    // wait for the animation to complete
    Helper.sleep(TestData.SECOND_IN_MILLI);

    Arrays.stream(processingBlocks)
        .forEach(
            it -> {
              LOGGER.info(String.format("Adding the following 'processing' block [%s]...", it));
              openProcessingCatalogue().selectBlock(it.getBlockName(), it.getBlockCompanyName());
            });

    // wait for the animation to complete
    Helper.sleep(TestData.SECOND_IN_MILLI);

    LOGGER.info("Clicking on the 'Next' button to move to the next page...");
    secondNextButton.click();

    // wait for the animation to complete
    Helper.sleep(TestData.SECOND_IN_MILLI);

    wait.until(ExpectedConditions.visibilityOf(configureJobButton));
  }

  /** @return An object representing the blocks catalogue with data catalogues selected */
  public BlocksCatalogueComponent openDataCatalogue() {
    addDataBlockButton.click();
    return new BlocksCatalogueComponent();
  }

  /** @return An object representing the blocks catalogue with processing catalogues selected */
  public BlocksCatalogueComponent openProcessingCatalogue() {
    addProcessingBlockButton.click();
    return new BlocksCatalogueComponent();
  }

  /** @return A list of all the data blocks from the pipeline */
  public LinkedList<PipelineComponent> getDataBlocksFromPipeline() {
    // use a LinkedList here to retain the order of creation
    LinkedList<PipelineComponent> pipelineComponent = new LinkedList<>();

    // initialize the list of data blocks from the pipeline
    for (int i = 0; i < dataBlocksInPipelineList.size(); i++) {
      pipelineComponent.add(
          new PipelineComponent(
              String.format("%s[%s]", DATA_BLOCKS_IN_PIPELINE_BASE_LOCATOR, i + 1)));
    }
    return pipelineComponent;
  }

  /** @return A list of all the processing blocks from the pipeline */
  public LinkedList<PipelineComponent> getProcessingBlocksFromPipeline() {
    // use a LinkedList here to retain the order of creation
    LinkedList<PipelineComponent> pipelineComponent = new LinkedList<>();

    // initialize the list of processing blocks from the pipeline
    for (int i = 0; i < processingBlocksInPipelineList.size(); i++) {
      pipelineComponent.add(
          new PipelineComponent(
              String.format("%s[%s]", PROCESSING_BLOCKS_IN_PIPELINE_BASE_LOCATOR, i + 1)));
    }
    return pipelineComponent;
  }
}
