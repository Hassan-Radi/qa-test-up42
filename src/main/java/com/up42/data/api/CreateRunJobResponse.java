package com.up42.data.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CreateRunJobResponse {

  private String error;
  private Data data;

  public static class Data {

    private String id;
    private String displayId;
    private String createdAt;
    private String updatedAt;
    private String status;
    private String name;
    private String startedAt;
    private String finishedAt;
    private String mode;
    private String workflowId;
    private String workflowName;
    private Inputs[] inputs;

    @JsonIgnore
    public Inputs[] getInputs() {
      return inputs;
    }

    @JsonIgnore
    public void setInputs(Inputs[] inputs) {
      this.inputs = inputs;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getDisplayId() {
      return displayId;
    }

    public void setDisplayId(String displayId) {
      this.displayId = displayId;
    }

    public String getCreatedAt() {
      return createdAt;
    }

    public void setCreatedAt(String createdAt) {
      this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
      return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
      this.updatedAt = updatedAt;
    }

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getStartedAt() {
      return startedAt;
    }

    public void setStartedAt(String startedAt) {
      this.startedAt = startedAt;
    }

    public String getFinishedAt() {
      return finishedAt;
    }

    public void setFinishedAt(String finishedAt) {
      this.finishedAt = finishedAt;
    }

    public String getMode() {
      return mode;
    }

    public void setMode(String mode) {
      this.mode = mode;
    }

    public String getWorkflowId() {
      return workflowId;
    }

    public void setWorkflowId(String workflowId) {
      this.workflowId = workflowId;
    }

    public String getWorkflowName() {
      return workflowName;
    }

    public void setWorkflowName(String workflowName) {
      this.workflowName = workflowName;
    }

    static class Inputs {}
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }
}
