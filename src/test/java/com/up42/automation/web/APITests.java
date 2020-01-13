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
package com.up42.automation.web;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.up42.data.TestData;
import com.up42.data.api.AccessTokenResponse;
import com.up42.data.api.CreateRunJobResponse;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APITests {

  protected static final Logger LOGGER = Logger.getLogger(APITests.class.getName());
  private static String accessTokenValue;
  private static String jobId;

  @Test
  public void getAccesToken() {
    RestAssured.baseURI =
        String.format(TestData.API_OAUTH_TOKEN_URL, TestData.PROJECT_ID, TestData.PROJECT_API_KEY);
    RequestSpecification httpRequest =
        RestAssured.given()
            .contentType(ContentType.URLENC)
            .param(TestData.GRANT_TYPE_PARAM_NAME, TestData.GRANT_TYPE_PARAM_VALUE);

    // Perform the POST operation
    Response response = httpRequest.post();

    // parse the response to a data object
    AccessTokenResponse accessToken = response.getBody().as(AccessTokenResponse.class);

    accessTokenValue = accessToken.getAccess_token();

    // verify the status code and content type
    response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
  }

  @Test(dependsOnMethods = "getAccesToken")
  public void createAndRunJob() {
    RestAssured.baseURI =
        String.format(TestData.CREATE_RUN_JOB_URL, TestData.PROJECT_ID, TestData.WORKFLOW_ID);
    RequestSpecification httpRequest =
        RestAssured.given()
            .contentType(ContentType.JSON)
            .auth()
            .oauth2(accessTokenValue)
            .body(TestData.CREATE_RUN_JOB_JSON_BODY);

    // Perform the POST operation
    Response response = httpRequest.post();

    // parse the response to a data object
    CreateRunJobResponse jobResponse = response.getBody().as(CreateRunJobResponse.class);

    jobId = jobResponse.getData().getId();

    // verify the status code and content type
    response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
  }
}
