package com.up42.data;

/** A data object representing a Block */
public class Block {

  private String blockName;
  private String blockCompanyName;

  public Block(String blockName, String blockCompanyName) {
    super();
    this.blockName = blockName;
    this.blockCompanyName = blockCompanyName;
  }

  public String getBlockName() {
    return blockName;
  }

  public void setBlockName(String blockName) {
    this.blockName = blockName;
  }

  public String getBlockCompanyName() {
    return blockCompanyName;
  }

  public void setBlockCompanyName(String blockCompanyName) {
    this.blockCompanyName = blockCompanyName;
  }

  @Override
  public String toString() {
    return "Block [blockName=" + blockName + ", blockCompanyName=" + blockCompanyName + "]";
  }
}
