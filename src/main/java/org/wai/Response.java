package org.wai;

import java.lang.*;

public class Response {


  private Boolean success;

  private Error error;

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public Error getError() {
    return error;
  }

  public void setError(Error error) {
    this.error = error;
  }
}
