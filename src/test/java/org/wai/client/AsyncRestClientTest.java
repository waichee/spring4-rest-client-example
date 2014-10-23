package org.wai.client;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.wai.client.response.Response;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AsyncRestClientTest {

  private MockRestServiceServer mockServer;

  @Value("${service.url}")
  private String apiUrl;

  private AsyncRestClient asyncRestClient;

  @Before
  public void setup() {
    AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
    this.mockServer = MockRestServiceServer.createServer(asyncRestTemplate);
    asyncRestClient = new AsyncRestClient(asyncRestTemplate);
    asyncRestClient.setApiUrl(apiUrl);
  }



  @Test
  public void testGetDataStringAsync() throws Exception {

    String mockResponseBody = "{\"success\" : false, \"error\" : {\"type\" : \"errorType\" , \"description\" : \"some message about the error\"}}";
    this.mockServer.expect(requestTo(apiUrl)).andExpect(method(HttpMethod.GET)).andRespond(withSuccess(mockResponseBody, MediaType.APPLICATION_JSON));

    ListenableFuture<ResponseEntity<String>> responseFuture = asyncRestClient.getDataStringAsync();

    while (!responseFuture.isDone()) {
      TimeUnit.MILLISECONDS.sleep(100);
    }
    this.mockServer.verify();

    ResponseEntity<String> responseEntity = responseFuture.get();
    assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
    assertTrue(responseEntity.getBody().equals(mockResponseBody));
    assertTrue(responseEntity.getBody().contains("some message about the error"));

  }

  @Test
  public void testGetDataJsonAsync() throws Exception {

    String mockResponseBody = "{\"success\" : true, \"error\" : {\"type\" : \"errorType\" , \"description\" : \"some message about the error\"}}";
    this.mockServer.expect(requestTo(apiUrl)).andExpect(method(HttpMethod.GET)).andRespond(withSuccess(mockResponseBody, MediaType.APPLICATION_JSON));

    ListenableFuture<ResponseEntity<Response>> responseFuture = asyncRestClient.getDataJsonAsync();

    while (!responseFuture.isDone()) {
      TimeUnit.MILLISECONDS.sleep(100);
    }
    this.mockServer.verify();

    ResponseEntity<Response> responseEntity = responseFuture.get();
    assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
    assertTrue(responseEntity.getBody().getSuccess().equals(true));
    assertTrue(responseEntity.getBody().getError().getType().equals("errorType"));
    assertTrue(responseEntity.getBody().getError().getDescription().equals("some message about the error"));

  }




}
