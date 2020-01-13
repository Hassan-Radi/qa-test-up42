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

public class PipelineComponent extends PageObject {

  protected static final Logger LOGGER = Logger.getLogger(PipelineComponent.class.getName());
  private static String BASE_LOCATOR;
  private static final String PIPELINE_NAME_LOCATOR = "//h3";

  public PipelineComponent(String baseLocator) {
    BASE_LOCATOR = baseLocator;
  }

  /** @return A String representing the name of the pipeline. */
  public String getPipelineName() {
    return getPipelineNameElement().getText();
  }

  /** @return A WebElement representing the Pipeline name element */
  public WebElement getPipelineNameElement() {
    return driver.findElement(By.xpath(BASE_LOCATOR + PIPELINE_NAME_LOCATOR));
  }
}
