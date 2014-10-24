package org.wai.client;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.wai.client.response.Response;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SyncRestClientTest {

  private MockRestServiceServer mockServer;

  @Value("${service.full.url}")
  private String apiUrl;

  private SyncRestClient client;

  @Before
  public void setup() {
    RestTemplate restTemplate = new RestTemplate();
    this.mockServer = MockRestServiceServer.createServer(restTemplate);
    client = new SyncRestClient(restTemplate);
    client.setApiUrl(apiUrl);
  }



  @Test
  public void testGetDataStringAsync() throws Exception {
    String mockResponseBody = "{\"success\" : false, \"error\" : {\"type\" : \"FATAL\" , \"description\" : \"some bla bla the error\"}}";
    this.mockServer.expect(requestTo(apiUrl)).andExpect(method(HttpMethod.GET)).andRespond(withSuccess(mockResponseBody, MediaType.APPLICATION_JSON));
    String response = client.getStringDataSync();

    this.mockServer.verify();
    assertTrue(response.equals(mockResponseBody));
  }

  @Test
  public void testGetDataJsonAsync() throws Exception {

    String mockResponseBody = "{\"success\" : true, \"error\" : {\"type\" : \"NAN\" , \"description\" : \"some message about the NA error\"}}";
    this.mockServer.expect(requestTo(apiUrl)).andExpect(method(HttpMethod.GET)).andRespond(withSuccess(mockResponseBody, MediaType.APPLICATION_JSON));
    Response json = client.getJsonDataSync();

    this.mockServer.verify();
    assertTrue(json.getSuccess().equals(true));
    assertTrue(json.getError().getType().equals("NAN"));
    assertTrue(json.getError().getDescription().equals("some message about the NA error"));

  }




}
