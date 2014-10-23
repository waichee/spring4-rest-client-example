package org.wai.client.response;

import java.lang.*;

public class Response {


  private Boolean success;

  private org.wai.client.response.Error error;

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public org.wai.client.response.Error getError() {
    return error;
  }

  public void setError(org.wai.client.response.Error error) {
    this.error = error;
  }

  @Override
  public String toString() {
    return "Response{" +
        "success=" + success +
        ", error=" + error +
        '}';
  }
}
