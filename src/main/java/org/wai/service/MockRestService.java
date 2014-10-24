package org.wai.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

/**
 * Mock HTTP REST Service using WireMock standalone
 */
@Service
public class MockRestService {

  private static final Logger LOG = LoggerFactory.getLogger(MockRestService.class);

  private WireMockServer mockServer;

  @Value("${api.version}")
  private String apiVersion;

  @Value("${service.port}")
  private Integer port;

  @Value("${service.hostname}")
  private String hostname;

  @Value("${service.relative.url}")
  private String serviceUrl;


  public void startServer(){

    if(mockServer == null) {
      this.mockServer = new WireMockServer(wireMockConfig().port(port));
      WireMock.configureFor(hostname, port);
      mockServer.start();
      LOG.info("Mock server is started at port {}", port);

      //Stubbing the response returned by the mock server
      stubFor(get(urlEqualTo("/"))
          .willReturn(aResponse()
              .withHeader("Content-Type", "text/plain")
              .withBody("I am a Mock server....")));

      //Always returned OK Json response
      stubFor(get(urlMatching(serviceUrl))
          .willReturn(aResponse()
              .withStatus(200)
              .withHeader("Content-Type", "application/json")
              .withBody("{\"success\" : true, \"error\" : {\"type\" : \"NotAnError\" , \"description\" : \"NA\"}}")));


    } else {
      LOG.warn("Not starting mock server as its already running...");
    }
  }

  @PreDestroy
  public void destroy(){
    if(mockServer != null)
      mockServer.stop();
  }

}
