package org.wai.client.response;

public class Error {

  String description;

  String type;


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Error{" +
        "description='" + description + '\'' +
        ", type='" + type + '\'' +
        '}';
  }
}
