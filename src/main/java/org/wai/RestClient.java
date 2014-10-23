package org.wai;

import org.springframework.web.client.RestOperations;

public class RestClient {
  private static final String API_URL = "http://localhost:8080/api/v1/dummy";

  private final RestOperations restTemplate;

  public RestClient(RestOperations restTemplate) {
    this.restTemplate = restTemplate;
  }

  public void getLvrRanges() {

    String responseString = restTemplate.getForObject(API_URL, String.class);
    System.out.println("Response string:" + responseString);

    Response responseObject = restTemplate.getForObject(API_URL, Response.class);
    System.out.println("JSON response description:" + responseObject.getError().getDescription());

  }


}
